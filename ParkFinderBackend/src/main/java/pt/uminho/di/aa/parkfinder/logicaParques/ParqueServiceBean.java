package pt.uminho.di.aa.parkfinder.logicaParques;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pt.uminho.di.aa.parkfinder.logicaParques.DAOs.*;
import pt.uminho.di.aa.parkfinder.logicaParques.model.*;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ParqueServiceBean implements ParqueService {

	private final ParqueDAO parqueDAO;
	private final TipoLugarEstacionamentoDAO tipoLugarDAO;
	private final LugarEstacionamentoDAO lugarDAO;
	private final EstatisticasDAO estatisticasDAO;

	@Autowired
	public ParqueServiceBean(ParqueDAO parqueDAO, TipoLugarEstacionamentoDAO tipoLugarEstacionamentoDAO, LugarEstacionamentoDAO lugarEstacionamentoDAO, EstatisticasDAO estatisticasDAO) {
		this.parqueDAO = parqueDAO;
		this.tipoLugarDAO = tipoLugarEstacionamentoDAO;
		this.lugarDAO = lugarEstacionamentoDAO;
		this.estatisticasDAO = estatisticasDAO;
	}

	/**
	 * Devolve a lista de todos os parques registados na aplicação.
	 */
	public List<Parque> listarParques() {
		return parqueDAO.findAll();
	}

	/**
	 * @param pagina Número da página (começa em 1). No caso de um valor inválido, retorna a primeira página.
	 * @param nrResultadosPorPagina Número de itens por página.
	 * @return página de parques
	 */
	public Page<Parque> listarParques(int pagina, int nrResultadosPorPagina){
		pagina = Math.max(pagina, 1);
		Pageable page = PageRequest.of(pagina - 1, nrResultadosPorPagina);
		return parqueDAO.findAll(page);
	}

	/**
	 * Encontra todos os parques com identificadores válidos da lista passada por argumento.
	 * @param ids lista de ids de parque
	 */
	public List<Parque> listarParques(List<Integer> ids) {
		return parqueDAO.findAllById(ids);
	}

	/**
	 * Encontra todos os parques com o nome passado por argumento.
	 * Não é sensível à capitalização de carácteres.
	 * @param nome Nome do parque
	 */
	public List<Parque> procurarParque(String nome) {
		return parqueDAO.findAllByNomeContainingIgnoreCase(nome);
	}

	/**
	 * Encontra o parque pelo identificador se existir.
	 * @param id_parque identificador do parque
	 */
	public Parque procurarParque(int id_parque) {
		return parqueDAO.findById(id_parque).orElse(null);
	}

	/**
	 * Adiciona o parque à base de dados da aplicação.
	 * @param p Instância de parque que contém todas as informações necessárias para a sua criação
	 */
	@Transactional(rollbackOn = Exception.class)
	public Parque criarParque(Parque p) throws Exception  {
		if(p == null) {
			throw new Exception("O parque não pode ser nulo!");
		}
		p.setId(0);
		// Valida campos do parque
		String erro = verificarCamposParque(p);
		if(erro != null) throw new Exception(erro);
		// Inicia estatisticas do parque
		p.setEstatisticas(new Estatisticas());
		// Se o horario nao tiver sido definido, associa um.
		if(p.getHorario() == null) p.setHorario(new Horario());
		return parqueDAO.save(p);
	}

	private static String verificarCamposParque(Parque p){
		if(p.getNome() == null)
			return "O nome do parque não pode ser nulo!";
		if(p.getDescricao() == null)
			return "A descricao do parque não pode ser nula!";
		if (p.getLatitude() < -90.0f || p.getLatitude() > 90.0f)
			System.out.println("Latitude inválida");
		if (p.getLongitude() < -180.0f || p.getLongitude() > 180.0f)
			System.out.println("Longitude inválida");
		return null;
	}

	/**
	 * Remove o parque da base de dados da aplicação se existir.
	 * @param id_parque identificador do parque
	 */
	public void removerParque(int id_parque) {
		parqueDAO.deleteById(id_parque);
	}

	/**
	 * Adiciona um precario para um tipo de lugar de estacionamento. Se já existir um
	 * precário para esse tipo de lugar o antigo não é alterado e o novo não é adicionado.
	 * @param id_parque identificador do parque
	 * @param p Precario
	 */
	@Transactional(rollbackOn = Exception.class)
	public void criarPrecario(int id_parque, Precario p) throws Exception {
		if(p == null)
			throw new Exception("Precario não pode ser nulo!");

		Parque parque = getParque(id_parque);

		TipoLugarEstacionamento tipoLugar = p.getTipo();
		if(tipoLugar == null)
			throw new Exception("Precario precisa de ter um tipo de lugar associado");

		//Encontra ou persiste tipo lugar
		tipoLugar = encontraOuPersisteTipoLugar(tipoLugar);
		p.setTipo(tipoLugar);

		//Remove precario que contem o mesmo tipo do novo
		Set<Precario> precarios = parque.getPrecarios();
		precarios.removeIf(precario -> precario.equals(p));

		//Adiciona nodo precario à colecao
		precarios.add(p);
		parqueDAO.save(parque);
	}

	/**
	 * Remove um precário para um tipo de lugar de estacionamento específico.
	 * @param id_parque identificador do parque
	 * @param tipoPrecario tipo de lugar de estacionamento ao qual o precario corresponde
	 */
	public void removerPrecario(int id_parque, TipoLugarEstacionamento tipoPrecario) throws Exception {
		Parque parque = getParque(id_parque);

		// Se o tipo de precario nao existe, retorna, porque um parque não o pode ter associado
		TipoLugarEstacionamento tipoLugarComId = tipoLugarDAO.findByNome(tipoPrecario.getNome());
		if(tipoLugarComId == null) return;

		Set<Precario> precarios = parque.getPrecarios();
		precarios.removeIf(precario -> precario.getTipo().equals(tipoPrecario));
		parqueDAO.save(parque);
	}

	/**
	 * Retorna a lista de precarios do parque indicado.
	 * @param id_parque identificador do parque
	 */
	public List<Precario> getPrecarios(int id_parque) throws Exception {
		Parque parque = getParque(id_parque);
		return new ArrayList<>(parque.getPrecarios());
	}

	/**
	 * Calcula o custo de fazer determinada reserva no parque pretendido para um determinado periodo.
	 * @param id_parque identificador do parque
	 * @param id_lugar identificador do lugar
	 * @param data_inicio data de inicio do periodo
	 * @param data_fim data de fim do periodo
	 */
	@Transactional(rollbackOn = Exception.class)
	public float calcularCusto(int id_parque, int id_lugar, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception {
		Parque parque = getParque(id_parque);
		LugarEstacionamento lugar = getLugar(id_lugar);
		if(lugar.getParqueId() != parque.getId())
			throw new Exception("Lugar não pertence ao parque!");
		Precario precario = parqueDAO.findPrecarioDoParque(parque.getId(), lugar.getTipo().getNome());
		return precario.calcular_preco(data_inicio, data_fim);
	}

	/**
	 * Devolve as estatísticas do parque
	 * @param id_parque identificador do parque
	 */
	public Estatisticas getEstatisticasParque(int id_parque) throws Exception {
		Parque parque = getParque(id_parque);
		return parque.getEstatisticas();
	}

	/**
	 * Devolve as estatísticas gerais da aplicação.
	 * Uma instância de estatistica por cada parque da aplicação.
	 */
	public List<Estatisticas> getEstatisticasGeral() {
		return estatisticasDAO.findAll();
	}

	/**
	 * Devolve as estatísticas gerais da aplicação.
	 * Soma das estatísticas individuais de todos os parques da aplicação.
	 */
	public Estatisticas getEstatisticasGeralAgregado() {
		List<Estatisticas> estatisticas= estatisticasDAO.findAll();
		int volume_de_estacionamento = 0;
		float faturacao_total = 0;
		for (Estatisticas e : estatisticas){
			volume_de_estacionamento += e.getVolume_de_estacionamento();
			faturacao_total += e.getFaturacao_total();
		}
		return new Estatisticas(0, volume_de_estacionamento, faturacao_total);
	}

	/**
	 * Adiciona um lugar do tipo escolhido ao parque.
	 * @param id_parque identificador do parque
	 * @param tipo_lugar tipo de lugar estacionamento
	 */
	public void addLugar(int id_parque, TipoLugarEstacionamento tipo_lugar) throws Exception {
		Parque parque = getParque(id_parque);
		tipo_lugar = encontraOuPersisteTipoLugar(tipo_lugar);
		LugarEstacionamento lugarEstacionamento = new LugarEstacionamento(0, parque, parque.getId(), tipo_lugar, true, false);
		Set<LugarEstacionamento> lugarEstacionamentoSet = parque.getLugaresEspeciais();
		lugarEstacionamentoSet.add(lugarEstacionamento);
		parque.setLugaresEspeciais(lugarEstacionamentoSet);
		parque.setTotal_lugares(parque.getTotal_lugares() + 1);
		parqueDAO.save(parque);
	}

	/**
	 * Remove o lugar com o identificador especificado do parque.
	 * @param id_parque identificador do parque
	 * @param id_lugar identificador do lugar
	 */
	@Transactional(rollbackOn = Exception.class)
	public void removerLugar(int id_parque, int id_lugar) throws Exception {
		Parque parque = getParque(id_parque);
		LugarEstacionamento lugar = getLugar(id_lugar);
		if(lugar.getParqueId() != parque.getId())
			throw new Exception("Lugar não pertence ao parque!");
		parque.setTotal_lugares(parque.getTotal_lugares() - 1);
		parqueDAO.save(parque);
		lugarDAO.delete(lugar);
	}

	/**
	 * Adiciona n lugares instântaneos ao parque.
	 * @param id_parque identificador do parque
	 * @param n numero de lugares instantaneos a adicionar ao parque
	 */
	public void addLugaresInstantaneos(int id_parque, int n) throws Exception {
		Parque parque = getParque(id_parque);
		int instantaneos_livres = parque.getInstantaneos_livres();
		int instantaneos_total = parque.getInstantaneos_total();
		parque.setInstantaneos_livres(instantaneos_livres + n);
		parque.setInstantaneos_total(instantaneos_total + n);
		parqueDAO.save(parque);
	}

	/**
	 * Remove n lugares instântenos do parque.
	 * Se não for possível remover os n lugares ao parque,
	 * porque este tem menos de n lugares.
	 * Nenhum lugar é removido.
	 * @param id_parque identificador do parque
	 * @param n numero de lugares instantaneos a adicionar ao parque
	 */
	@Transactional(rollbackOn = Exception.class)
	public void removeLugaresInstantaneos(int id_parque, int n) throws Exception {
		Parque parque = getParque(id_parque);

		int instantaneos_livres = parque.getInstantaneos_livres();
		int instantaneos_total = parque.getInstantaneos_total();
		if (instantaneos_livres - n < 0)
			throw new Exception("Não podem ser removidos tantos lugares");

		parque.setInstantaneos_livres(instantaneos_livres - n);
		parque.setInstantaneos_total(instantaneos_total - n);
		parqueDAO.save(parque);
	}

	/**
	 * Devolve o valor da variável utilizavel do lugar com o identificador especificado.
	 * @param id_parque identificador do parque
	 * @param id_lugar identificador do lugar
	 */
	public boolean getEstadoUtilizavelLugar(int id_parque, int id_lugar) throws Exception {
		Parque parque = getParque(id_parque);
		LugarEstacionamento lugar = getLugar(id_lugar);
		if(lugar.getParqueId() != parque.getId())
			throw new Exception("Lugar não pertence ao parque!");
		return lugar.isUtilizavel();
	}

	/**
	 * Modifica o valor da variável utilizavel do lugar com o identificador especificado
	 * para o valor passado por argumento.
	 * @param id_parque identificador do parque
	 * @param id_lugar identificador do lugar
	 * @param utilizavel -
	 */
	public void setEstadoUtilizavelLugar(int id_parque, int id_lugar, boolean utilizavel) throws Exception {
		Parque parque = getParque(id_parque);
		LugarEstacionamento lugar = getLugar(id_lugar);
		if(lugar.getParqueId() != parque.getId())
			throw new Exception("Lugar não pertence ao parque!");
		lugar.setUtilizavel(utilizavel);
		lugarDAO.save(lugar);
	}

	/**
	 * Modifica o valor da variável ocupado do lugar com o identificador especificado
	 * para o valor passado por argumento.
	 * @param id_parque identificador do parque
	 * @param id_lugar identificador do lugar
	 * @param ocupado -
	 */
	public void setEstadoOcupadoLugar(int id_parque, int id_lugar, boolean ocupado) throws Exception {
		Parque parque = getParque(id_parque);
		LugarEstacionamento lugar = getLugar(id_lugar);
		if(lugar.getParqueId() != parque.getId())
			throw new Exception("Lugar não pertence ao parque!");
		lugar.setOcupado(ocupado);
		lugarDAO.save(lugar);
	}

	/**
	 * Guarda o horário passado por argumento no parque.
	 * @param id_parque identificador do parque
	 * @param h novo horario
	 */
	public void setHorario(int id_parque, Horario h) throws Exception {
		Parque parque = getParque(id_parque);
		Horario horarioAtual = parque.getHorario();
		if(horarioAtual != null)
			h.setId(horarioAtual.getId());
		parque.setHorario(h);
		parqueDAO.save(parque);
	}

	/**
	 * Devolve o horário associado ao parque.
	 * @param id_parque identificador do parque
	 */
	public Horario getHorario(int id_parque) throws Exception {
		Parque parque = getParque(id_parque);
		return parque.getHorario();
	}

	/**
	 * @return lista de pares. Um par contem como key um parque e como value o numero de lugares livres (instantaneos + especiais)
	 */
	public List<Map.Entry<Parque, Integer>> listarParquesMaisLugaresLivres() {
		return parqueDAO.findAll()
						.stream()
						.map(p -> new AbstractMap.SimpleEntry<>(p, p.getLugaresLivres()))
						.collect(Collectors.toList());
	}

	/**
	 * Remove do parque de estacionamento um lugar do tipo especificado
	 * @param id_parque identificador do parque
	 * @param tipo_lugar tipo de lugar de estacionamento a remover
	 */
	@Transactional(rollbackOn = Exception.class)
	public void removerLugar(int id_parque, TipoLugarEstacionamento tipo_lugar) throws Exception {
		Parque parque = getParque(id_parque);
		List<LugarEstacionamento> lugarEstacionamento = lugarDAO.findLugaresSemReservasFuturas(parque.getId(), tipo_lugar.getNome());
		if(lugarEstacionamento.size() == 0)
			throw new Exception("O parque não possui nenhum lugar de estacionamento com esse tipo que não tenha reservas futuras.");
		lugarDAO.eliminarLugar(lugarEstacionamento.get(0).getLugarId());
	}

	/**
	 * Encontra os lugares disponível do parque com o tipo especificado
	 * @param id_parque identificador do parque
	 * @param tipo tipo de lugar
	 * @param data_inicio data de inicio do periodo onde se pretende efetuar a reserva
	 * @param data_fim data final do periodo onde se pretende efetuar a reserva
	 * @return lista com ids dos lugares disponiveis nesse intervalo. Ou lista vazia.
	 */
	public List<Integer> procurarLugaresDisponiveis(int id_parque, TipoLugarEstacionamento tipo, LocalDateTime data_inicio, LocalDateTime data_fim){
		return lugarDAO.procurarLugaresDisponiveis(id_parque, tipo.getNome(), data_inicio, data_fim).stream().map(LugarEstacionamento::getLugarId).toList();
	}

	// ********** Funcoes Auxiliares ********

	private TipoLugarEstacionamento encontraOuPersisteTipoLugar(TipoLugarEstacionamento tipoLugar){
		// Encontra entrada na BD com o tipo de lugar
		TipoLugarEstacionamento tipoLugarComId = tipoLugarDAO.findByNome(tipoLugar.getNome());
		if(tipoLugarComId == null)
			tipoLugarDAO.save(tipoLugar);
		else
			tipoLugar = tipoLugarComId;
		return tipoLugar;
	}

	private Parque getParque(int id_parque) throws Exception{
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if(parque == null)
			throw new Exception("Parque não existe!");
		return parque;
	}

	private LugarEstacionamento getLugar(int id_lugar) throws Exception {
		LugarEstacionamento lugar = lugarDAO.findById(id_lugar).orElse(null);
		if(lugar == null)
			throw new Exception("Lugar não existe!");
		return lugar;
	}

	@Override
	public LugarEstacionamento getLugarById(int id_lugar) {
		return lugarDAO.findById(id_lugar).orElse(null);
	}
}