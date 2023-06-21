package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pt.uminho.di.aa.parkfinder.api.DTOs.CondutorEditDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.ReservaDTO;
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
	 * @param condutorDTO Instância com os novos campos do perfil atualizados
	 */
	public boolean editarPerfil(CondutorEditDTO condutorDTO) throws Exception {
		checkIsLoggedIn();

		Condutor newCondutor = condutor.clone();
		if(condutorDTO.getNome() != null)
			newCondutor.setNome(condutorDTO.getNome().orElse(null));
		if(condutorDTO.getEmail() != null)
			newCondutor.setEmail(condutorDTO.getEmail().orElse(null));
		if(condutorDTO.getPassword() != null)
			newCondutor.setPassword(condutorDTO.getPassword().orElse(null));
		if(condutorDTO.getNif() != null)
			if(condutorDTO.getNif().isPresent())
				newCondutor.setNif(condutorDTO.getNif().get());
		if(condutorDTO.getNr_telemovel() != null)
			if(condutorDTO.getNr_telemovel().isPresent())
				newCondutor.setNrTelemovel(condutorDTO.getNr_telemovel().get());

		condutor = (Condutor) utilizadorServiceBean.atualizarUtilizador(newCondutor);
		return true;
	}

	/**
	 * Função que devolve a lista das reservas efetuadas pelo condutor com o 'login' efetuado
	 * @return Lista das reservas efetuadas pelo condutor
	 */
	public List<ReservaDTO> listarMinhasReservas() throws Exception {
		checkIsLoggedIn();
		return reservaServiceBean.listarReservas(condutor.getId())
								 .stream()
								 .map(this::reservaToDTO)
								 .toList();
	}

	/**
	 * Retorna a reserva instantânea criada em caso de sucesso.
	 * @param id_parque identificador do parque onde queremos efetuar uma reserva instantânea
	 */
	public ReservaDTO fazerReservaInstantanea(int id_parque) throws Exception{
		checkIsLoggedIn();
		Reserva r = parqueReservaServiceBean.criarReservaInstantanea(condutor.getId(), id_parque);
		return reservaToDTO(r);
	}

	/**
	 * Retorna a reserva agendada criada em caso de sucesso.
	 * @param id_parque identificador do parque onde a reserva vai ser efetuada
	 * @param tipo_lugar tipo do lugar de estacionamento que se prentende reservar
	 * @param data_inicio data de início da ocupação do lugar
	 * @param data_fim data de fim da ocupação do lugar
	 */
	public ReservaDTO fazerReservaAgendada(int id_parque, String tipo_lugar, LocalDateTime data_inicio, LocalDateTime data_fim) throws Exception {
		checkIsLoggedIn();
		Reserva r = parqueReservaServiceBean.criarReservaAgendada(condutor.getId(),id_parque,new TipoLugarEstacionamento(tipo_lugar),data_inicio,data_fim);
		return reservaToDTO(r);
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

	private ReservaDTO reservaToDTO(Reserva r){
		return new ReservaDTO(r.getId(), r.getUtilizadorID(), r.getParqueID(), r.getEstado(),
				r.getCusto(), r.isPago(), r.getMatricula(), r.getDataInicio(), r.getDataFim());
	}
}