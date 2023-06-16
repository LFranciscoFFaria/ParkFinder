package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class AdministradorServiceBean implements AdministradorService {

	/**
	 * 
	 * @param N
	 * @param id_parque
	 */
	public void addLugarInstantaneo(int N, int id_parque) {

	}

	/**
	 * 
	 * @param N
	 * @param id_parque
	 * @param tipo
	 */
	public void addLugarEspecial(int N, int id_parque, TipoLugarEstacionamento tipo) {

	}

	/**
	 * 
	 * @param N
	 * @param id_parque
	 */
	public void removerLugarInstantaneo(int N, int id_parque) {

	}

	/**
	 * 
	 * @param N
	 * @param id_parque
	 * @param tipo
	 */
	public void removerLugarEspecial(int N, int id_parque, TipoLugarEstacionamento tipo) {

	}

	/**
	 * 
	 * @param matricula
	 */
	public Reserva encontrarReservaPorMatricula(String matricula) {

	}

	/**
	 * 
	 * @param id_reserva
	 * @param matricula
	 */
	public boolean associarMatriculaAReserva(String id_reserva, String matricula) {

	}

	/**
	 * 
	 * @param id_parque
	 */
	public Reserva[] verReservasAtivasDeParque(int id_parque) {

	}

	public void logout() {
		// TODO - implement AdministradorService.logout
		throw new UnsupportedOperationException();
	}

}