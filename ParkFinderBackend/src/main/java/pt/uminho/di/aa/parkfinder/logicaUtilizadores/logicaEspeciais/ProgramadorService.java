package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import pt.uminho.di.aa.parkfinder.logicaParques.model.Estatisticas;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;

import java.util.List;

public interface ProgramadorService {

	/**
	 * 
	 * @param g
	 * @param ids_parques
	 */
	void criarGestor(Gestor g, List<Integer> ids_parques) throws Exception;

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
	void adicionarParquesAGestor(List<Integer> ids_parques, int id_gestor) throws Exception;

	/**
	 * 
	 * @param ids_parques
	 * @param id_gestor
	 */
	void removerParquesAGestor(List<Integer> ids_parques, int id_gestor) throws Exception;

	/**
	 * 
	 * @param p
	 */
	void registarParque(Parque p) throws Exception;

	/**
	 * 
	 * @param p
	 */
	void removerParque(Parque p) throws Exception;

	/**
	 * 
	 * @param nome
	 */
	List<Gestor> procurarGestor(String nome);

	List<Gestor>  listarGestores();

	Estatisticas verEstatisticasGerais();

	void logout();
}