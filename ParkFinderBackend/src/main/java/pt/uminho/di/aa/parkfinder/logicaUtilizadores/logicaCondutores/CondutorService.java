package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores;

import pt.uminho.di.aa.parkfinder.logicaParques.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;

import java.util.List;

public interface CondutorService {

	/**
	 * 
	 * @param newCondutor
	 */
	boolean editarPerfil(Condutor newCondutor);

	List<Reserva> listarMinhasReservas();

	/**
	 * 
	 * @param id_parque
	 */
	Reserva fazerReservaInstantanea(int id_parque);

	/**
	 * 
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	Reserva fazerReservaAgendada(int id_parque, TipoLugarEstacionamento tipo, java.util.Date data_inicio, java.util.Date data_fim);

	/**
	 * 
	 * @param id_reserva
	 */
	boolean pagarReserva(int id_reserva);

	void logout();
}