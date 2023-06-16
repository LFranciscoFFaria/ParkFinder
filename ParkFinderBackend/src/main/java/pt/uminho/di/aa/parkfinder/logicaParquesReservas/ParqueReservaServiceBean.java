package pt.uminho.di.aa.parkfinder.logicaParquesReservas;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ParqueReservaServiceBean implements ParqueReservaService {

	/**
	 * 
	 * @param id_user
	 * @param id_parque
	 */
	public Reserva criarReservaInstantanea(int id_user, int id_parque) {

	}

	/**
	 * 
	 * @param id_user
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	public Reserva criarReservaAgendada(int id_user, int id_parque, TipoLugarEstacionamento tipo, java.util.Date data_inicio, java.util.Date data_fim) {

	}

	/**
	 * 
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	private int getIdLugarDisponivel(int id_parque, TipoLugarEstacionamento tipo, java.util.Date data_inicio, java.util.Date data_fim) {

	}

	/**
	 * 
	 * @param id_reserva
	 * @param matricula
	 */
	public boolean marcarEntradaParque(int id_reserva, String matricula) {

	}

	/**
	 * 
	 * @param id_reserva
	 */
	public boolean marcarSaidaParque(int id_reserva) {

	}

	/**
	 * 
	 * @param id_reserva
	 */
	public boolean pagarReserva(int id_reserva) {

	}

}