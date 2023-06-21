package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueServiceBean;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Estatisticas;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Programador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorServiceBean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
@SessionScope
public class ProgramadorServiceBean implements ProgramadorService {

	private final UtilizadorServiceBean utilizadorServiceBean;
	private final ParqueServiceBean parqueServiceBean;
	private Programador programador;

	public ProgramadorServiceBean(UtilizadorServiceBean utilizadorServiceBean, ParqueServiceBean parqueServiceBean) {
		this.utilizadorServiceBean = utilizadorServiceBean;
		this.parqueServiceBean = parqueServiceBean;
	}

	/**
	 * Adiciona um gestor associado aos parques cujos identificadores foram passados por argumento à base de dados.
	 * @param g objeto que representa o gestor que vai ser criado
	 * @param ids_parques lista com os identificadores dos parques que vão ser atribuidos ao gestor
	 */
	public void criarGestor(Gestor g, List<Integer> ids_parques) throws Exception {
		checkIsLoggedIn();
		if (g == null)
			throw new Exception("O gestor não pode ser nulo.");
		Utilizador u = utilizadorServiceBean.getUtilizador(g.getEmail());
		if (u == null){
			Set<Parque> parques = new HashSet<>(parqueServiceBean.listarParques(ids_parques));
			g.setParques(parques);
			g.setDiscriminator("Gestor");
			utilizadorServiceBean.criarUtilizador(g);
		}
		else
			throw new Exception("O gestor com este identificador já existe na base de dados.");
	}

	/**
	 * Remove o gestor com o identificador passado por argumento da base de dados.
	 * @param id_gestor identificador do gestor a ser removido da base de dados
	 */
	public void removerGestor(int id_gestor) throws Exception {
		checkIsLoggedIn();
		Gestor g = (Gestor) utilizadorServiceBean.getUtilizador(id_gestor);
		if (g == null)
			throw new Exception("O identificador do gestor não se encontra na base de dados.");
		if (!g.getDiscriminator().equals("Gestor"))
			throw new Exception("O utilizador não é um gestor.");
		utilizadorServiceBean.removerUtilizador(id_gestor);
	}

	/**
	 * Adicona os parques da lista de identificadores passada por argumento aos paruqes que o gestor já possuia.
	 * @param ids_parques lista com os identificadores dos parques que o gestor vai receber
	 * @param id_gestor identificador do gestor que vai receber mais parques
	 */
	public void adicionarParquesAGestor(List<Integer> ids_parques, int id_gestor) throws Exception {
		checkIsLoggedIn();
		Gestor gestor = (Gestor) utilizadorServiceBean.getUtilizador(id_gestor);
		if (gestor == null)
			throw new Exception("O gestor não existe.");
		if (!gestor.getDiscriminator().equals("Gestor"))
			throw new Exception("O utilizador não é um gestor.");
		var parques_novos = new HashSet<>(parqueServiceBean.listarParques(ids_parques));
		Set<Parque> parques_gestor = new HashSet<Parque>(gestor.getParques());
		parques_gestor.addAll(parques_novos);
		gestor.setParques(parques_gestor);
		utilizadorServiceBean.atualizarUtilizador(gestor);
	}

	/**
	 * Remove os parques da lista de identificadores passada por argumento aos paruqes que o gestor já possuia.
	 * @param ids_parques lista com os identificadores dos parques que o gestor vai perder permissões para usar
	 * @param id_gestor identificador do gestor
	 */
	public void removerParquesAGestor(List<Integer> ids_parques, int id_gestor) throws Exception {
		checkIsLoggedIn();
		Gestor gestor = (Gestor) utilizadorServiceBean.getUtilizador(id_gestor);
		if (gestor == null)
			throw new Exception("O gestor não existe.");
		if (!gestor.getDiscriminator().equals("Gestor"))
			throw new Exception("O utilizador não é um gestor.");
		var parques_gestor = gestor.getParques();
		Set<Parque> parques_remove = new HashSet<>(parqueServiceBean.listarParques(ids_parques));
		parques_remove.forEach(parques_gestor::remove);
		gestor.setParques(parques_gestor);
		utilizadorServiceBean.atualizarUtilizador(gestor);
	}

	/**
	 * Função que regista um parque na base de dados.
	 * @param p parque a ser adicionado à base de dados
	 */
	public void registarParque(Parque p) throws Exception {
		checkIsLoggedIn();
		Parque parque = parqueServiceBean.procurarParque(p.getId());
		if (parque != null)
			throw new Exception("O parque já existe na base de dados.");
		parqueServiceBean.criarParque(p);
	}

	/**
	 * Função que remove um parque na base de dados.
	 *
	 * @param id_parque parque a ser removido da base de dados
	 */
	public void removerParque(int id_parque) throws Exception {
		checkIsLoggedIn();
		Parque parque = parqueServiceBean.procurarParque(id_parque);
		if (parque == null)
			throw new Exception("O parque não existe.");
		parqueServiceBean.removerParque(id_parque);
	}

	/**
	 * Procura o gestor pelo seu nome na base de dados.
	 * @param nome nome do gestor a procurar
	 * @return Retorna a lista de gestores com o nome especificado.
	 */
	@SuppressWarnings("unchecked")
	public List<Gestor>  procurarGestor(String nome) throws Exception {
		checkIsLoggedIn();
		return (List<Gestor>) (List<?>) utilizadorServiceBean.procurarUtilizador(nome, "Gestor");
	}

	/**
	 * Retorna a lista de todos os gestores encontrados na base de dados.
	 */
	@SuppressWarnings("unchecked")
	public List<Gestor>  listarGestores() throws Exception {
		checkIsLoggedIn();
		return (List<Gestor>) (List<?>) utilizadorServiceBean.procurarUtilizadores("Gestor");
	}

	/**
	 * Retorna as estatisticas gerais agregadas de todos os parques da aplicação.
	 */
	public Estatisticas verEstatisticasGerais() throws Exception {
		checkIsLoggedIn();
		return parqueServiceBean.getEstatisticasGeralAgregado();
	}

	/**
	 * Função que realiza o logout do programamdor.
	 */
	public void logout() throws Exception {
		checkIsLoggedIn();
		programador = null;
	}

	private void checkIsLoggedIn() throws Exception {
		if(programador == null)
			throw new Exception("Não tem sessão iniciada.");
	}

    public void setProgramador(Utilizador u) {
    	this.programador = (Programador) u;
	}
}