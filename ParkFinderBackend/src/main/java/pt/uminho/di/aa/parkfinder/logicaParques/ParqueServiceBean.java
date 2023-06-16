package pt.uminho.di.aa.parkfinder.logicaParques;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ParqueServiceBean implements ParqueService {

	public List<Parque> listarParques() {
		// TODO - implement ParqueService.listarParques
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ids
	 */
	public List<Parque> listarParques(int[] ids) {
		// TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 */
	public List<Parque> procurarParque(String nome) {
		// TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 */
	public Parque procurarParque(int id_parque) {
		// TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param p
	 */
	public Parque criarParque(Parque p) {
		// TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 */
	public void removerParque(int id_parque) {
		// TODO - implement ParqueService.removerParque
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param p
	 */
	public void criarPrecario(int id_parque, Precario p) {
		// TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param tipoPrecario
	 */
	public void removerPrecario(int id_parque, TipoLugarEstacionamento tipoPrecario) {
		// TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 */
	public List<Precario> getPrecarios(int id_parque) {
		// TODO
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
		// TODO - implement ParqueService.addLugaresInstantaneos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param n
	 */
	public void removeLugaresInstantaneos(int id_parque, int n) {
		// TODO - implement ParqueService.removeLugaresInstantaneos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 */
	public boolean getEstadoUtilizavelLugar(int id_parque, int id_lugar) {
		// TODO - implement ParqueService.getEstadoUtilizavelLugar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param utilizavel
	 */
	public void setEstadoUtilizavelLugar(int id_parque, int id_lugar, boolean utilizavel) {
		// TODO - implement ParqueService.setEstadoUtilizavelLugar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param ocupado
	 */
	public void setEstadoOcupadoLugar(int id_parque, int id_lugar, boolean ocupado) {
		// TODO - implement ParqueService.setEstadoOcupadoLugar
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
		// TODO - implement ParqueService.removerLugar
		throw new UnsupportedOperationException();
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