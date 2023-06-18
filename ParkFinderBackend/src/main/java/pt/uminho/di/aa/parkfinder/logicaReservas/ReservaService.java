package pt.uminho.di.aa.parkfinder.logicaReservas;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaService {

	/**
	 * 
	 * @param r
	 */
	Reserva criarReserva(Reserva r) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 */
	void removerReserva(int id_reserva) throws Exception;

	/**
	 *
	 * @param id_reserva
	 */
	public Reserva getReserva(int id_reserva) throws Exception;

	/**
	 *
	 * @param reserva
	 */
	public boolean updateReserva(Reserva reserva) throws Exception;

	/**
	 * 
	 * @param id_user
	 */
	List<Reserva> getReservas(int id_user);

	/**
	 * 
	 * @param id_reserva
	 * @param estado
	 */
	boolean setEstado(int id_reserva, int estado) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 * @param custo
	 */
	boolean setCusto(int id_reserva, float custo) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 * @param pago
	 */
	void setPago(int id_reserva, boolean pago) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 * @param data_inicio
	 */
	boolean setDataInicio(int id_reserva, LocalDateTime data_inicio) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 * @param data_fim
	 */
	boolean setDataFim(int id_reserva, LocalDateTime data_fim) throws Exception;

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
	boolean setAll(int id_reserva, Integer estado, Boolean pago, Float custo, LocalDateTime dataInicio, LocalDateTime dataFim, String matricula) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 * @param matricula
	 */
	void setMatricula(int id_reserva, String matricula) throws Exception;

	/**
	 * 
	 * @param id_user
	 */
	List<Reserva> listarReservas(int id_user);
}