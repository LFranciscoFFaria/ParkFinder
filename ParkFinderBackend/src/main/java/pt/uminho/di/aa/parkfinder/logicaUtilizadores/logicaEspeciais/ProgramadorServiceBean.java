package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Estatisticas;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Programador;

import java.util.List;


@Component
@SessionScope
public class ProgramadorServiceBean implements ProgramadorService {

	private Programador programador;

	/**
	 * 
	 * @param g
	 * @param ids_parques
	 */
	public void criarGestor(Gestor g, List<Integer> ids_parques) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_gestor
	 */
	public void removerGestor(int id_gestor) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ids_parques
	 * @param id_gestor
	 */
	public void adicionarParquesAGestor(List<Integer> ids_parques, int id_gestor) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ids_parques
	 * @param id_gestor
	 */
	public void removerParquesAGestor(List<Integer> ids_parques, int id_gestor) {
		//TODO
		throw new UnsupportedOperationException();
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
	public List<Gestor>  procurarGestor(String nome) {
		// TODO - implement ProgramadorService.procurarGestor
		throw new UnsupportedOperationException();
	}

	public List<Gestor>  listarGestores() {
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