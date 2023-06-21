package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParquesReservas.ParqueReservaServiceBean;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaReservas.ReservaServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorServiceBean;

import java.time.LocalDateTime;
import java.util.List;


@Component
@SessionScope
public class CondutorServiceBean implements CondutorService {

	private final UtilizadorServiceBean utilizadorServiceBean;
	private final ReservaServiceBean reservaServiceBean;
	private final ParqueReservaServiceBean parqueReservaServiceBean;
	private Condutor condutor = null;

	@Autowired
	public CondutorServiceBean(UtilizadorServiceBean utilizadorServiceBean, ReservaServiceBean reservaServiceBean, ParqueReservaServiceBean parqueReservaServiceBean) {
		this.utilizadorServiceBean = utilizadorServiceBean;
		this.reservaServiceBean = reservaServiceBean;
		this.parqueReservaServiceBean = parqueReservaServiceBean;
	}

	public void setCondutor(Utilizador utilizador) {
		this.condutor = (Condutor) utilizador;
	}


	/* ******** Métodos do serviço ******** */

	/**
	 * Função que permite a edição dos campos do perfil do utilizador
	 * @param condutorEdit Instância com os novos campos do perfil atualizados
	 */
	public boolean editarPerfil(CondutorEdit condutorEdit) throws Exception {
		checkIsLoggedIn();

		Condutor newCondutor = condutor.clone();
		if(condutorEdit.getNome() != null)
			newCondutor.setNome(condutorEdit.getNome().orElse(null));
		if(condutorEdit.getEmail() != null)
			newCondutor.setEmail(condutorEdit.getEmail().orElse(null));
		if(condutorEdit.getPassword() != null)
			newCondutor.setPassword(condutorEdit.getPassword().orElse(null));
		if(condutorEdit.getNif() != null)
			if(condutorEdit.getNif().isPresent())
				newCondutor.setNif(condutorEdit.getNif().get());
		if(condutorEdit.getNr_telemovel() != null)
			if(condutorEdit.getNr_telemovel().isPresent())
				newCondutor.setNrTelemovel(condutorEdit.getNr_telemovel().get());

		condutor = (Condutor) utilizadorServiceBean.atualizarUtilizador(newCondutor);
		return true;
	}

	/**
	 * Função que devolve a lista das reservas efetuadas pelo condutor com o 'login' efetuado
	 *
	 * @return Lista das reservas efetuadas pelo condutor
	 */
	public List<Reserva> listarMinhasReservas() throws Exception {
		checkIsLoggedIn();
		return reservaServiceBean.listarReservas(condutor.getId());
	}

	/**
	 * Retorna a reserva instantânea criada em caso de sucesso.
	 *
	 * @param id_parque identificador do parque onde queremos efetuar uma reserva instantânea
	 */
	public Reserva fazerReservaInstantanea(int id_parque) throws Exception{
		checkIsLoggedIn();
		return parqueReservaServiceBean.criarReservaInstantanea(condutor.getId(), id_parque);
	}

	/**
	 * Retorna a reserva agendada criada em caso de sucesso.
	 *
	 * @param id_parque   identificador do parque onde a reserva vai ser efetuada
	 * @param tipo_lugar  tipo do lugar de estacionamento que se prentende reservar
	 * @param data_inicio data de início da ocupação do lugar
	 * @param data_fim    data de fim da ocupação do lugar
	 */
	public Reserva fazerReservaAgendada(int id_parque, String tipo_lugar, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception {
		checkIsLoggedIn();
		return parqueReservaServiceBean.criarReservaAgendada(condutor.getId(),id_parque,new TipoLugarEstacionamento(tipo_lugar),data_inicio,data_fim);
	}

	/**
	 * Função que atualiza uma reserva para paga.
	 * @param id_reserva identicador da reserva que vai ser paga
	 * @return Retorna verdadeiro se o reserva foi paga com sucesso.
	 */
	public boolean pagarReserva(int id_reserva) throws Exception {
		checkIsLoggedIn();
		Reserva reserva = reservaServiceBean.getReserva(id_reserva);
		if(reserva.getUtilizadorID() != condutor.getId())
			throw new Exception("A reserva pertence a outro utilizador.");
		return parqueReservaServiceBean.pagarReserva(id_reserva);
	}

	/**
	 *
	 */
	public void logout() throws Exception {
		checkIsLoggedIn();
		condutor = null;
	}

	private void checkIsLoggedIn() throws Exception {
		if(condutor == null)
			throw new Exception("Não tem sessão iniciada.");
	}
}