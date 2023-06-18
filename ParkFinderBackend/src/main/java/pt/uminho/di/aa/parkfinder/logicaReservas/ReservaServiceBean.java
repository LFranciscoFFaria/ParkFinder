package pt.uminho.di.aa.parkfinder.logicaReservas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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
		LocalDateTime data_inicio =  r.getData_inicio();
		LocalDateTime data_fim = r.getData_fim();
		if(data_fim.isBefore(data_inicio)){
			throw new Exception("A data de fim da reserva não pode ser anterior à data de início");
		}
		return reservaDAO.save(r);
	}

	/**
	 * Remove uma reserva existente da base de dados.
	 * @param id_reserva identificador da reserva a remover da base de dados
	 */
	public void removerReserva(int id_reserva) throws Exception {
		if (!reservaDAO.existsById(id_reserva)){
			throw new Exception("A reserva não existe!");
		}
		reservaDAO.deleteById(id_reserva);
	}

	/**
	 * Encontra uma reserva existente na base de dados.
	 * @param id_reserva identificador da reserva a encontrar na base de dados
	 * @return retorna a reserva encontrada ou lança um exceção se não a encontrar
	 */
	public Reserva getReserva(int id_reserva) throws Exception {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if(reserva == null)
			throw new Exception("A reserva não existe!");
		return reserva;
	}

	/**
	 * Encontra uma reserva existente na base de dados.
	 * @param reserva nova reserva que vai substituir a antiga na base de dados
	 * @return retorna verdadeiro caso consiga atualizar a reserva
	 */
	public boolean updateReserva(Reserva reserva) throws Exception {
		if (reserva == null)
			throw new Exception("A reserva não pode ser nula!");
		Reserva reservaold = reservaDAO.findById(reserva.getId()).orElse(null);
		if (reservaold == null)
			throw new Exception("A reserva não existe!");
		LocalDateTime data_inicio =  reserva.getData_inicio();
		LocalDateTime data_fim = reserva.getData_fim();
		if(data_fim.isBefore(data_inicio)){
			throw new Exception("A data de fim da reserva não pode ser anterior à data de inicio");
		}
		reservaDAO.save(reserva);
		return true;
	}

	/**
	 * Devolve a lista com as reservas do utilizador com o identificador especificado.
	 * @param id_user identificador do utilizador
	 * @return retorna a lista de reservas associadas ao utilizador.
	 */
	public List<Reserva> getReservas(int id_user) {
		return reservaDAO.findAllByUtilizadorId(id_user);
	}

	/**
	 * Altera o estado da reserva para o passado por argumento.
	 * @param id_reserva identificador da reserva
	 * @param estado novo estado da reserva
	 */
	public boolean setEstado(int id_reserva, int estado) throws Exception {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva == null){
			throw new Exception("A reserva não existe!");
		}
		reserva.setEstado(estado);
		reservaDAO.save(reserva);
		// TODO: trocar para void o return type
		return true;
	}

	/**
	 * Altera o custo da reserva para o passado por argumento.
	 * @param id_reserva identificador da reserva
	 * @param custo custo associado à reserva
	 */
	public boolean setCusto(int id_reserva, float custo) throws Exception {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva == null){
			throw new Exception("A reserva não existe!");
		}
		reserva.setCusto(custo);
		reservaDAO.save(reserva);
		// TODO: trocar para void o return type
		return true;
	}

	/**
	 * Altera o valor da variável pago da reserva para o valor passado por argumento.
	 * @param id_reserva identificador da reserva
	 * @param pago variável boleana que indica se a reserva já foi paga
	 */
	public void setPago(int id_reserva, boolean pago) throws Exception {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva == null){
			throw new Exception("A reserva não existe!");
		}
		reserva.setPago(pago);
		reservaDAO.save(reserva);
	}

	/**
	 * Altera o valor da data de início da reserva para o passado por argumento.
	 * @param id_reserva identificador da reserva
	 * @param data_inicio data de inicio da reserva
	 */
	public boolean setDataInicio(int id_reserva, LocalDateTime data_inicio) throws Exception {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva == null){
			throw new Exception("A reserva não existe!");
		}
		reserva.setData_inicio(data_inicio);
		reservaDAO.save(reserva);
		// TODO: trocar para void o return type
		return true;
	}

	/**
	 * Altera o valor da data de fim da reserva para o passado por argumento.
	 * @param id_reserva identificador da reserva
	 * @param data_fim data de fim da reserva
	 */
	public boolean setDataFim(int id_reserva, LocalDateTime data_fim) throws Exception {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva == null){
			throw new Exception("A reserva não existe!");
		}
		reserva.setData_fim(data_fim);
		reservaDAO.save(reserva);
		// TODO: trocar para void o return type
		return true;
	}

	/**
	 * Altera os valores das variáveis da reserva para os passados por argumento.
	 * @param id_reserva identificador da reserva
	 * @param estado estado da reserva
	 * @param pago estado de pagamento da reserva
	 * @param custo custo da reserva
	 * @param dataInicio data de início da reserva
	 * @param dataFim data de fim da reserva
	 * @param matricula matrícula do carro associado à reserva
	 */
	public boolean setAll(int id_reserva, Integer estado, Boolean pago, Float custo, LocalDateTime dataInicio, LocalDateTime dataFim, String matricula) throws Exception {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva == null){
			throw new Exception("A reserva não existe!");
		}
		reserva.setEstado(estado);
		reserva.setPago(pago);
		reserva.setCusto(custo);
		reserva.setData_inicio(dataInicio);
		reserva.setData_fim(dataFim);
		reserva.setMatricula(matricula);
		reservaDAO.save(reserva);
		// TODO: trocar para void o return type
		return true;
	}

	/**
	 * Altera o valor da variável matrícula da reserva para o passado por argumento.
	 * @param id_reserva identificador da reserva
	 * @param matricula matrícula do carro que se pretende associar à reserva
	 */
	public void setMatricula(int id_reserva, String matricula) throws Exception {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva == null){
			throw new Exception("Reserva não existe!");
		}
		reserva.setMatricula(matricula);
		reservaDAO.save(reserva);
	}

	/**
	 * Retorna a lista de
	 * @param id_user identificador do utilizador
	 */
	public List<Reserva> listarReservas(int id_user) {
		// TODO - Não é equivalente à função getReservas() ?
		throw new UnsupportedOperationException();
	}

}