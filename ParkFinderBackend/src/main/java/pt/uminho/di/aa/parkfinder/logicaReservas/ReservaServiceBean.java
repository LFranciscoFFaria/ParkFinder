package pt.uminho.di.aa.parkfinder.logicaReservas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pt.uminho.di.aa.parkfinder.logicaParquesReservas.EstadoReserva;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ReservaServiceBean implements ReservaService {

	private final ReservaDAO reservaDAO;

	@Autowired
	public ReservaServiceBean(ReservaDAO reservaDAO) {
		this.reservaDAO = reservaDAO;
	}

	/**
	 * Adiciona uma reserva à base de dados.
	 * @param r reserva já construida pronta para ser persistida na base de dados
	 * @return retorna a reserva adicionada
	 */
	public Reserva criarReserva(Reserva r) throws Exception{
		r.setId(0);
		LocalDateTime data_inicio = r.getDataInicio();
		LocalDateTime data_fim = r.getDataFim();
		if(data_inicio != null && data_fim != null && data_fim.isBefore(data_inicio))
			throw new Exception("A data de fim da reserva não pode ser anterior à data de início");
		return reservaDAO.save(r);
	}

	/**
	 * Remove uma reserva existente da base de dados.
	 * @param id_reserva identificador da reserva a remover da base de dados
	 */
	public void removerReserva(int id_reserva) throws Exception {
		if (!reservaDAO.existsById(id_reserva))
			throw new Exception("A reserva não existe!");
		reservaDAO.deleteById(id_reserva);
	}

	/**
	 * Encontra uma reserva existente na base de dados.
	 * @param id_reserva identificador da reserva a encontrar na base de dados
	 * @return retorna a reserva encontrada ou lança uma exceção se não a encontrar
	 */
	public Reserva getReserva(int id_reserva) throws Exception {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if(reserva == null)
			throw new Exception("A reserva não existe!");
		return reserva;
	}

	/**
	 * @param id_parque id de parque
	 * @param matricula matricula que está associada a uma reserva
	 * @return retorna uma reserva cujo estado se encontre em "OCUPADA", e que corresponda a um certo parque.
	 */
	public Reserva getReservaMatricula(int id_parque, String matricula) throws Exception {
		Reserva reserva = reservaDAO.findReservaByParqueIDAndMatriculaAndEstado(id_parque, matricula, EstadoReserva.OCUPADA).orElse(null);
		if(reserva == null)
			throw new Exception("Não existe uma reserva ativa para a matrícula passada por argumento!");
		return reserva;
	}

	/**
	 * Devolve a lista de reservas associadas a um parque.
	 * @param id_parque identificador do parque
	 */
	public List<Reserva> getReservasParque(int id_parque){
		// TODO: Considerar adicionar estado para obter as reservas OCUPADAS
		return reservaDAO.findAllByParqueIdOrderByDataInicioDesc(id_parque);
	}

	public List<Reserva> getReservasAtivasDoParque(int id_parque) {
		return reservaDAO.findAllByParqueIDAndEstado(id_parque, EstadoReserva.OCUPADA);
	}

	/**
	 * Encontra uma reserva existente na base de dados.
	 * @param reserva nova reserva que vai substituir a antiga na base de dados
	 * @return retorna verdadeiro caso consiga atualizar a reserva
	 */
	//public boolean updateReserva(Reserva reserva) throws Exception {
	//	if (reserva == null)
	//		throw new Exception("A reserva não pode ser nula!");
	//	Reserva reservaold = reservaDAO.findById(reserva.getId()).orElse(null);
	//	if (reservaold == null)
	//		throw new Exception("A reserva não existe!");
	//	LocalDateTime data_inicio =  reserva.getData_inicio();
	//	LocalDateTime data_fim = reserva.getData_fim();
	//	if(data_inicio != null && data_fim != null && data_fim.isBefore(data_inicio))
	//		throw new Exception("A data de fim da reserva não pode ser anterior à data de inicio");
	//
	//	reservaDAO.save(reserva);
	//	return true;
	//}

	/**
	 * Altera o estado da reserva para o passado por argumento.
	 *
	 * @param id_reserva identificador da reserva
	 * @param estado     novo estado da reserva
	 */
	public void setEstado(int id_reserva, int estado) throws Exception {
		Reserva reserva = getReservaPrivate(id_reserva);
		reserva.setEstado(estado);
		reservaDAO.save(reserva);
	}

	/**
	 * Altera o custo da reserva para o passado por argumento.
	 *
	 * @param id_reserva identificador da reserva
	 * @param custo      custo associado à reserva
	 */
	public void setCusto(int id_reserva, float custo) throws Exception {
		Reserva reserva = getReservaPrivate(id_reserva);
		reserva.setCusto(custo);
		reservaDAO.save(reserva);
	}

	/**
	 * Altera o valor da variável pago da reserva para o valor passado por argumento.
	 * @param id_reserva identificador da reserva
	 * @param pago variável boleana que indica se a reserva já foi paga
	 */
	public void setPago(int id_reserva, boolean pago) throws Exception {
		Reserva reserva = getReservaPrivate(id_reserva);
		reserva.setPago(pago);
		reservaDAO.save(reserva);
	}

	/**
	 * Altera o valor da data de início da reserva para o passado por argumento.
	 *
	 * @param id_reserva  identificador da reserva
	 * @param data_inicio data de inicio da reserva
	 */
	public void setDataInicio(int id_reserva, LocalDateTime data_inicio) throws Exception {
		Reserva reserva = getReservaPrivate(id_reserva);
		reserva.setDataInicio(data_inicio);
		var dataFim = reserva.getDataFim();
		if(dataFim != null && dataFim.isBefore(data_inicio))
			throw new Exception("Data inicio não pode ser superior à data de fim.");
		reservaDAO.save(reserva);
	}

	/**
	 * Altera o valor da data de fim da reserva para o passado por argumento.
	 *
	 * @param id_reserva identificador da reserva
	 * @param data_fim   data de fim da reserva
	 */
	public void setDataFim(int id_reserva, LocalDateTime data_fim) throws Exception {
		Reserva reserva = getReservaPrivate(id_reserva);
		reserva.setDataFim(data_fim);
		reservaDAO.save(reserva);
		var dataInicio = reserva.getDataInicio();
		if(dataInicio != null && dataInicio.isAfter(data_fim))
			throw new Exception("Data fim não pode ser inferior à data de inicio.");
	}

	/**
	 * Altera os valores das variáveis da reserva para os passados por argumento.
	 * Valores nulos servem para identificar campos que não é suposto alterar.
	 * @param id_reserva identificador da reserva
	 * @param estado     estado da reserva
	 * @param pago       estado de pagamento da reserva
	 * @param custo      custo da reserva
	 * @param dataInicio data de início da reserva
	 * @param dataFim    data de fim da reserva
	 * @param matricula  matrícula do carro associado à reserva
	 */
	public Reserva setAll(int id_reserva, Optional<Integer> estado, Optional<Boolean> pago, Optional<Float> custo,
						  Optional<LocalDateTime> dataInicio, Optional<LocalDateTime> dataFim, Optional<String> matricula) throws Exception {
		Reserva reserva = getReservaPrivate(id_reserva);

		if(estado != null && estado.isPresent())
			reserva.setEstado(estado.get());
		if(pago != null && pago.isPresent())
			reserva.setPago(pago.get());
		if(custo != null)
			reserva.setCusto(custo.orElse(null));
		if(dataInicio != null)
			reserva.setDataInicio(dataInicio.orElse(null));
		if(dataFim != null)
			reserva.setDataFim(dataFim.orElse(null));
		if(matricula != null)
			reserva.setMatricula(matricula.orElse(null));

		var data_inicio_atual = reserva.getDataInicio();
		var data_fim_atual = reserva.getDataFim();

		if(data_inicio_atual != null && data_fim_atual != null && data_inicio_atual.isAfter(data_fim_atual))
			throw new Exception("Data inicio não pode ser superior à data de fim.");

		return reservaDAO.save(reserva);
	}

	/**
	 * Altera o valor da variável matrícula da reserva para o passado por argumento.
	 * @param id_reserva identificador da reserva
	 * @param matricula matrícula do carro que se pretende associar à reserva
	 */
	public void setMatricula(int id_reserva, String matricula) throws Exception {
		Reserva reserva = getReservaPrivate(id_reserva);
		reserva.setMatricula(matricula);
		reservaDAO.save(reserva);
	}

	/**
	 * Devolve a lista com as reservas do utilizador com o identificador especificado
	 * ordenadas de forma decrescente pela data de início.
	 * @param id_user identificador do utilizador
	 * @return retorna a lista de reservas associadas ao utilizador.
	 */
	public List<Reserva> listarReservas(int id_user) {
		return reservaDAO.findAllByUtilizadorIdOrderByDataInicioDesc(id_user);
	}


	private Reserva getReservaPrivate(int id_reserva) throws Exception {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva == null)
			throw new Exception("Reserva não existe!");
		return reserva;
	}

}