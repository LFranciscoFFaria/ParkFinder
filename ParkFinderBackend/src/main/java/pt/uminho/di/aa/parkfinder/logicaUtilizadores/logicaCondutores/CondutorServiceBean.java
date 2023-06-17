package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaReservas.ReservaServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorServiceBean;

import java.util.List;


@Component
@SessionScope
public class CondutorServiceBean implements CondutorService {

	private final UtilizadorServiceBean utilizadorServiceBean;
	private final ReservaServiceBean reservaServiceBean;
	private Condutor condutor = null;

	@Autowired
	public CondutorServiceBean(UtilizadorServiceBean utilizadorServiceBean, ReservaServiceBean reservaServiceBean) {
		this.utilizadorServiceBean = utilizadorServiceBean;
		this.reservaServiceBean = reservaServiceBean;
	}

	public void setCondutor(Utilizador utilizador) {
		this.condutor = (Condutor) utilizador;
	}


	/* ******** Métodos do serviço ******** */

	/**
	 * 
	 * @param newCondutor Instância com os novos campos do perfil atualizados
	 */
	public boolean editarPerfil(Condutor newCondutor) throws Exception {
		if(condutor == null)
			throw new Exception("Não tem sessão iniciada.");
		System.out.println("Condutor logged in: " + condutor.getNome());
		newCondutor.setId(condutor.getId());
		utilizadorServiceBean.atualizarUtilizador(newCondutor);
		return true;
	}

	public List<Reserva> listarMinhasReservas() {
		// TODO - implement CondutorService.listarMinhasReservas
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 */
	public Reserva fazerReservaInstantanea(int id_parque) {
		// TODO - implement CondutorService.fazerReservaInstantanea
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_parque
	 * @param tipo
	 * @param data_inicio
	 * @param data_fim
	 */
	public Reserva fazerReservaAgendada(int id_parque, TipoLugarEstacionamento tipo, java.util.Date data_inicio, java.util.Date data_fim) {
		// TODO - implement CondutorService.fazerReservaAgendada
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_reserva
	 */
	public boolean pagarReserva(int id_reserva) {
		// TODO - implement CondutorService.pagarReserva
		throw new UnsupportedOperationException();
	}

	/**
	 * @return true if user was logged in. false if the user wasn't authenticated.
	 */
	public boolean logout() {
		if(condutor == null)
			return false;
		condutor = null;
		return true;
	}

}