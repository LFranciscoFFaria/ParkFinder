package pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UtilizadorServiceBean implements UtilizadorService {

	/**
	 * 
	 * @param email
	 */
	public Utilizador getUtilizador(String email) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param email
	 * @param password
	 */
	public Utilizador login(String email, String password) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param utilizador
	 */
	public Utilizador criarUtilizador(Utilizador utilizador) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_user
	 */
	public boolean removerUtilizador(int id_user) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param utilizador
	 */
	public boolean atualizarUtilizador(Utilizador utilizador) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 * @param descriminador
	 */
	public Utilizador[] procurarUtilizador(String nome, String descriminador) {
		// TODO - implement UtilizadorService.procurarUtilizador
		throw new UnsupportedOperationException();
	}

}