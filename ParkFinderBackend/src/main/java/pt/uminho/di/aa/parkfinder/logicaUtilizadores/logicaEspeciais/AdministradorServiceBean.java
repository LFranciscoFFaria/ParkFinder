package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueService;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParquesReservas.ParqueReservaService;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaReservas.ReservaService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorService;

import java.util.List;


@Component
@SessionScope
public class AdministradorServiceBean implements AdministradorService {

	private final ParqueService parqueService;
	private final ReservaService reservaService;
	private final ParqueReservaService parqueReservaService;
	private final UtilizadorService utilizadorService;
	private Administrador administrador = null;

	public AdministradorServiceBean(ParqueService parqueService, ReservaService reservaService, ParqueReservaService parqueReservaService, UtilizadorService utilizadorService) {
		this.parqueService = parqueService;
		this.reservaService = reservaService;
		this.parqueReservaService = parqueReservaService;
		this.utilizadorService = utilizadorService;
	}

	/**
	 * Devolve a lista dos parques associados ao administrador.
	 */
	public List<Parque> listarMeusParques() throws Exception {
		checkIsLoggedIn();
		return parqueService.getParquesDoAdministrador(administrador.getId());
	}

	/**
	 * Função que adiciona o número de lugares instântaneos especificados ao parque.
	 * @param id_parque identificador do parque
	 * @param N número de lugares instantâneos a adicionar
	 */
	public void addLugarInstantaneo(int id_parque,int N) throws Exception {
		checkIsLoggedIn();
		if (N<1)
			throw new Exception("O número de lugares a adicionar tem de ser 1 ou maior.");
		parqueService.addLugaresInstantaneos(id_parque, N);
	}

	/**
	 * Função que adiciona o número de lugares especiais especificados ao parque.
	 * @param id_parque identificador do parque
	 * @param N número de lugares especiais a adicionar
	 * @param tipo tipo do lugar especial
	 */
	public void addLugarEspecial(int id_parque, int N, TipoLugarEstacionamento tipo) throws Exception {
		checkIsLoggedIn();
		if (N<1)
			throw new Exception("O número de lugares a adicionar tem de ser 1 ou maior.");
		parqueService.addLugares(id_parque, tipo, N);
	}

	/**
	 * Função que remove o número de lugares instântaneos especificados ao parque.
	 * @param id_parque identificador do parque
	 * @param N número de lugares instantâneos a remover
	 */
	public void removerLugarInstantaneo(int id_parque,int N) throws Exception {
		checkIsLoggedIn();
		if (N<1)
			throw new Exception("O número de lugares a remover tem de ser 1 ou maior.");
		parqueService.removeLugaresInstantaneos(id_parque, N);
	}

	/**
	 * Função que remove o número de lugares especiais especificados ao parque.
	 * @param id_parque identificador do parque
	 * @param N número de lugares especiais a remover
	 * @param tipo tipo do lugar especial
	 */
	public void removerLugarEspecial(int id_parque, int N, TipoLugarEstacionamento tipo) throws Exception {
		checkIsLoggedIn();
		if (N<1)
			throw new Exception("O número de lugares a adicionar tem de ser 1 ou maior.");
		parqueService.removerLugares(id_parque, tipo, N);
	}

	/**
	 * Função que retorna uma reserva se a matrícula passada por argumento estiver numa reserva com o estado OCUPADA.
	 *
	 * @param id_parque identificador do parque onde se pretende procurar
	 * @param matricula matricula do carro
	 */
	public Reserva encontrarReservaPorMatricula(int id_parque, String matricula) throws Exception {
		checkIsLoggedIn();
		return reservaService.getReservaMatricula(id_parque, matricula);
	}

	/**
	 * Associa a matrícula passada por argumento à reserva.
	 * @param id_reserva identificador da reserva à qual queremos alterar a matrícula
	 * @param matricula matricula nova a associar
	 */
	public boolean associarMatriculaAReserva(int id_reserva, String matricula) throws Exception {
		checkIsLoggedIn();
		reservaService.setMatricula(id_reserva,matricula);
		return true;
	}

	/**
	 * Função que retorna a lista das reservas ativas do parque.
	 * @param id_parque identificador do parque
	 */
	public List<Reserva> verReservasAtivasDeParque(int id_parque) throws Exception {
		checkIsLoggedIn();
		return reservaService.getReservasAtivasDoParque(id_parque);
	}

	public void logout() throws Exception{
		checkIsLoggedIn();
		administrador = null;
	}

	public void marcarEntradaParque(int idReserva, String matricula) throws Exception {
		checkIsLoggedIn();
		parqueReservaService.marcarEntradaParque(idReserva, matricula);
	}

	@Transactional
	public void criarReservaInstantaneaEMarcaEntrada(int idUtilizador, int idParque, String matricula) throws Exception{
		checkIsLoggedIn();
		Reserva r = parqueReservaService.criarReservaInstantanea(idUtilizador, idParque);
		marcarEntradaParque(r.getId(), matricula);
	}

	public void marcarSaidaParque(int idReserva) throws Exception {
		checkIsLoggedIn();
		parqueReservaService.marcarSaidaParque(idReserva);
	}

	public void setAdministrador(Administrador a) {
		this.administrador = a;
    }

	@Override
	public Administrador getAdministradorInfo() throws Exception {
		checkIsLoggedIn();
		return (Administrador) utilizadorService.getUtilizador(administrador.getId());
	}

	private void checkIsLoggedIn() throws Exception {
		if(administrador == null)
			throw new Exception("Não tem sessão iniciada.");
	}
}