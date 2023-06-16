package pt.uminho.di.aa.parkfinder.logicaParques;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores.UtilizadorDAO;

import java.util.List;

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
		//return parqueDAO.findByAllNome(nome);
		return null;
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
		// TODO: não sei adicionar à lista de Precarios
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
		// TODO: não sei remover à lista de Precarios
	}

	/**
	 * 
	 * @param id_parque
	 */
	public List<Precario> getPrecarios(int id_parque) {
		// TODO nao sei
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param data_inicio
	 * @param data_fim
	 */
	public float calcularCusto(int id_parque, int id_lugar, java.util.Date data_inicio, java.util.Date data_fim) {
		// TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 */
	public Estatisticas getEstatisticasParque(int id_parque) {
		// TODO
		throw new UnsupportedOperationException();
	}

	public List<Estatisticas> getEstatisticasGeral() {
		// TODO
		throw new UnsupportedOperationException();
	}

	public Estatisticas getEstatisticasGeralAgregado() {
		// TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param tipo_lugar
	 */
	public void addLugar(int id_parque, TipoLugarEstacionamento tipo_lugar) {
		// TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 */
	public void removerLugar(int id_parque, int id_lugar) {
		// TODO - implement ParqueService.removerLugar
		throw new UnsupportedOperationException();
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
		// TODO Vai ser preciso fazer uma querry
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param utilizavel
	 */
	public void setEstadoUtilizavelLugar(int id_parque, int id_lugar, boolean utilizavel) {
		// TODO Mesma querry que em cima
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param ocupado
	 */
	public void setEstadoOcupadoLugar(int id_parque, int id_lugar, boolean ocupado) {
		// TODO fazer outra querry
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param h
	 */
	public void setHorario(int id_parque, Horario h) {
		// TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 */
	public Horario getHorario(int id_parque) {
		// TODO
		throw new UnsupportedOperationException();
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

	}

	/**
	 * 
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	public Integer procurarLugarDisponivel(int id_parque, TipoLugarEstacionamento tipo, java.util.Date data_inicio, java.util.Date data_fim) {
		// TODO - implement ParqueService.procurarLugarDisponivel
		throw new UnsupportedOperationException();
	}

}