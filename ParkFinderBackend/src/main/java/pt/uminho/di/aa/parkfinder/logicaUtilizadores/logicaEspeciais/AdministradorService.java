package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import pt.uminho.di.aa.parkfinder.api.DTOs.ReservaDTO;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.util.List;

public interface AdministradorService {

	/**
	 * 
	 * @param N
	 * @param id_parque
	 */
	void addLugarInstantaneo(int id_parque, int N) throws Exception;

	/**
	 * 
	 * @param N
	 * @param id_parque
	 * @param tipo
	 */
	void addLugarEspecial(int id_parque, int N, TipoLugarEstacionamento tipo) throws Exception;

	/**
	 * 
	 * @param N
	 * @param id_parque
	 */
	void removerLugarInstantaneo(int id_parque, int N) throws Exception;

	/**
	 * 
	 * @param N
	 * @param id_parque
	 * @param tipo
	 */
	void removerLugarEspecial(int id_parque, int N, TipoLugarEstacionamento tipo) throws Exception;

	/**
	 * 
	 * @param matricula
	 */
	ReservaDTO encontrarReservaPorMatricula(String matricula) throws Exception;

	/**
	 * 
	 * @param id_reserva
	 * @param matricula
	 */
	boolean associarMatriculaAReserva(int id_reserva, String matricula) throws Exception;

	/**
	 * 
	 * @param id_parque
	 */
	List<ReservaDTO> verReservasAtivasDeParque(int id_parque);

	void logout();
}