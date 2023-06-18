package pt.uminho.di.aa.parkfinder.logicaParquesReservas;

import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;

import java.time.LocalDateTime;
import java.util.List;

public interface ParqueReservaService {

	/**
	 * 
	 * @param id_user
	 * @param id_parque
	 */
	Reserva criarReservaInstantanea(int id_user, int id_parque) throws Exception;

	/**
	 * 
	 * @param id_user
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	Reserva criarReservaAgendada(int id_user, int id_parque, TipoLugarEstacionamento tipo, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	List<Integer> getIdsDeLugaresDisponiveis(int id_parque, TipoLugarEstacionamento tipo, LocalDateTime data_inicio, LocalDateTime data_fim);

	/**
	 * 
	 * @param id_reserva
	 * @param matricula
	 */
	boolean marcarEntradaParque(int id_reserva, String matricula) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 */
	boolean marcarSaidaParque(int id_reserva) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 */
	boolean pagarReserva(int id_reserva) throws Exception;
}