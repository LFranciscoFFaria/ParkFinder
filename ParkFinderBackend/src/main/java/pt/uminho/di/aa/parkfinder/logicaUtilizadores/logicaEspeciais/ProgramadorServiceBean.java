package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class ProgramadorServiceBean implements ProgramadorService {

	private Programador programador;

	/**
	 * 
	 * @param g
	 * @param ids_parques
	 */
	public void criarGestor(Gestor g, int[] ids_parques) {

	}

	/**
	 * 
	 * @param id_gestor
	 */
	public void removerGestor(int id_gestor) {

	}

	/**
	 * 
	 * @param ids_parques
	 * @param id_gestor
	 */
	public void adicionarParquesAGestor(int[] ids_parques, int id_gestor) {

	}

	/**
	 * 
	 * @param ids_parques
	 * @param id_gestor
	 */
	public void removerParquesAGestor(int[] ids_parques, int id_gestor) {

	}

	/**
	 * 
	 * @param p
	 */
	public void registarParque(Parque p) {
		// TODO - implement ProgramadorService.registarParque
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param p
	 */
	public void removerParque(Parque p) {
		// TODO - implement ProgramadorService.removerParque
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 */
	public Gestor[] procurarGestor(String nome) {
		// TODO - implement ProgramadorService.procurarGestor
		throw new UnsupportedOperationException();
	}

	public Gestor[] listarGestores() {
		// TODO - implement ProgramadorService.listarGestores
		throw new UnsupportedOperationException();
	}

	public Estatisticas verEstatisticasGerais() {
		// TODO - implement ProgramadorService.verEstatisticasGerais
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement ProgramadorService.logout
		throw new UnsupportedOperationException();
	}

}