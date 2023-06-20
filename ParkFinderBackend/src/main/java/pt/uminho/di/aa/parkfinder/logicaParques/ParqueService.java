package pt.uminho.di.aa.parkfinder.logicaParques;


import org.springframework.data.domain.Page;
import pt.uminho.di.aa.parkfinder.logicaParques.model.*;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ParqueService {

	List<Parque> listarParques();

	Page<Parque> listarParques(int pagina, int nrResultadosPorPagina);

	/**
	 * 
	 * @param ids
	 */
	List<Parque> listarParques(List<Integer> ids);


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
	Parque criarParque(Parque p)  throws Exception ;

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
	void criarPrecario(int id_parque, Precario p) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param tipoPrecario
	 */
	void removerPrecario(int id_parque, TipoLugarEstacionamento tipoPrecario) throws Exception;

	/**
	 * 
	 * @param id_parque
	 */
	List<Precario> getPrecarios(int id_parque) throws Exception;

	Precario getPrecarioByNome(int id_parque, String nome) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param data_inicio
	 * @param data_fim
	 */
	float calcularCusto(int id_parque, Integer id_lugar, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception;

	/**
	 * 
	 * @param id_parque
	 */
	Estatisticas getEstatisticasParque(int id_parque) throws Exception;

	List<Estatisticas> getEstatisticasGeral();

	Estatisticas getEstatisticasGeralAgregado();

	/**
	 * 
	 * @param id_parque
	 * @param tipo_lugar
	 */
	void addLugar(int id_parque, TipoLugarEstacionamento tipo_lugar) throws Exception;

	/**
	 *
	 * @param id_parque
	 * @param tipo_lugar
	 * @param n
	 */
	void addLugares(int id_parque, TipoLugarEstacionamento tipo_lugar, int n) throws Exception;

	/**
	 *
	 * @param id_parque
	 * @param tipo_lugar
	 */
	void removerLugar(int id_parque, TipoLugarEstacionamento tipo_lugar) throws Exception;

	/**
	 *
	 * @param id_parque
	 * @param tipo_lugar
	 * @param n
	 */
	void removerLugares(int id_parque, TipoLugarEstacionamento tipo_lugar, int n) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 */
	void removerLugar(int id_parque, int id_lugar) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param n
	 */
	void addLugaresInstantaneos(int id_parque, int n) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param n
	 */
	void removeLugaresInstantaneos(int id_parque, int n) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 */
	boolean getEstadoUtilizavelLugar(int id_parque, int id_lugar) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param utilizavel
	 */
	void setEstadoUtilizavelLugar(int id_parque, int id_lugar, boolean utilizavel) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param id_lugar
	 * @param ocupado
	 */
	void setEstadoOcupadoLugar(int id_parque, int id_lugar, boolean ocupado) throws Exception;

	/**
	 * 
	 * @param id_parque
	 * @param h
	 */
	void setHorario(int id_parque, Horario h) throws Exception;

	/**
	 * 
	 * @param id_parque
	 */
	Horario getHorario(int id_parque) throws Exception;

	List<Map.Entry<Parque, Integer>> listarParquesMaisLugaresLivres();

	/**
	 * 
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	List<Integer> procurarLugaresDisponiveis(int id_parque, TipoLugarEstacionamento tipo, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception;

	/**
	 *
	 * @param id_parque
	 * @param disponivel
	 */
	public void setDisponivel(int id_parque, boolean disponivel) throws Exception;

	/**
	 *
	 * @param id_parque
	 * @param nome
	 * @param descricao
	 * @param latitude
	 * @param longitude
	 * @param disponivel
	 * @param instantaneos_livres
	 * @param instantaneos_total
	 * @param total_lugares
	 * @param caminho_foto
	 * @return
	 */
	public boolean setAll(int id_parque, String nome, String descricao, Float latitude, Float longitude, Boolean disponivel, Integer instantaneos_livres, Integer instantaneos_total,Integer total_lugares, String caminho_foto) throws Exception;

	LugarEstacionamento getLugarById(int id_lugar);
}