package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores;

import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;

import java.time.LocalDateTime;
import java.util.List;

public interface CondutorService {

	/**
	 * 
	 * @param newCondutor
	 */
	boolean editarPerfil(Condutor newCondutor) throws Exception;

	List<Reserva> listarMinhasReservas();

	/**
	 * 
	 * @param id_parque
	 */
	Reserva fazerReservaInstantanea(int id_parque) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	Reserva fazerReservaAgendada(int id_parque, TipoLugarEstacionamento tipo, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 */
	boolean pagarReserva(int id_reserva) throws Exception;

	boolean logout();
}