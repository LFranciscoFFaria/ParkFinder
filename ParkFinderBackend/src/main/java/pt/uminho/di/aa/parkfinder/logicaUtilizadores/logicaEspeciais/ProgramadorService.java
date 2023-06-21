package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import pt.uminho.di.aa.parkfinder.logicaParques.model.Estatisticas;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;

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
	void removerGestor(int id_gestor) throws Exception;

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
     * @param id_parque
     */
	void removerParque(int id_parque) throws Exception;

	/**
	 * 
	 * @param nome
	 */
	List<Gestor> procurarGestor(String nome) throws Exception;

	List<Gestor>  listarGestores() throws Exception;

	Estatisticas verEstatisticasGerais() throws Exception;

	void logout() throws Exception;

	void setProgramador(Utilizador utilizador);
}