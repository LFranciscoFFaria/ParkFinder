package pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UtilizadorServiceBean implements UtilizadorService {

	private UtilizadorDAO utilizadorDAO;

	@Autowired
	public UtilizadorServiceBean(UtilizadorDAO utilizadorDAO) {
		this.utilizadorDAO = utilizadorDAO;
	}

	/**
	 * 
	 * @param email
	 */
	public Utilizador getUtilizador(String email) {
		return utilizadorDAO.findByEmail(email).orElse(null);
	}

	/**
	 * 
	 * @param email
	 * @param password
	 */
	public Utilizador login(String email, String password) {
		Utilizador utilizador = utilizadorDAO.findByEmail(email).orElse(null);
		if(utilizador != null && utilizador.getPassword().equals(password)){
			//TODO Logica de tipos de utilizador
		}

		return null;
	}

	/**
	 * 
	 * @param utilizador
	 */
	public Utilizador criarUtilizador(Utilizador utilizador) {
		utilizador.setId(0);
		return utilizadorDAO.save(utilizador);
	}

	/**
	 * 
	 * @param id_user
	 */

	// TODO: Considerar adicionar exceção
	public boolean removerUtilizador(int id_user) {
		if (!utilizadorDAO.existsById(id_user)){
			//throw new Exception("Utilizador não existe!");
			return false;
		}
		utilizadorDAO.deleteById(id_user);
		return true;
	}

	/**
	 * 
	 * @param utilizador
	 */
	public boolean atualizarUtilizador(Utilizador utilizador) {
		Utilizador u = utilizadorDAO.findById(utilizador.getId()).orElse(null);
		if (u.equals(null)){
			return false;
		}
		else {
			//TODO Fazer lógica de atualização
			return true;
		}
	}

	/**
	 * 
	 * @param nome
	 * @param descriminador
	 */
	public List<Utilizador> procurarUtilizador(String nome, String descriminador) {
		// TODO - Vai ser preciso fazer uma query

		throw new UnsupportedOperationException();
	}

}