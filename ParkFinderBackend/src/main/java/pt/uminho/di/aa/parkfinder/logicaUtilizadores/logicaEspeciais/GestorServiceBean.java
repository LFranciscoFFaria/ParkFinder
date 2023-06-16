package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pt.uminho.di.aa.parkfinder.logicaParques.*;

import java.util.List;

@Component
@SessionScope
public class GestorServiceBean implements GestorService {

	private Gestor gestor;

	/**
	 * 
	 * @param id_parque
	 */
	public Estatisticas verEstatisticasParque(int id_parque) {

	}

	/**
	 * 
	 * @param id_parque
	 * @param tipoLugar
	 * @param precario
	 */
	public void adicionarPrecario(int id_parque, TipoLugarEstacionamento tipoLugar, Precario precario) {

	}

	public List<Parque> listarMeusParques() {

	}

	/**
	 * 
	 * @param id_parque
	 * @param tipoLugar
	 */
	public void removerPrecario(int id_parque, TipoLugarEstacionamento tipoLugar) {

	}

	public Administrador[] listarMeusAdministradores() {

	}

	/**
	 * 
	 * @param a
	 * @param ids_parques
	 */
	public void criarAdmin(Administrador a, int[] ids_parques) {

	}

	/**
	 * 
	 * @param id_admin
	 */
	public void removerAdmin(int id_admin) {

	}

	/**
	 * 
	 * @param id_admin
	 * @param ids_parques
	 */
	public void removerPermissaoAdminSobreParques(int id_admin, int[] ids_parques) {

	}

	/**
	 * 
	 * @param id_parque
	 * @param newInfo
	 */
	public boolean alterarInformacoesParque(int id_parque, Parque newInfo) {
		// TODO - implement GestorService.alterarInformacoesParque
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param disponivel
	 */
	public void alterarEstadoDisponivelDeParque(int id_parque, boolean disponivel) {
		// TODO - implement GestorService.alterarEstadoDisponivelDeParque
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ids_parques
	 * @param id_admin
	 */
	public void adicionarParquesAAdmin(int[] ids_parques, int id_admin) {
		// TODO - implement GestorService.adicionarParquesAAdmin
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement GestorService.logout
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param horario
	 */
	public boolean criarOuAtualizarHorario(int id_parque, Horario horario) {
		// TODO - implement GestorService.criarOuAtualizarHorario
		throw new UnsupportedOperationException();
	}

}