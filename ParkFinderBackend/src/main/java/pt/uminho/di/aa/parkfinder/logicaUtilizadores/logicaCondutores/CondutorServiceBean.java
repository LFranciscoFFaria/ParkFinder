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

	/**
	 * Função que devolve a lista das reservas efetuadas pelo condutor com o 'login' efetuado
	 * @return Lista das reservas efetuadas pelo condutor
	 */
	public List<Reserva> listarMinhasReservas(){
		return reservaServiceBean.listarReservas(condutor.getId());
	}

	/**
	 * Retorna a reserva instantânea criada em caso de sucesso.
	 * @param id_parque identificador do parque onde queremos efetuar uma reserva instantânea
	 */
	public Reserva fazerReservaInstantanea(int id_parque) throws Exception{
		return parqueReservaServiceBean.criarReservaInstantanea(condutor.getId(),id_parque);
	}

	/**
	 * Retorna a reserva agendada criada em caso de sucesso.
	 * @param id_parque identificador do parque onde a reserva vai ser efetuada
	 * @param tipo tipo do lugar que se prentende reservar
	 * @param data_inicio data de início da ocupação do lugar
	 * @param data_fim data de fim da ocupação do lugar
	 */
	public Reserva fazerReservaAgendada(int id_parque, TipoLugarEstacionamento tipo, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception {
		return parqueReservaServiceBean.criarReservaAgendada(condutor.getId(),id_parque,tipo,data_inicio,data_fim);
	}

	/**
	 * Função que atualiza uma reserva para paga.
	 * @param id_reserva identicador da reserva que vai ser paga
	 * @return Retorna verdadeiro se o reserva foi paga com sucesso.
	 */
	public boolean pagarReserva(int id_reserva) throws Exception {
		return parqueReservaServiceBean.pagarReserva(id_reserva);
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