package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;

import java.util.List;


@Component
@SessionScope
public class AdministradorServiceBean implements AdministradorService {

	private Administrador administrador = null;

	/**
	 * 
	 * @param N
	 * @param id_parque
	 */
	public void addLugarInstantaneo(int N, int id_parque) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param N
	 * @param id_parque
	 * @param tipo
	 */
	public void addLugarEspecial(int N, int id_parque, TipoLugarEstacionamento tipo) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param N
	 * @param id_parque
	 */
	public void removerLugarInstantaneo(int N, int id_parque) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param N
	 * @param id_parque
	 * @param tipo
	 */
	public void removerLugarEspecial(int N, int id_parque, TipoLugarEstacionamento tipo) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param matricula
	 */
	public Reserva encontrarReservaPorMatricula(String matricula) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_reserva
	 * @param matricula
	 */
	public boolean associarMatriculaAReserva(String id_reserva, String matricula) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 */
	public List<Reserva> verReservasAtivasDeParque(int id_parque) {
		//TODO
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement AdministradorService.logout
		throw new UnsupportedOperationException();
	}

    public void setAdministrador(Utilizador u) {
		this.administrador = (Administrador) u;
    }
}