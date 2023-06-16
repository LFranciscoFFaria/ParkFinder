package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;

import java.util.List;


@Component
@SessionScope
public class CondutorServiceBean implements CondutorService {

	private Condutor condutor;

	/**
	 * 
	 * @param newCondutor
	 */
	public boolean editarPerfil(Condutor newCondutor) {
		//TODO
		throw new UnsupportedOperationException();
	}

	public List<Reserva> listarMinhasReservas() {
		// TODO - implement CondutorService.listarMinhasReservas
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 */
	public Reserva fazerReservaInstantanea(int id_parque) {
		// TODO - implement CondutorService.fazerReservaInstantanea
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	public Reserva fazerReservaAgendada(int id_parque, TipoLugarEstacionamento tipo, java.util.Date data_inicio, java.util.Date data_fim) {
		// TODO - implement CondutorService.fazerReservaAgendada
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_reserva
	 */
	public boolean pagarReserva(int id_reserva) {
		// TODO - implement CondutorService.pagarReserva
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement CondutorService.logout
		throw new UnsupportedOperationException();
	}

}