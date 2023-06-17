package pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UtilizadorServiceBean implements UtilizadorService {

	private final UtilizadorDAO utilizadorDAO;

	@Autowired
	public UtilizadorServiceBean(UtilizadorDAO utilizadorDAO) {
		this.utilizadorDAO = utilizadorDAO;
	}

	/**
	 * Encontra utilizador pelo email.
	 * @param email -
	 */
	public Utilizador getUtilizador(String email) {
		return utilizadorDAO.findByEmail(email).orElse(null);
	}

	/**
	 * Se as credenciais estiverem corretas, retorna uma instância do utilizador.
	 * @param email -
	 * @param password -
	 */
	public Utilizador login(String email, String password) {
		Utilizador utilizador = utilizadorDAO.findByEmail(email).orElse(null);

		if(utilizador != null && utilizador.getPassword().equals(password))
			return utilizador;

		return null;
	}

	/**
	 * Cria um utilizador, e retorna a instância atualizada deste (com o id definido pela BD)
	 * @param utilizador instância que contém todos os dados a usar na criação de um utilizador.
	 */
	public Utilizador criarUtilizador(Utilizador utilizador) throws Exception {
		if(utilizador == null)
			throw new Exception("Utilizador não pode ser nulo.");

		String valido = utilizador.validarAtributos();
		if (valido != null) throw new Exception(valido);

		if(utilizadorDAO.findByEmail(utilizador.getEmail()).orElse(null) != null)
			throw new Exception("Já existe um utilizador a utilizar o email fornecido.");

		utilizador.setId(0);
		utilizador.setNotificacoes(null);
		utilizador.setReservas(null);
		return utilizadorDAO.save(utilizador);
	}

	/**
	 * Remove utilizador através do identificador.
	 * @param id_user identificador do utilizador
	 */
	public boolean removerUtilizador(int id_user) {
		if (!utilizadorDAO.existsById(id_user)) return false;
		utilizadorDAO.deleteById(id_user);
		return true;
	}

	/**
	 * Atualiza campos de utilizador.
	 * @param utilizador Instância de utilizador com todos os campos, i.e., deve incluir os campos atualizados e os que não foram atualizados.
	 */
	public Utilizador atualizarUtilizador(Utilizador utilizador) throws Exception {
		if(utilizador == null)
			throw new Exception("Utilizador não pode ser nulo.");

		String valido = utilizador.validarAtributos();
		if (valido != null) throw new Exception(valido);

		utilizador = utilizadorDAO.save(utilizador);

		return utilizador;
	}

	/**
	 * Procura por utilizadores com nome parecido com o fornecido.
	 * É necessário fornecer um descriminador válido para obter resultados.
	 * @param nome Nome do utilizador
	 * @param descriminador Tipo de utilizador
	 */
	public List<Utilizador> procurarUtilizador(String nome, String descriminador) {
		return utilizadorDAO.findUtilizadorByNomeContainingIgnoreCaseAndDiscriminator(nome, descriminador);
	}

}