package pt.uminho.di.aa.parkfinder.logicaParques;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pt.uminho.di.aa.parkfinder.logicaParques.model.*;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ParqueServiceBean implements ParqueService {

	private ParqueDAO parqueDAO;

	@Autowired
	public ParqueServiceBean(ParqueDAO parqueDAO) {
		this.parqueDAO = parqueDAO;
	}

	public List<Parque> listarParques() {
		return parqueDAO.findAll();
	}

	/**
	 * 
	 * @param ids
	 */
	public List<Parque> listarParques(List<Integer> ids) {
		// TODO: Não sei se a verificação de ID válido é para ser feita aqui
		return parqueDAO.findAllById(ids);
	}

	/**
	 * 
	 * @param nome
	 */
	public List<Parque> procurarParque(String nome) {
		return parqueDAO.findAllByNome(nome);
	}

	/**
	 * 
	 * @param id_parque
	 */
	public Parque procurarParque(int id_parque) {
		return parqueDAO.findById(id_parque).orElse(null);
	}

	/**
	 * 
	 * @param p
	 */
	public Parque criarParque(Parque p) {
		p.setId(0);
		// TODO: verificar que campos do parque já veem preenchidos do front end
		p.setEstatisticas(null);
		return parqueDAO.save(p);
	}

	/**
	 * 
	 * @param id_parque
	 */
	public void removerParque(int id_parque) {
		// TODO: ver exceptions
		if (!parqueDAO.existsById(id_parque)){
			//throw new Exception("Parque não existe!");
		}
		parqueDAO.deleteById(id_parque);
	}

	/**
	 * 
	 * @param id_parque
	 * @param p
	 */
	public void criarPrecario(int id_parque, Precario p) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		Set<Precario> precarios = parque.getPrecarios();
		for (Precario precario : precarios){
			if(precario.getTipo().getId() == p.getTipo().getId()){
				//throw new Exception("Já existe um precário desse tipo " + tipoPrecario.toString() + " associado ao parque!");
			}
		}
		precarios.add(p);
		parque.setPrecarios(precarios);
		parqueDAO.save(parque);
	}

	/**
	 * 
	 * @param id_parque
	 * @param tipoPrecario
	 */
	public void removerPrecario(int id_parque, TipoLugarEstacionamento tipoPrecario) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		Set<Precario> precarios = parque.getPrecarios();
		for (Precario precario : precarios){
			if(precario.getTipo().getId() == tipoPrecario.getId()){
				precarios.remove(precario);
				parque.setPrecarios(precarios);
				parqueDAO.save(parque);
				break;
			}
		}
		//throw new Exception("Não existe um precário do tipo " + tipoPrecario.toString() + " associado ao parque!");
	}

	/**
	 * 
	 * @param id_parque
	 */
	public List<Precario> getPrecarios(int id_parque) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		// TODO: não sei se resulta ou se queremos mudar o tipo de retorno
		return parque.getPrecarios().stream().toList();
	}

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param data_inicio
	 * @param data_fim
	 */
	public float calcularCusto(int id_parque, int id_lugar, java.util.Date data_inicio, java.util.Date data_fim) {
		// TODO: Necessario saber o tipo de lugar de estacionamento para ser possivel calcular o custo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 */
	public Estatisticas getEstatisticasParque(int id_parque) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		return parque.getEstatisticas();
	}

	public List<Estatisticas> getEstatisticasGeral() {
		List<Parque> parques= parqueDAO.findAll();
		List<Estatisticas> estatisticas = new ArrayList<Estatisticas>();
		for(Parque parque:parques){
			estatisticas.add(parque.getEstatisticas());
		}
		return estatisticas;
	}

	public Estatisticas getEstatisticasGeralAgregado() {
		List<Parque> parques= parqueDAO.findAll();
		Estatisticas estatistica = new Estatisticas();
		int volume_de_estacionamento = 0;
		float faturacao_total = 0;
		for (Parque parque:parques){
			Estatisticas e = parque.getEstatisticas();
			volume_de_estacionamento += e.getVolume_de_estacionamento();
			faturacao_total += e.getFaturacao_total();
		}
		estatistica.setVolume_de_estacionamento(volume_de_estacionamento);
		estatistica.setFaturacao_total(faturacao_total);
		return estatistica;
	}

	/**
	 * 
	 * @param id_parque
	 * @param tipo_lugar
	 */
	public void addLugar(int id_parque, TipoLugarEstacionamento tipo_lugar) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		LugarEstacionamento lugarEstacionamento = new LugarEstacionamento();
		lugarEstacionamento.setParqueId(id_parque);
		lugarEstacionamento.setOcupado(false);
		lugarEstacionamento.setUtilizavel(true);
		lugarEstacionamento.setTipo(tipo_lugar);
		Set <LugarEstacionamento> lugarEstacionamentoSet = parque.getLugaresEspeciais();
		lugarEstacionamentoSet.add(lugarEstacionamento);
		parque.setLugaresEspeciais(lugarEstacionamentoSet);
		parqueDAO.save(parque);
	}

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 */
	public void removerLugar(int id_parque, int id_lugar) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		Set <LugarEstacionamento> lugarEstacionamentoSet = parque.getLugaresEspeciais();
		for (LugarEstacionamento lugarEstacionamento:lugarEstacionamentoSet){
			if(lugarEstacionamento.getLugarId()==id_lugar) {
				//TODO : preciso verificar as reservas do lugar ou se está ocupado
				lugarEstacionamentoSet.remove(lugarEstacionamento);
				parque.setLugaresEspeciais(lugarEstacionamentoSet);
				parqueDAO.save(parque);
				break;
			}
		}
		//throw new Exception("O parque não possui um lugar de estacionamento com esse identificador");
	}

	/**
	 * 
	 * @param id_parque
	 * @param n
	 */
	public void addLugaresInstantaneos(int id_parque, int n) {
		// TODO: Verificar exceção
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			//throw new Exception("Parque não Existe")
		}
		int instantaneos_livres = parque.getInstantaneos_livres();
		int instantaneos_total = parque.getInstantaneos_total();
		parque.setInstantaneos_livres(instantaneos_livres + n);
		parque.setInstantaneos_total(instantaneos_total + n);
		parqueDAO.save(parque);
	}

	/**
	 * 
	 * @param id_parque
	 * @param n
	 */
	public void removeLugaresInstantaneos(int id_parque, int n) {
		// TODO: Verificar exceção
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			//throw new Exception("Parque não Existe");
		}
		int instantaneos_livres = parque.getInstantaneos_livres();
		int instantaneos_total = parque.getInstantaneos_total();
		if ((instantaneos_livres - n)<0||(instantaneos_total - n)<0){
			//throw new Exception("Não podem ser removidos tantos lugares")
		}
		parque.setInstantaneos_livres(instantaneos_livres - n);
		parque.setInstantaneos_total(instantaneos_total - n);
		parqueDAO.save(parque);
	}

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 */
	public boolean getEstadoUtilizavelLugar(int id_parque, int id_lugar) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		Set <LugarEstacionamento> lugarEstacionamentoSet = parque.getLugaresEspeciais();
		for (LugarEstacionamento lugarEstacionamento : lugarEstacionamentoSet){
			if(lugarEstacionamento.getLugarId()==id_lugar) {
				return lugarEstacionamento.isUtilizavel();
			}
		}
		//throw new Exception("O parque não possui um lugar de estacionamento com esse identificador");
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param utilizavel
	 */
	public void setEstadoUtilizavelLugar(int id_parque, int id_lugar, boolean utilizavel) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		Set <LugarEstacionamento> lugarEstacionamentoSet = parque.getLugaresEspeciais();
		for (LugarEstacionamento lugarEstacionamento : lugarEstacionamentoSet){
			if(lugarEstacionamento.getLugarId()==id_lugar) {
				lugarEstacionamento.setUtilizavel(utilizavel);
				parque.setLugaresEspeciais(lugarEstacionamentoSet);
				parqueDAO.save(parque);
				break;
			}
		}
		//throw new Exception("O parque não possui um lugar de estacionamento com esse identificador");
	}

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param ocupado
	 */
	public void setEstadoOcupadoLugar(int id_parque, int id_lugar, boolean ocupado) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		Set <LugarEstacionamento> lugarEstacionamentoSet = parque.getLugaresEspeciais();
		for (LugarEstacionamento lugarEstacionamento : lugarEstacionamentoSet){
			if(lugarEstacionamento.getLugarId()==id_lugar) {
				lugarEstacionamento.setOcupado(ocupado);
				parque.setLugaresEspeciais(lugarEstacionamentoSet);
				parqueDAO.save(parque);
				break;
			}
		}
		//throw new Exception("O parque não possui um lugar de estacionamento com esse identificador");
	}

	/**
	 * 
	 * @param id_parque
	 * @param h
	 */
	public void setHorario(int id_parque, Horario h) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		parque.setHorario(h);
		parqueDAO.save(parque);
	}

	/**
	 * 
	 * @param id_parque
	 */
	public Horario getHorario(int id_parque) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		return parque.getHorario();
	}

	public List<List<Object>> listarParquesMaisLugaresLivresETotais() {
		// TODO - implement ParqueService.listarParquesMaisLugaresLivresETotais
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param tipo_lugar
	 */
	public void removerLugar(int id_parque, TipoLugarEstacionamento tipo_lugar) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		Set <LugarEstacionamento> lugarEstacionamentoSet = parque.getLugaresEspeciais();
		for (LugarEstacionamento lugarEstacionamento:lugarEstacionamentoSet){
			if(lugarEstacionamento.getTipo().getId()==tipo_lugar.getId()) {
				//TODO : preciso verificar as reservas do lugar ou se está ocupado
				lugarEstacionamentoSet.remove(lugarEstacionamento);
				parque.setLugaresEspeciais(lugarEstacionamentoSet);
				parqueDAO.save(parque);
				break;
			}
		}
		//throw new Exception("O parque não possui um lugar de estacionamento com esse identificador");
	}

	/**
	 * 
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	public Integer procurarLugarDisponivel(int id_parque, TipoLugarEstacionamento tipo, java.util.Date data_inicio, java.util.Date data_fim) {
		Parque parque = parqueDAO.findById(id_parque).orElse(null);
		if (parque.equals(null)){
			// TODO: ver exceptions
			//throw new Exception("Parque não existe!");
		}
		Set <LugarEstacionamento> lugarEstacionamentoSet = parque.getLugaresEspeciais();
		for (LugarEstacionamento lugarEstacionamento : lugarEstacionamentoSet){
			if(lugarEstacionamento.getTipo().getId()==tipo.getId()) {
				//TODO Lógica de ver as reservas
				return lugarEstacionamento.getLugarId();
			}
		}
		return -1;
	}

}