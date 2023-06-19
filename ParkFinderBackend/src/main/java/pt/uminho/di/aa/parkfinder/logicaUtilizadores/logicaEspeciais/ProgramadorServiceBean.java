package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueServiceBean;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Estatisticas;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Programador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorServiceBean;

import java.util.ArrayList;
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
		if (g == null)
			throw new Exception("O gestor não pode ser nulo.");
		Utilizador u = utilizadorServiceBean.getUtilizador(g.getEmail());
		if (u == null){
			Set<Parque> parques = (Set<Parque>) parqueServiceBean.listarParques(ids_parques);
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
		Gestor gestor = (Gestor) utilizadorServiceBean.getUtilizador(id_gestor);
		if (gestor == null)
			throw new Exception("O gestor não existe.");
		if (!gestor.getDiscriminator().equals("Gestor"))
			throw new Exception("O utilizador não é um gestor.");
		var parques_novos = (Set<Parque>) parqueServiceBean.listarParques(ids_parques);
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
		Gestor gestor = (Gestor) utilizadorServiceBean.getUtilizador(id_gestor);
		if (gestor == null)
			throw new Exception("O gestor não existe.");
		if (!gestor.getDiscriminator().equals("Gestor"))
			throw new Exception("O utilizador não é um gestor.");
		var parques_gestor = gestor.getParques();
		Set<Parque> parques_remove = (Set<Parque>) parqueServiceBean.listarParques(ids_parques);
		parques_remove.forEach(parques_gestor::remove);
		gestor.setParques(parques_gestor);
		utilizadorServiceBean.atualizarUtilizador(gestor);
	}

	/**
	 * Função que regista um parque na base de dados.
	 * @param p parque a ser adicionado à base de dados
	 */
	public void registarParque(Parque p) throws Exception {
		Parque parque = parqueServiceBean.procurarParque(p.getId());
		if (parque != null)
			throw new Exception("O parque já existe na base de dados.");
		parqueServiceBean.criarParque(p);
	}

	/**
	 * Função que remove um parque na base de dados.
	 * @param p parque a ser removido da base de dados
	 */
	public void removerParque(Parque p) throws Exception {
		Parque parque = parqueServiceBean.procurarParque(p.getId());
		if (parque == null)
			throw new Exception("O parque não existe na base de dados.");
		parqueServiceBean.removerParque(p.getId());
	}

	/**
	 * Procura o gestor pelo seu nome na base de dados.
	 * @param nome nome do gestor a procurar
	 * @return Retorna a lista de gestores com o nome especificado.
	 */
	public List<Gestor>  procurarGestor(String nome) {
		//return utilizadorServiceBean.procurarUtilizador(nome, "gestor");
		throw new UnsupportedOperationException();
	}

	/**
	 * Retorna a lista de todos os gestores encontrados na base de dados.
	 */
	public List<Gestor>  listarGestores() {
		List<Utilizador> utilizadores = utilizadorServiceBean.procurarUtilizador("gestor");
		List<Gestor> gestores = new ArrayList<Gestor>();
		for (Utilizador utilizador:utilizadores){
			gestores.add((Gestor) utilizador);
		}
		return gestores;
	}

	/**
	 * Retorna as estatisticas gerais agregadas de todos os parques da aplicação.
	 */
	public Estatisticas verEstatisticasGerais() {
		// TODO: Pelo nome não sei se é suposto não serem as agregadas ou não
		return parqueServiceBean.getEstatisticasGeralAgregado();
	}

	/**
	 * Função que realiza o logout do programamdor.
	 */
	public void logout() {
		// TODO: No condutor está função retorna um boleano não sei qual é suposto ser
		programador = null;
	}

    public void setProgramador(Utilizador u) {
    	this.programador = (Programador) u;
	}
}