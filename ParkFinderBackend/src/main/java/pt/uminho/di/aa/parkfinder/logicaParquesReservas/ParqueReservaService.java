package pt.uminho.di.aa.parkfinder.logicaParquesReservas;

public interface ParqueReservaService {

	/**
	 * 
	 * @param id_user
	 * @param id_parque
	 */
	Reserva criarReservaInstantanea(int id_user, int id_parque);

	/**
	 * 
	 * @param id_user
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	Reserva criarReservaAgendada(int id_user, int id_parque, TipoLugarEstacionamento tipo, java.util.Date data_inicio, java.util.Date data_fim);

	/**
	 * 
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	int getIdLugarDisponivel(int id_parque, TipoLugarEstacionamento tipo, java.util.Date data_inicio, java.util.Date data_fim);

	/**
	 * 
	 * @param id_reserva
	 * @param matricula
	 */
	boolean marcarEntradaParque(int id_reserva, String matricula);

	/**
	 * 
	 * @param id_reserva
	 */
	boolean marcarSaidaParque(int id_reserva);

	/**
	 * 
	 * @param id_reserva
	 */
	boolean pagarReserva(int id_reserva);
}