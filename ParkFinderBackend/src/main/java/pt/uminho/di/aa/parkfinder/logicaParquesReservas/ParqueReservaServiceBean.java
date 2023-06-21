package pt.uminho.di.aa.parkfinder.logicaParquesReservas;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueService;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Horario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.LugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaReservas.ReservaService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ParqueReservaServiceBean implements ParqueReservaService {

	private final ParqueService parqueService;
	private final ReservaService reservaService;
	private final UtilizadorService utilizadorService;

	public ParqueReservaServiceBean(ParqueService parqueService, ReservaService reservaService, UtilizadorService utilizadorService) {
		this.parqueService = parqueService;
		this.reservaService = reservaService;
		this.utilizadorService = utilizadorService;
	}

	/**
	 * Função que cria um reserva instantanea num determinado parque associada a um utilizador.
	 * @param id_user identificador do utilizador que realiza o pedido de reserva
	 * @param id_parque identificador do parque onde a reserva vai ser efetuada
	 * @return retorna a reserva criada
	 */
	@Transactional(rollbackOn = Exception.class)
	public Reserva criarReservaInstantanea(int id_user, int id_parque) throws Exception{
		Parque parque = parqueService.procurarParque(id_parque);
		if (parque == null)
			throw new Exception("O parque não existe");

		if (parque.getInstantaneos_livres() > 0) {
			Utilizador utilizador = utilizadorService.getUtilizador(id_user);
			Reserva reserva = new Reserva(utilizador, null, parque, EstadoReserva.AGENDADA,null,false,null, LocalDateTime.now(),null);
			reservaService.criarReserva(reserva);
			return reserva;
		}
		else
			throw new Exception("O parque não possui lugares instantâneos livres");
	}

	/**
	 *
	 * @param id_user identificador do utilizador que realiza o pedido de reserva
	 * @param id_parque identificador do parque onde a reserva vai ser efetuada
	 * @param tipo tipo do lugar que se prentende reservar
	 * @param data_inicio data de início da ocupação do lugar
	 * @param data_fim data de fim da ocupação do lugar
	 */
	@Transactional(rollbackOn = Exception.class)
	public Reserva criarReservaAgendada(int id_user, int id_parque, TipoLugarEstacionamento tipo, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception {
		Horario horario = parqueService.getHorario(id_parque);
		LocalTime time_inicio = LocalTime.from(data_inicio),
				  time_fim = LocalTime.from(data_fim);

		if(data_inicio.getDayOfYear() == data_fim.getDayOfYear() && data_inicio.getYear() == data_fim.getYear())
			throw new Exception("No momento, o sistema não suporta reservas que abrangem múltiplos dias.");

		if(!horario.estaAberto(data_inicio.getDayOfWeek(), time_inicio, time_fim))
			throw new Exception("O parque não se encontra aberto parcial-/totalmente no período pretendido.");

		List<Integer> ids_livres_parque = getIdsDeLugaresDisponiveis(id_parque,tipo,data_inicio,data_fim);
		if (ids_livres_parque.size() > 0) {
			Parque parque = parqueService.procurarParque(id_parque);
			if (parque == null)
				throw new Exception("O parque não existe");

			LugarEstacionamento lugarEstacionamento = parqueService.getLugarById(ids_livres_parque.get(0));
			if (lugarEstacionamento == null)
				throw new Exception("O lugar não existe");

			// Calcular o custo da reserva
			float custo = parqueService.calcularCusto(parque.getId(), lugarEstacionamento.getLugarId(), data_inicio,data_fim);

			Utilizador utilizador = utilizadorService.getUtilizador(id_user);
			Reserva reserva = new Reserva(utilizador,lugarEstacionamento,parque,EstadoReserva.PENDENTE_PAGAMENTO, custo,false,null,data_inicio,data_fim);
			reservaService.criarReserva(reserva);
			return reserva;
		}
		else
			throw new Exception("O parque escolhido não possui lugares livres do tipo escolhido para o periodo estabelecido!");
	}

	/**
	 * Retorna uma lista que contêm os identificadores de todos os lugares disponíveis do tipo
	 * especificado no periodo estabelecido para o parque escolhido.
	 * @param id_parque identificador do parque
	 * @param tipo tipo do lugar que se prentende verificar a disponibilidade
	 * @param data_inicio data de início da ocupação pretendida do lugar
	 * @param data_fim data de fim da ocupação pretendida do lugar
	 */
	public List<Integer> getIdsDeLugaresDisponiveis(int id_parque, TipoLugarEstacionamento tipo, LocalDateTime data_inicio, LocalDateTime data_fim) {
		return parqueService.procurarLugaresDisponiveis(id_parque,tipo,data_inicio,data_fim);
	}

	/**
	 * Se as condições se verificarem deixa o carro entrar do parque e marca uma reserva como ocupada
	 * @param id_reserva identificador da reserva usada para entrar no parque
	 * @param matricula matricula do carro que deu entrada no parque
	 * @return retorna verdadeiro a reserva estiver em condições de ser finalizada
	 * ou uma exeção se as condições não se verificarem.
	 */

	public boolean marcarEntradaParque(int id_reserva, String matricula) throws Exception{
		Reserva reserva = reservaService.getReserva(id_reserva);

		LocalDateTime agora = LocalDateTime.now();
		LugarEstacionamento lugar = reserva.getLugar();

		// Caso Reserva Instantânea
		if(lugar == null){
			//Precisa apenas de estar com estado em agendada
			if(reserva.getEstado() == EstadoReserva.AGENDADA){
				reservaService.setAll(id_reserva, Optional.of(EstadoReserva.OCUPADA), null, null, null, null, Optional.of(matricula));
				return true;
			}
		}
		// Caso Reserva Agendada/Especial
		else {
			//Se o estado está em agendado, então a reserva já foi paga, portanto não é necessário verificar.
			//Preciso verificar se está a entrar dentro do parque, durante o tempo da sua reserva.
			if(reserva.getEstado() == EstadoReserva.AGENDADA
					&& reserva.getDataInicio().isBefore(agora)
					&& reserva.getDataFim().isAfter(agora)){
				reservaService.setAll(id_reserva, Optional.of(EstadoReserva.OCUPADA), null, null, null, null, Optional.of(matricula));
				return true;
			}
		}

		return false;
		//else
		//	throw new Exception("A reserva ainda não está paga ou ainda não se encontra marcada como ocupada");
	}

	/**
	 * Se as condições se verificarem deixa o carro sair do parque e marca uma reserva como concluida
	 * @param id_reserva identificador da reserva
	 * @return retorna verdadeiro a reserva estiver em condições de ser finalizada
	 * ou uma exeção se as condições não se verificarem.
	 */
	public boolean marcarSaidaParque(int id_reserva) throws Exception{
		Reserva reserva = reservaService.getReserva(id_reserva);
		if (reserva.getEstado() == EstadoReserva.OCUPADA && reserva.isPago()) {
			reservaService.setEstado(id_reserva,EstadoReserva.CONCLUIDA);
			return true;
		}
		return false;
		//else
		//	throw new Exception("A reserva ainda não está paga ou ainda não se encontra marcada como ocupada");
	}

	/**
	 * Marca a reserva com o identificador fornecido como paga.
	 * @param id_reserva identificador da reserva
	 * @return retorna verdadeiro se a operação de atualização da reserva tiver sucesso.
	 */
	@SuppressWarnings("OptionalAssignedToNull")
	@Transactional(rollbackOn = Exception.class)
	public boolean pagarReserva(int id_reserva) throws Exception{
		Reserva reserva = reservaService.getReserva(id_reserva);
		float custo;

		if (!reserva.isPago()) {
			if(reserva.getEstado() == EstadoReserva.PENDENTE_PAGAMENTO) {
				// Lógica Reserva Instantanea
				// Se lugar for nulo, então é reserva instantanea.
				// Reserva instantanea é paga quando se está dentro do parque.
				if (reserva.getLugar() == null) {
					LocalDateTime fimReserva = LocalDateTime.now();
					custo = parqueService.calcularCusto(reserva.getParqueID(), null, reserva.getDataInicio(), fimReserva);
					reservaService.setAll(id_reserva, Optional.of(EstadoReserva.OCUPADA), Optional.of(true), Optional.of(custo), null, Optional.of(fimReserva), null);
				}
				// Lógina Reserva Agendada/Especial
				else {
					custo = reserva.getCusto();
					reservaService.setAll(id_reserva, Optional.of(EstadoReserva.AGENDADA), Optional.of(true), null, null, null, null);
				}
			}
			else throw new Exception("A reserva não se encontra num estado que permite o pagamento da mesma.");
		} else throw new Exception("A reserva já foi paga.");

		//Atualiza estatisticas do parque
		parqueService.incrementaVolume_E_aumentaFaturacao(reserva.getParqueID(), custo);

		return true;
	}

	/**
	 * Calcula o valor a pagar pela reserva instantanea.
	 * Marca a reserva como pendente de pagamento.
	 * E define a data final.
	 * @param idReserva identificador da reserva
	 * @return montante a pagar pela reserva instantanea
	 * @throws Exception
	 */
	@Transactional
	public float calculaCustoReservaInstantanea(int idReserva) throws Exception {
		Reserva reserva = reservaService.getReserva(idReserva);
		if(reserva != null &&
		  !reserva.isPago() &&
		   reserva.getLugarID() == null &&
		   reserva.getEstado() == EstadoReserva.OCUPADA) {

			LocalDateTime agora = LocalDateTime.now();
			String tipoLugar = reserva.getLugar().getTipo().getNome();
			Precario precario = parqueService.getPrecarioByParqueIdAndTipoLugar(reserva.getParqueID(), tipoLugar);
			float custo = precario.calcular_preco(reserva.getDataInicio(), agora);

			reservaService.setAll(idReserva, Optional.of(EstadoReserva.PENDENTE_PAGAMENTO), null,
									  Optional.of(custo), null, Optional.of(agora), null);

			return custo;
		}
		else throw new Exception("Reserva não pode ser paga.");
	}
}