package pt.uminho.di.aa.parkfinder.logicaParques;


import java.util.List;

public interface ParqueService {

	List<Parque> listarParques();

	/**
	 * 
	 * @param ids
	 */
	List<Parque> listarParques(int[] ids);

	/**
	 * 
	 * @param nome
	 */
	List<Parque> procurarParque(String nome);

	/**
	 * 
	 * @param id_parque
	 */
	Parque procurarParque(int id_parque);

	/**
	 * 
	 * @param p
	 */
	Parque criarParque(Parque p);

	/**
	 * 
	 * @param id_parque
	 */
	void removerParque(int id_parque);

	/**
	 * 
	 * @param id_parque
	 * @param p
	 */
	void criarPrecario(int id_parque, Precario p);

	/**
	 * 
	 * @param id_parque
	 * @param tipoPrecario
	 */
	void removerPrecario(int id_parque, TipoLugarEstacionamento tipoPrecario);

	/**
	 * 
	 * @param id_parque
	 */
	List<Precario> getPrecarios(int id_parque);

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param data_inicio
	 * @param data_fim
	 */
	float calcularCusto(int id_parque, int id_lugar, java.util.Date data_inicio, java.util.Date data_fim);

	/**
	 * 
	 * @param id_parque
	 */
	Estatisticas getEstatisticasParque(int id_parque);

	List<Estatisticas> getEstatisticasGeral();

	Estatisticas getEstatisticasGeralAgregado();

	/**
	 * 
	 * @param id_parque
	 * @param tipo_lugar
	 */
	void addLugar(int id_parque, TipoLugarEstacionamento tipo_lugar);

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 */
	void removerLugar(int id_parque, int id_lugar);

	/**
	 * 
	 * @param id_parque
	 * @param n
	 */
	void addLugaresInstantaneos(int id_parque, int n);

	/**
	 * 
	 * @param id_parque
	 * @param n
	 */
	void removeLugaresInstantaneos(int id_parque, int n);

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 */
	boolean getEstadoUtilizavelLugar(int id_parque, int id_lugar);

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param utilizavel
	 */
	void setEstadoUtilizavelLugar(int id_parque, int id_lugar, boolean utilizavel);

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param ocupado
	 */
	void setEstadoOcupadoLugar(int id_parque, int id_lugar, boolean ocupado);

	/**
	 * 
	 * @param id_parque
	 * @param h
	 */
	void setHorario(int id_parque, Horario h);

	/**
	 * 
	 * @param id_parque
	 */
	Horario getHorario(int id_parque);

	List<List<Object>> listarParquesMaisLugaresLivresETotais();

	/**
	 * 
	 * @param id_parque
	 * @param tipo_lugar
	 */
	void removerLugar(int id_parque, TipoLugarEstacionamento tipo_lugar);

	/**
	 * 
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	Integer procurarLugarDisponivel(int id_parque, TipoLugarEstacionamento tipo, java.util.Date data_inicio, java.util.Date data_fim);
}