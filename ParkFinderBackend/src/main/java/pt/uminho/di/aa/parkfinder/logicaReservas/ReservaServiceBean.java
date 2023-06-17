package pt.uminho.di.aa.parkfinder.logicaReservas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ReservaServiceBean implements ReservaService {

	private ReservaDAO reservaDAO;

	@Autowired
	public ReservaServiceBean(ReservaDAO reservaDAO) {
		this.reservaDAO = reservaDAO;
	}

	/**
	 * Adiciona uma reserva à base de dados.
	 * @param r
	 */
	public Reserva criarReserva(Reserva r) {
		r.setId(0);
		return reservaDAO.save(r);
	}

	/**
	 * Remove uma reserva existente da base de dados.
	 * @param id_reserva
	 */
	public void removerReserva(int id_reserva) {
		if (!reservaDAO.existsById(id_reserva)){
			//throw new Exception("Reserva não existe!");
		}
		reservaDAO.deleteById(id_reserva);
	}

	public Reserva getReserva(int id_reserva) throws Exception {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if(reserva == null)
			throw new Exception("A reserva não existe!");
		return reserva;
	}

	public boolean updateReserva(Reserva reserva) throws Exception {
		if (reserva == null)
			throw new Exception("A reserva não pode ser nula!");
		Reserva reservaold = reservaDAO.findById(reserva.getId()).orElse(null);
		if (reservaold == null)
			throw new Exception("A reserva não existe!");
		reservaDAO.save(reserva);
		return true;
	}

	/**
	 * Devolve a lista com as reservas do utilizador com o identificador especificado.
	 * @param id_user
	 */
	public List<Reserva> getReservas(int id_user) {
		return reservaDAO.findAllByUtilizadorId(id_user);
	}

	/**
	 * Altera o estado da reserva para o passado por argumento.
	 * @param id_reserva
	 * @param estado
	 */
	public boolean setEstado(int id_reserva, int estado) {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		reserva.setEstado(estado);
		reservaDAO.save(reserva);
		// TODO: trocar para void o return type
		return true;
	}

	/**
	 * Altera o custo da reserva para o passado por argumento.
	 * @param id_reserva
	 * @param custo
	 */
	public boolean setCusto(int id_reserva, float custo) {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		reserva.setCusto(custo);
		reservaDAO.save(reserva);
		// TODO: trocar para void o return type
		return true;
	}

	/**
	 * Altera o valor da variável pago da reserva para o valor passado por argumento.
	 * @param id_reserva
	 * @param pago
	 */
	public void setPago(int id_reserva, boolean pago) {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		reserva.setPago(pago);
		reservaDAO.save(reserva);
	}

	/**
	 * Altera o valor da data de início da reserva para o passado por argumento.
	 * @param id_reserva
	 * @param data_inicio
	 */
	public boolean setDataInicio(int id_reserva, java.util.Date data_inicio) {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		reserva.setData_inicio(data_inicio);
		reservaDAO.save(reserva);
		// TODO: trocar para void o return type
		return true;
	}

	/**
	 * Altera o valor da data de fim da reserva para o passado por argumento.
	 * @param id_reserva
	 * @param data_fim
	 */
	public boolean setDataFim(int id_reserva, java.util.Date data_fim) {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		reserva.setData_fim(data_fim);
		reservaDAO.save(reserva);
		// TODO: trocar para void o return type
		return true;
	}

	/**
	 * Altera os valores das variáveis da reserva para os passados por argumento.
	 * @param id_reserva
	 * @param estado
	 * @param pago
	 * @param custo
	 * @param dataInicio
	 * @param dataFim
	 * @param matricula
	 */
	public boolean setAll(int id_reserva, Integer estado, Boolean pago, Float custo, java.util.Date dataInicio, java.util.Date dataFim, String matricula) {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
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
	 * @param id_reserva
	 * @param matricula
	 */
	public void setMatricula(int id_reserva, String matricula) {
		Reserva reserva = reservaDAO.findById(id_reserva).orElse(null);
		if (reserva.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		reserva.setMatricula(matricula);
		reservaDAO.save(reserva);
	}

	/**
	 * 
	 * @param id_user
	 */
	public List<Reserva> listarReservas(int id_user) {
		// TODO - Não é equivalente à função getReservas() ?
		throw new UnsupportedOperationException();
	}

}