package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pt.uminho.di.aa.parkfinder.logicaParques.DTOs.ParqueDTO;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueServiceBean;
import pt.uminho.di.aa.parkfinder.logicaParques.model.*;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.DAOs.GestorDAO;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorServiceBean;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
@SessionScope
public class GestorServiceBean implements GestorService {

	private final ParqueServiceBean parqueServiceBean;
	private final UtilizadorServiceBean utilizadorServiceBean;
	private final GestorDAO gestorDAO;
	private Gestor gestor;

	@Autowired
	public GestorServiceBean(ParqueServiceBean parqueServiceBean, UtilizadorServiceBean utilizadorServiceBean, GestorDAO gestorDAO) {
		this.parqueServiceBean = parqueServiceBean;
		this.utilizadorServiceBean = utilizadorServiceBean;
		this.gestorDAO = gestorDAO;
	}

	/**
	 * Função que retorna as estatísticas de determinado parque.
	 * @param id_parque identificador do parque
	 */
	public Estatisticas verEstatisticasParque(int id_parque) throws Exception {
		checkIsLoggedIn();
		return parqueServiceBean.getEstatisticasParque(id_parque);
	}

	/**
	 * Adiciona o precário passado por argumento ao parque escolhido
	 * @param id_parque identificador do parque
	 * @param tipoLugar tipo do lugar
	 * @param precario o precário a aplicar ao tipo de lugar
	 */
	public void adicionarPrecario(int id_parque, TipoLugarEstacionamento tipoLugar, Precario precario) throws Exception {
		checkIsLoggedIn();
		if (precario.getTipo() == null)
			precario.setTipo(tipoLugar);
		if (!precario.getTipo().getNome().equals(tipoLugar.getNome()))
			throw new Exception("O tipo de lugar passado é diferente do tipo associado ao precário!");
		parqueServiceBean.criarPrecario(id_parque,precario);
	}

	/**
	 * Devolve a lista dos parques associados ao gestor.
	 */
	public List<Parque> listarMeusParques() throws Exception {
		checkIsLoggedIn();
		return new ArrayList<>(gestor.getParques());
	}

	/**
	 * Remove um precário do parque.
	 * @param id_parque identificador do parque
	 * @param tipoLugar tipo do lugar a que o precário se estava a aplicar
	 */
	public void removerPrecario(int id_parque, TipoLugarEstacionamento tipoLugar) throws Exception {
		checkIsLoggedIn();
		parqueServiceBean.removerPrecario(id_parque,tipoLugar);
	}

	/**
	 * Devolve a lista de administradores associados a um gestor
	 */
	public List<Administrador> listarMeusAdministradores() throws Exception {
		checkIsLoggedIn();
		return new ArrayList<>(gestor.getAdmins());
	}

	/**
	 * Cria um administrador com os seus respetivos parques
	 * @param nome
	 * @param email
	 * @param password
	 * @param nrTelemovel
	 * @param ids_parques identificadores dos parques a atribuir ao administrador
	 */
	public void criarAdmin(String nome, String email, String password, int nrTelemovel, List<Integer> ids_parques) throws Exception {
		checkIsLoggedIn();

		Utilizador u = utilizadorServiceBean.getUtilizador(email);
		if(u != null)
			throw new Exception("Já existe um utilizador com este email!");

		gestor = gestorDAO.findById(gestor.getId()).orElse(null);
		Set<Parque> parquesAdmin = new HashSet<>(parqueServiceBean.listarParques(ids_parques));
		Administrador a = new Administrador(nome, email, password,
											nrTelemovel, gestor,
											parquesAdmin);

		utilizadorServiceBean.criarUtilizador(a);
	}

	/**
	 * Remove o administrador da base de dados.
	 * @param id_admin identificador do administrador
	 */
	public void removerAdmin(int id_admin) throws Exception {
		checkIsLoggedIn();
		Administrador a = (Administrador) utilizadorServiceBean.getUtilizador(id_admin);
		if (a == null)
			throw new Exception("O identificador de administrador não se encontra na base de dados.");
		if (!a.getDiscriminator().equals("Admin"))
			throw new Exception("O utilizador não é um administrador.");
		utilizadorServiceBean.removerUtilizador(id_admin);
	}

	/**
	 * Remove as permições que o administrador tinha para alterar os parques passados por argumento.
	 * @param id_admin identificador do administrador
	 * @param ids_parques lista com os identificadores do paruqe a serem removidos do adinistrador
	 */
	public void removerPermissaoAdminSobreParques(int id_admin, List<Integer> ids_parques) throws Exception {
		checkIsLoggedIn();
		Administrador administrador = (Administrador) utilizadorServiceBean.getUtilizador(id_admin);
		if (administrador == null)
			throw new Exception("O administrador não existe.");
		if (!administrador.getDiscriminator().equals("Admin"))
			throw new Exception("O utilizador não é um administrador.");
		var parques_administrador = administrador.getParques();
		Set<Parque> parques_remove = new HashSet<>(parqueServiceBean.listarParques(ids_parques));
		parques_remove.forEach(parques_administrador::remove);
		gestor.setParques(parques_administrador);
		utilizadorServiceBean.atualizarUtilizador(administrador);
	}

	/**
	 * Função que permite a atualização da informação do parque.
	 * @param id_parque identificador do parque
	 * @param newInfo nova informação relativa ao parque
	 */
	public boolean alterarInformacoesParque(int id_parque, ParqueDTO newInfo) throws Exception {
		checkIsLoggedIn();
		return parqueServiceBean.setAll(id_parque,newInfo.getNome(),
								newInfo.getDescricao(), newInfo.getMorada(), newInfo.getLatitude(),
								newInfo.getLongitude(),newInfo.getDisponivel(),
								newInfo.getCaminho_foto());
	}

	/**
	 * Função que permite alterar o estado de disponibilidade do parque
	 * @param id_parque identificador do parque
	 * @param disponivel valor boleano que representa a disponibilidade do parque
	 */
	public void alterarEstadoDisponivelDeParque(int id_parque, boolean disponivel) throws Exception {
		checkIsLoggedIn();
		parqueServiceBean.setDisponivel(id_parque, disponivel);
	}

	/**
	 * Adiciona os parques da lista de identificadores ao administrador.
	 * @param ids_parques lista com os identificadores dos parques a adicionar ao administrador
	 * @param id_admin ientificador do administrador
	 */
	public void adicionarParquesAAdmin(List<Integer> ids_parques, int id_admin) throws Exception {
		checkIsLoggedIn();
		Administrador administrador = (Administrador) utilizadorServiceBean.getUtilizador(id_admin);
		if (administrador == null)
			throw new Exception("O administrador não existe.");
		if (!administrador.getDiscriminator().equals("Admin"))
			throw new Exception("O utilizador não é um administrador.");
		var parques_administrador = administrador.getParques();
		Set<Parque> parques_novos = new HashSet<>(parqueServiceBean.listarParques(ids_parques));
		parques_administrador.addAll(parques_novos);
		administrador.setParques(parques_administrador);
		utilizadorServiceBean.atualizarUtilizador(administrador);
	}

	public void logout() throws Exception {
		checkIsLoggedIn();
		gestor = null;
	}

	/**
	 * Adiciona ou atualiza o horário do parque para o horário passado por argumento.
	 * @param id_parque identificador da parque
	 * @param horario novo horário do parque
	 */
	public boolean criarOuAtualizarHorario(int id_parque, Horario horario) throws Exception {
		if (horario == null)
			throw new Exception("O horário não pode ser nulo!");
		parqueServiceBean.setHorario(id_parque, horario);
		return true;
	}

    public void setGestor(Utilizador u) {
		this.gestor = (Gestor) u;
    }

	private void checkIsLoggedIn() throws Exception {
		if(gestor == null)
			throw new Exception("Não tem sessão iniciada.");
	}
}