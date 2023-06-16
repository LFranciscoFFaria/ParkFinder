package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

public interface ProgramadorService {

	/**
	 * 
	 * @param g
	 * @param ids_parques
	 */
	void criarGestor(Gestor g, int[] ids_parques);

	/**
	 * 
	 * @param id_gestor
	 */
	void removerGestor(int id_gestor);

	/**
	 * 
	 * @param ids_parques
	 * @param id_gestor
	 */
	void adicionarParquesAGestor(int[] ids_parques, int id_gestor);

	/**
	 * 
	 * @param ids_parques
	 * @param id_gestor
	 */
	void removerParquesAGestor(int[] ids_parques, int id_gestor);

	/**
	 * 
	 * @param p
	 */
	void registarParque(Parque p);

	/**
	 * 
	 * @param p
	 */
	void removerParque(Parque p);

	/**
	 * 
	 * @param nome
	 */
	Gestor[] procurarGestor(String nome);

	Gestor[] listarGestores();

	Estatisticas verEstatisticasGerais();

	void logout();
}