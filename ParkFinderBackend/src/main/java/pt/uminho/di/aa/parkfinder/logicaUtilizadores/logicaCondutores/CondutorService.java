package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores;

import pt.uminho.di.aa.parkfinder.api.DTOs.CondutorEditDTO;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;

import java.time.LocalDateTime;
import java.util.List;

public interface CondutorService {

	/**
	 * 
	 * @param condutorDTO
	 */
	boolean editarPerfil(CondutorEditDTO condutorDTO) throws Exception;

	List<Reserva> listarMinhasReservas() throws Exception;

	/**
	 * 
	 * @param id_parque
	 */
	Reserva fazerReservaInstantanea(int id_parque) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param tipo_lugar
	 * @param data_inicio
	 * @param data_fim
	 */
	Reserva fazerReservaAgendada(int id_parque, String tipo_lugar, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 */
	boolean pagarReserva(int id_reserva) throws Exception;

	void logout() throws Exception;
}