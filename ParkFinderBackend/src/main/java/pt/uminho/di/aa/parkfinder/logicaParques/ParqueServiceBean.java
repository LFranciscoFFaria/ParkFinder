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

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ParqueServiceBean implements ParqueService {

	private final ParqueDAO parqueDAO;
	private final TipoLugarEstacionamentoDAO tipoLugarDAO;
	private final LugarEstacionamentoDAO lugarDAO;
	private final EstatisticasDAO estatisticasDAO;
	private final PrecarioDAO precarioDAO;
	private final HorarioDAO horarioDAO;

	@Autowired
	public ParqueServiceBean(ParqueDAO parqueDAO, TipoLugarEstacionamentoDAO tipoLugarEstacionamentoDAO, LugarEstacionamentoDAO lugarEstacionamentoDAO, EstatisticasDAO estatisticasDAO, PrecarioDAO precarioDAO, HorarioDAO horarioDAO) {
		this.parqueDAO = parqueDAO;
		this.tipoLugarDAO = tipoLugarEstacionamentoDAO;
		this.lugarDAO = lugarEstacionamentoDAO;
		this.estatisticasDAO = estatisticasDAO;
		this.precarioDAO = precarioDAO;
		this.horarioDAO = horarioDAO;
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
		if (ids == null) return new ArrayList<>();
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
	@Transactional(rollbackOn = Exception.class)
	public void removerParque(int id_parque) {
		if (parqueDAO.existsById(id_parque)) {
			List<LugarEstacionamento> l = lugarDAO.findAllByParqueId(id_parque);
			l.forEach(lugarEstacionamento -> {
				eliminarLugar(lugarEstacionamento.getLugarId());
			});
			parqueDAO.deleteById(id_parque);
		}
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

		Parque parque = parqueDAO.findByIdWithPrecarios(id_parque);

		if(parque == null)
			throw new Exception("Esse identificador de parque não existe");

		TipoLugarEstacionamento tipoLugar = p.getTipo();
		if(tipoLugar == null)
			throw new Exception("Precario precisa de ter um tipo de lugar associado");

		//Encontra ou persiste tipo lugar
		tipoLugar = encontraOuPersisteTipoLugar(tipoLugar);
		p.setTipo(tipoLugar);

		System.out.println(parque.getPrecarios());

		//Remove precario que contem o mesmo tipo do novo
		Set<Precario> precarios = parque.getPrecarios();
		for (Iterator<Precario> it = precarios.iterator();it.hasNext();){
			Precario precario = it.next();
			if (precario.getTipo().getNome().equals(p.getTipo().getNome())){
				it.remove();
				precarioDAO.delete(precario);
			}
		}

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
		Parque parque = parqueDAO.findByIdWithPrecarios(id_parque);

		if(parque == null)
			throw new Exception("Esse identificador de parque não existe");

		// Se o tipo de precario nao existe, retorna, porque um parque não o pode ter associado
		TipoLugarEstacionamento tipoLugarComId = tipoLugarDAO.findByNome(tipoPrecario.getNome());
		if(tipoLugarComId == null) return;

		System.out.println(parque.getPrecarios());

		Set<Precario> precarios = parque.getPrecarios();
		for (Iterator<Precario> it = precarios.iterator();it.hasNext();){
			Precario precario = it.next();
			String tipoNome = precario.getTipo().getNome();
			if (tipoNome.equals(tipoLugarComId.getNome())){
				it.remove();
				precarioDAO.delete(precario);
			}
		}
	}

	/**
	 * Retorna a lista de precarios do parque indicado.
	 * @param id_parque identificador do parque
	 */
	public List<Precario> getPrecarios(int id_parque) throws Exception {
		Parque parque = parqueDAO.findByIdWithPrecarios(id_parque);
		if(parque == null) throw new Exception("Parque não existe!");
		return new ArrayList<>(parque.getPrecarios());
	}

	/**
	 * @param id_parque identificador do parque
	 * @param tipoLugar Tipo de lugar
	 * @return precario do parque mencionado para o tipo de lugar referido. Null se não existir.
	 */
	@Override
	public Precario getPrecarioByParqueIdAndTipoLugar(int id_parque, String tipoLugar){
		return parqueDAO.findPrecarioDoParque(id_parque, tipoLugar);
	}

	public float simularCusto(int id_parque, TipoLugarEstacionamento tipo_lugar, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception {
		if(tipo_lugar == null || tipo_lugar.getNome() == null)
			throw new Exception("Tipo de lugar de estacionamento inválido.");
		if(data_inicio == null || data_fim == null)
			throw new Exception("Datas não podem ser nulas, para se poder simular o preço para um intervalo.");
		Precario precario = getPrecarioByParqueIdAndTipoLugar(id_parque, tipo_lugar.getNome());
		if(precario == null)
			throw new Exception("O parque não possui precario para esse tipo de lugar.");
		return precario.calcular_preco(data_inicio, data_fim);
	}

	/**
	 * Calcula o custo de fazer determinada reserva no parque pretendido para um determinado periodo.
	 * @param id_parque identificador do parque
	 * @param id_lugar identificador do lugar. Deve ser nulo se for uma reserva instantânea.
	 * @param data_inicio data de início do período
	 * @param data_fim data de fim do periodo
	 */
	@Transactional(rollbackOn = Exception.class)
	public float calcularCusto(int id_parque, Integer id_lugar, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception {
		Parque parque = getParque(id_parque);
		Precario precario;
		//reserva instantanea
		if(id_lugar == null){
			precario = getPrecarioByParqueIdAndTipoLugar(id_parque, "Instantaneo");
		}else {
			LugarEstacionamento lugar = getLugar(id_lugar);
			if (lugar.getParqueId() != parque.getId())
				throw new Exception("Lugar não pertence ao parque!");
			precario = getPrecarioByParqueIdAndTipoLugar(parque.getId(), lugar.getTipo().getNome());
		}
		if(precario == null)
			throw new Exception("Precario para o tipo de lugar da reserva não está estabelecido para o respetivo parque. Contacte o gestor do parque.");
		return precario.calcular_preco(data_inicio, data_fim);
	}

	/**
	 * Devolve as estatísticas do parque
	 * @param id_parque identificador do parque
	 */
	public Estatisticas getEstatisticasParque(int id_parque) throws Exception {
		return parqueDAO.getEstatisticasDoParque(id_parque);
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
	@Transactional(rollbackOn = Exception.class)
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

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void addLugares(int id_parque, TipoLugarEstacionamento tipo_lugar, int n) throws Exception {
		Parque parque = getParque(id_parque);
		tipo_lugar = encontraOuPersisteTipoLugar(tipo_lugar);
		Set<LugarEstacionamento> lugarEstacionamentoSet = parque.getLugaresEspeciais();
		for(int i = 0; i < n; i++) {
			LugarEstacionamento lugarEstacionamento = new LugarEstacionamento(0, parque, parque.getId(), tipo_lugar, true, false);
			lugarEstacionamentoSet.add(lugarEstacionamento);
		}
		parque.setTotal_lugares(parque.getTotal_lugares() + n);
		parque.setLugaresEspeciais(lugarEstacionamentoSet);
		parqueDAO.save(parque);
	}

	/**
	 * Remove do parque de estacionamento um lugar do tipo especificado
	 * @param id_parque identificador do parque
	 * @param tipo_lugar tipo de lugar de estacionamento a remover
	 */
	@Transactional(rollbackOn = Exception.class)
	public void removerLugar(int id_parque, TipoLugarEstacionamento tipo_lugar) throws Exception {
		Parque parque = getParque(id_parque);
		List<Integer> lugarEstacionamento = lugarDAO.findLugaresSemReservasFuturas(parque.getId(), tipo_lugar.getNome());
		if(lugarEstacionamento.size() == 0)
			throw new Exception("O parque não possui nenhum lugar de estacionamento com esse tipo que não tenha reservas futuras.");
		parque.setTotal_lugares(parque.getTotal_lugares() - 1);
		parqueDAO.save(parque);
		eliminarLugar(lugarEstacionamento.get(0));
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
		eliminarLugar(id_lugar);
	}

	@Transactional(rollbackOn = Exception.class)
	public void removerLugares(int id_parque, TipoLugarEstacionamento tipo_lugar, int n) throws Exception {
		Parque parque = getParque(id_parque);
		List<Integer> lugarEstacionamento = lugarDAO.findLugaresSemReservasFuturas(parque.getId(), tipo_lugar.getNome());
		if(lugarEstacionamento.size() < n)
			throw new Exception("O parque apenas possui " + lugarEstacionamento.size() + " lugares de estacionamento do tipo referido que não têm reservas futuras.");
		parque.setTotal_lugares(parque.getTotal_lugares() - n);
		parqueDAO.save(parque);
		for(int i = 0; i < n; i++)
			eliminarLugar(lugarEstacionamento.get(i));
	}

	/**
	 * Adiciona n lugares instântaneos ao parque.
	 * @param id_parque identificador do parque
	 * @param n numero de lugares instantaneos a adicionar ao parque
	 */
	@Transactional(rollbackOn = Exception.class)
	public void addLugaresInstantaneos(int id_parque, int n) throws Exception {
		if (n<1)
			throw new Exception("O número de lugares a adicionar tem de ser 1 ou maior.");
		Parque parque = getParque(id_parque);
		int instantaneos_livres = parque.getInstantaneos_livres();
		int instantaneos_total = parque.getInstantaneos_total();
		int total_lugares = parque.getTotal_lugares();
		parque.setInstantaneos_livres(instantaneos_livres + n);
		parque.setInstantaneos_total(instantaneos_total + n);
		parque.setTotal_lugares(total_lugares + n);
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
		if (n<1)
			throw new Exception("O número de lugares a remover tem de ser 1 ou maior.");
		Parque parque = getParque(id_parque);

		int instantaneos_livres = parque.getInstantaneos_livres();
		int instantaneos_total = parque.getInstantaneos_total();
		int total_lugares = parque.getTotal_lugares();
		if (instantaneos_livres - n < 0)
			throw new Exception("Não podem ser removidos tantos lugares");

		parque.setInstantaneos_livres(instantaneos_livres - n);
		parque.setInstantaneos_total(instantaneos_total - n);
		parque.setTotal_lugares(total_lugares - n);
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
		Parque parque = parqueDAO.findByIdWithHorario(id_parque);
		if(parque == null) throw new Exception("Parque não existe!");
		Horario horarioAntigo = parque.getHorario();
		parque.setHorario(h);
		parqueDAO.save(parque);
		if(horarioAntigo != null)
			horarioDAO.delete(horarioAntigo);
	}

	/**
	 * Devolve o horário associado ao parque.
	 * @param id_parque identificador do parque
	 */
	public Horario getHorario(int id_parque) throws Exception {
		return parqueDAO.getHorarioDoParque(id_parque);
	}

	/**
	 * Encontra os lugares disponível do parque com o tipo especificado
	 *
	 * @param id_parque   identificador do parque
	 * @param tipo        tipo de lugar
	 * @param data_inicio data de início do periodo onde se pretende efetuar a reserva
	 * @param data_fim    data final do periodo onde se pretende efetuar a reserva
	 * @return lista com ids dos lugares disponiveis nesse intervalo. Ou lista vazia.
	 */
	public Set<Integer> procurarLugaresDisponiveis(int id_parque, TipoLugarEstacionamento tipo, LocalDateTime data_inicio, LocalDateTime data_fim){
		return lugarDAO.procurarLugaresDisponiveis(id_parque, tipo.getNome(), data_inicio, data_fim);
	}

	/**
	 * Muda o estado de disponibilidade do parque para o passado por argumento.
	 * @param id_parque identificador do parque
	 * @param disponivel valor boleano que representa a disponibilidade do parque
	 */
	public void setDisponivel(int id_parque, boolean disponivel) throws Exception {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque == null){
			throw new Exception("A parque não existe!");
		}
		parque.setDisponivel(disponivel);
		parqueDAO.save(parque);
	}

	/**
	 * Método a utilizar se queremos alterar mais de duas variáveis do parque simultanemente.
	 * @param id_parque identificador do parque
	 * @param nome nome do parque
	 * @param descricao descrição do parque
	 * @param morada morada
	 * @param latitude latitude do parque
	 * @param longitude longitude do parque
	 * @param disponivel disponibilidade do parque
	 * @param caminho_foto caminho onde está guardada a foto do parque
	 * @return retorna verdadeiro em caso de sucesso
	*/
	public boolean setAll(int id_parque, Optional<String> nome, Optional<String> descricao, Optional<String> morada, Optional<Float> latitude, Optional<Float> longitude, Optional<Boolean> disponivel, Optional<String> caminho_foto) throws Exception {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque == null){
			throw new Exception("A parque não existe!");
		}
		if(nome != null)
			nome.ifPresent(parque::setNome);
		if (descricao != null)
			descricao.ifPresent(parque::setDescricao);
		if (morada != null)
			morada.ifPresent(parque::setMorada);
		if (latitude != null)
			latitude.ifPresent(parque::setLatitude);
		if (longitude != null)
			longitude.ifPresent(parque::setLongitude);
		if (disponivel != null)
			disponivel.ifPresent(parque::setDisponivel);
		if (caminho_foto != null)
			caminho_foto.ifPresent(parque::setCaminho_foto);
		parqueDAO.save(parque);
		return true;
	}

	@Override
	public LugarEstacionamento getLugarById(int id_lugar) {
		return lugarDAO.findById(id_lugar).orElse(null);
	}

	public void incrementaVolume_E_aumentaFaturacao(int id_parque, float custo) throws Exception {
		Parque parque = parqueDAO.findByIdWithEstatisticas(id_parque);
		Estatisticas estatisticas = parque.getEstatisticas();
		estatisticas.incVolumeDeEstacionamento();
		estatisticas.setFaturacao_total(estatisticas.getFaturacao_total() + custo);
		parqueDAO.save(parque);
	}

	@Override
	@Transactional
	public void incLugaresInstantaneos(int id_parque, int n) throws Exception {
		Parque parque = getParque(id_parque);
		parque.incLugaresInstantaneos(n);
		parqueDAO.save(parque);
	}

	@Override
	@Transactional
	public void decLugaresInstantaneos(int id_parque, int n) throws Exception {
		Parque parque = getParque(id_parque);
		parque.decLugaresInstantaneos(n);
		parqueDAO.save(parque);
	}

	public List<Parque> getParquesDoGestor(int id_gestor){
		return parqueDAO.getParquesDoGestor(id_gestor);
	}

	public List<Parque> getParquesDoAdministrador(int id_admin){
		return parqueDAO.getParquesDoGestor(id_admin);
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

	private void eliminarLugar(int lugar_id){
		lugarDAO.requisitoEliminarLugar(lugar_id);
		lugarDAO.deleteById(lugar_id);
	}
}