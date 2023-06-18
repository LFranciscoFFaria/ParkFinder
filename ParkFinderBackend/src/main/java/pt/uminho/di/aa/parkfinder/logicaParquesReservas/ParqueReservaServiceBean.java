package pt.uminho.di.aa.parkfinder.logicaParquesReservas;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pt.uminho.di.aa.parkfinder.logicaParques.DAOs.LugarEstacionamentoDAO;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueServiceBean;
import pt.uminho.di.aa.parkfinder.logicaParques.model.LugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaReservas.ReservaServiceBean;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ParqueReservaServiceBean implements ParqueReservaService {

	private final ParqueServiceBean parqueServiceBean;
	private final ReservaServiceBean reservaServiceBean;
	// TODO: Acredito que usar o DAO diretamento não seja muito correto,
	//  mas não sei fazer o que quero de outra maneira
	private final LugarEstacionamentoDAO lugarEstacionamentoDAO;

	public ParqueReservaServiceBean(ParqueServiceBean parqueServiceBean, ReservaServiceBean reservaServiceBean, LugarEstacionamentoDAO lugarEstacionamentoDAO) {
		this.parqueServiceBean = parqueServiceBean;
		this.reservaServiceBean = reservaServiceBean;
		this.lugarEstacionamentoDAO = lugarEstacionamentoDAO;
	}

	/**
	 * Função que cria um reserva instantanea num determinado parque associada a um utilizador.
	 * @param id_user identificador do utilizador que realiza o pedido de reserva
	 * @param id_parque identificador do parque onde a reserva vai ser efetuada
	 * @return retorna a reserva criada
	 */
	public Reserva criarReservaInstantanea(int id_user, int id_parque) throws Exception{
		//Reserva reserva = new Reserva();
		Parque parque = parqueServiceBean.procurarParque(id_parque);
		if (parque == null){
			throw new Exception("O parque não existe");
		}
		if (parque.getInstantaneos_livres()>0) {
			// TODO: Não estou bem a ver como vou buscar o Condutor
			// TODO: Não sei como definir o id por isso vou colocá-lo a 0
			Reserva reserva = new Reserva(0,null,null,parque,0,null,false,null,LocalDateTime.now(),null );
			reservaServiceBean.criarReserva(reserva);
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
	public Reserva criarReservaAgendada(int id_user, int id_parque, TipoLugarEstacionamento tipo, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception {
		List<Integer> ids_livres_parque = getIdLugarDisponivel(id_parque,tipo,data_inicio,data_fim);
		if (ids_livres_parque.size()>0) {
			Parque parque = parqueServiceBean.procurarParque(id_parque);
			if (parque == null)
				throw new Exception("O parque não existe");
			// TODO: Não estou bem a ver como vou buscar o Condutor
			LugarEstacionamento lugarEstacionamento = lugarEstacionamentoDAO.findById(ids_livres_parque.get(0)).orElse(null);
			if (lugarEstacionamento == null)
				throw new Exception("O lugar não existe");
			// Calcular o custo da reserva
			float custo = parqueServiceBean.calcularCusto(parque.getId(), lugarEstacionamento.getLugarId(), data_inicio,data_fim);
			// TODO: Verificar se para este tipo de reserva ser criado já necessita de estar paga previamente
			Reserva reserva = new Reserva(0,null,lugarEstacionamento,parque,0,custo,false,null,data_inicio,data_fim);
			reservaServiceBean.criarReserva(reserva);
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
	public List<Integer> getIdLugarDisponivel(int id_parque, TipoLugarEstacionamento tipo, LocalDateTime data_inicio, LocalDateTime data_fim) {
		return parqueServiceBean.procurarLugaresDisponiveis(id_parque,tipo,data_inicio,data_fim);
	}

	/**
	 * Se as condições se verificarem deixa o carro entrar do parque e marca uma reserva como ocupada
	 * @param id_reserva identificador da reserva usada para entrar no parque
	 * @param matricula matricula do carro que deu entrada no parque
	 * @return retorna verdadeiro a reserva estiver em condições de ser finalizada
	 * ou uma exeção se as condições não se verificarem.
	 */
	public boolean marcarEntradaParque(int id_reserva, String matricula) throws Exception{
		// TODO: 1- Faz sentido verificar horas de chegada para as reservas agendadas do condutor ?
		// TODO: 1- Para não o deixar entrar muito cedo no parque ?
		// TODO: 2- Faz sentido marcar a reserva logo como ocupada ou marcar como agendada e
		// TODO: 2- ter outra função que marca o estacionamento do carro no parque para mudar o estado para ocupada?
		Reserva reserva = reservaServiceBean.getReserva(id_reserva);
		// TODO: Permitir que o lugar da reserva seja nulo ou arrranjar outra maneira de resolver isto
		// Caso Reserva Instantânea
		if (reserva.getEstado() == 0 && reserva.getLugar() == null) {
			reserva.setEstado(2);
			reserva.setMatricula(matricula);
			return true;
		// Caso Reserva Agendada/Especial
		} else if (reserva.getEstado() == 1 && reserva.getLugar() != null) {
			reserva.setEstado(2);
			reserva.setMatricula(matricula);
			return true;
		}
		else
			throw new Exception("A reserva ainda não está paga ou ainda não se encontra marcada como ocupada");
	}

	/**
	 * Se as condições se verificarem deixa o carro sair do parque e marca uma reserva como concluida
	 * @param id_reserva identificador da reserva
	 * @return retorna verdadeiro a reserva estiver em condições de ser finalizada
	 * ou uma exeção se as condições não se verificarem.
	 */
	public boolean marcarSaidaParque(int id_reserva) throws Exception{
		Reserva reserva = reservaServiceBean.getReserva(id_reserva);
		if (reserva.getEstado() == 2 && reserva.isPago()) {
			reserva.setEstado(3);
			return true;
		}
		else
			throw new Exception("A reserva ainda não está paga ou ainda não se encontra marcada como ocupada");
	}

	/**
	 * Marca a reserva com o identificador fornecido como paga.
	 * @param id_reserva identificador da reserva
	 * @return retorna verdadeiro se a operação de atualização da reserva tiver sucesso.
	 */
	public boolean pagarReserva(int id_reserva) throws Exception{
		Reserva reserva = reservaServiceBean.getReserva(id_reserva);
		// TODO: Permitir que o lugar da reserva seja nulo ou arrranjar outra maneira de resolver isto
		// Lógica Reserva Instantanea
		if (!reserva.isPago()) {
			if (reserva.getEstado() == 2 && reserva.getLugar() == null) {
				reserva.setPago(true);
			}
			// Lógina Reserva Agendada/Especial
			else if (reserva.getEstado() == 0 && reserva.getLugar() != null) {
				reserva.setEstado(1);
				reserva.setPago(true);
			} else
				throw new Exception("A reserva não se encontra num estado que permite o pagamento da mesma.");
		}
		else
			throw new Exception("A reserva já foi paga.");
		return reservaServiceBean.updateReserva(reserva);
	}
}