package pt.uminho.di.aa.parkfinder.logicaReservas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pt.uminho.di.aa.parkfinder.logicaParques.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueDAO;

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
	 * 
	 * @param r
	 */
	public Reserva criarReserva(Reserva r) {
		r.setId(0);
		return reservaDAO.save(r);
	}

	/**
	 * 
	 * @param id_reserva
	 */
	public void removerReserva(int id_reserva) {
		if (!reservaDAO.existsById(id_reserva)){
			//throw new Exception("Reserva não existe!");
		}
		reservaDAO.deleteById(id_reserva);
	}

	/**
	 * 
	 * @param id_user
	 */
	public List<Reserva> getReservas(int id_user) {
		//return reservaDAO.findAllByUtilizadorId(id_user);
		return null;
	}

	/**
	 * 
	 * @param id_reserva
	 * @param estado
	 */
	public boolean setEstado(int id_reserva, int estado) {

	}

	/**
	 * 
	 * @param id_reserva
	 * @param custo
	 */
	public boolean setCusto(int id_reserva, float custo) {

	}

	/**
	 * 
	 * @param id_reserva
	 * @param pago
	 */
	public void setPago(int id_reserva, boolean pago) {

	}

	/**
	 * 
	 * @param id_reserva
	 * @param data_inicio
	 */
	public boolean setDataInicio(int id_reserva, java.util.Date data_inicio) {

	}

	/**
	 * 
	 * @param id_reserva
	 * @param data_fim
	 */
	public boolean setDataFim(int id_reserva, java.util.Date data_fim) {

	}

	/**
	 * 
	 * @param id_reserva
	 * @param estado
	 * @param pago
	 * @param custo
	 * @param dataInicio
	 * @param dataFim
	 * @param matricula
	 */
	public boolean setAll(int id_reserva, Integer estado, Boolean pago, Float custo, java.util.Date dataInicio, java.util.Date dataFim, String matricula) {

	}

	/**
	 * 
	 * @param id_reserva
	 * @param matricula
	 */
	public void setMatricula(int id_reserva, String matricula) {

	}

	/**
	 * 
	 * @param id_user
	 */
	public List<Reserva> listarReservas(int id_user) {
		// TODO - implement ReservaService.listarReservas
		throw new UnsupportedOperationException();
	}

}