package pt.uminho.di.aa.parkfinder.logicaReservas;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
	 * @param matricula
	 */
	public Reserva getReservaMatricula(int parque_id, String matricula) throws Exception;

	/**
	 *
	 * @param id_parque
	 */
	public List<Reserva> getReservasParque(int id_parque);

	/**
	 *
	 * @param reserva
	 */
	//public boolean updateReserva(Reserva reserva) throws Exception;

	/**
	 * @param id_reserva
	 * @param estado
	 */
	void setEstado(int id_reserva, int estado) throws Exception;

	/**
	 * @param id_reserva
	 * @param custo
	 */
	void setCusto(int id_reserva, float custo) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 * @param pago
	 */
	void setPago(int id_reserva, boolean pago) throws Exception;

	/**
	 * @param id_reserva
	 * @param data_inicio
	 */
	void setDataInicio(int id_reserva, LocalDateTime data_inicio) throws Exception;

	/**
     * @param id_reserva
     * @param data_fim
     */
	void setDataFim(int id_reserva, LocalDateTime data_fim) throws Exception;

	/**
     * @param id_reserva
     * @param estado
     * @param pago
     * @param custo
     * @param dataInicio
     * @param dataFim
     * @param matricula
     */
	Reserva setAll(int id_reserva, Optional<Integer> estado, Optional<Boolean> pago, Optional<Float> custo, Optional<LocalDateTime> dataInicio, Optional<LocalDateTime> dataFim, Optional<String> matricula) throws Exception;

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