package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import pt.uminho.di.aa.parkfinder.logicaParques.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;

import java.util.List;

public interface AdministradorService {

	/**
	 * 
	 * @param N
	 * @param id_parque
	 */
	void addLugarInstantaneo(int N, int id_parque);

	/**
	 * 
	 * @param N
	 * @param id_parque
	 * @param tipo
	 */
	void addLugarEspecial(int N, int id_parque, TipoLugarEstacionamento tipo);

	/**
	 * 
	 * @param N
	 * @param id_parque
	 */
	void removerLugarInstantaneo(int N, int id_parque);

	/**
	 * 
	 * @param N
	 * @param id_parque
	 * @param tipo
	 */
	void removerLugarEspecial(int N, int id_parque, TipoLugarEstacionamento tipo);

	/**
	 * 
	 * @param matricula
	 */
	Reserva encontrarReservaPorMatricula(String matricula);

	/**
	 * 
	 * @param id_reserva
	 * @param matricula
	 */
	boolean associarMatriculaAReserva(String id_reserva, String matricula);

	/**
	 * 
	 * @param id_parque
	 */
	List<Reserva> verReservasAtivasDeParque(int id_parque);

	void logout();
}