package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueServiceBean;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaReservas.ReservaServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;

import java.util.List;


@Component
@SessionScope
public class AdministradorServiceBean implements AdministradorService {

	private final ParqueServiceBean parqueServiceBean;
	private final ReservaServiceBean reservaServiceBean;
	private Administrador administrador = null;

	public AdministradorServiceBean(ParqueServiceBean parqueServiceBean, ReservaServiceBean reservaServiceBean) {
		this.parqueServiceBean = parqueServiceBean;
		this.reservaServiceBean = reservaServiceBean;
	}

	/**
	 * Função que adiciona o número de lugares instântaneos especificados ao parque.
	 * @param id_parque identificador do parque
	 * @param N número de lugares instantâneos a adicionar
	 */
	public void addLugarInstantaneo(int id_parque,int N) throws Exception {
		if (N<1)
			throw new Exception("O número de lugares a adicionar tem de ser 1 ou maior.");
		parqueServiceBean.addLugaresInstantaneos(id_parque, N);
	}

	/**
	 * Função que adiciona o número de lugares especiais especificados ao parque.
	 * @param id_parque identificador do parque
	 * @param N número de lugares especiais a adicionar
	 * @param tipo tipo do lugar especial
	 */
	public void addLugarEspecial(int id_parque, int N, TipoLugarEstacionamento tipo) throws Exception {
		if (N<1)
			throw new Exception("O número de lugares a adicionar tem de ser 1 ou maior.");
		int i = 0;
		while(i<N) {
			//TODO: Esta função assim parece um crime, mas vou deixar o Alex corrigir :)
			parqueServiceBean.addLugar(id_parque, tipo);
			i--;
		}
	}

	/**
	 * Função que remove o número de lugares instântaneos especificados ao parque.
	 * @param id_parque identificador do parque
	 * @param N número de lugares instantâneos a remover
	 */
	public void removerLugarInstantaneo(int id_parque,int N) throws Exception {
		if (N<1)
			throw new Exception("O número de lugares a remover tem de ser 1 ou maior.");
		parqueServiceBean.removeLugaresInstantaneos(id_parque, N);
	}

	/**
	 * Função que remove o número de lugares especiais especificados ao parque.
	 * @param id_parque identificador do parque
	 * @param N número de lugares especiais a remover
	 * @param tipo tipo do lugar especial
	 */
	public void removerLugarEspecial(int id_parque, int N, TipoLugarEstacionamento tipo) throws Exception {
		if (N<1)
			throw new Exception("O número de lugares a adicionar tem de ser 1 ou maior.");
		int i = 0;
		while(i<N) {
			//TODO: Esta função assim parece um crime, mas vou deixar o Alex corrigir :)
			parqueServiceBean.removerLugar(id_parque, tipo);
			i--;
		}
	}

	/**
	 * Função que retorna uma reserva se a matrícula passada por argumento estiver numa reserva com o estado OCUPADA.
	 * @param matricula matricula do carro
	 */
	public Reserva encontrarReservaPorMatricula(String matricula) throws Exception {
		return reservaServiceBean.getReservaMatricula(matricula);
	}

	/**
	 * Associa a matrícula passada por argumento à reserva.
	 * @param id_reserva identificador da reserva à qual queremos alterar a matrícula
	 * @param matricula matricula nova a associar
	 */
	public boolean associarMatriculaAReserva(int id_reserva, String matricula) throws Exception {
		reservaServiceBean.setMatricula(id_reserva,matricula);
		return true;
	}

	/**
	 * Função que retorna a lista das reservas ativas do parque.
	 * @param id_parque identificador do parque
	 */
	public List<Reserva> verReservasAtivasDeParque(int id_parque) {
		return reservaServiceBean.getReservasParque(id_parque);
	}

	public void logout() {
		// TODO: No condutor está função retorna um boleano não sei qual é suposto ser
		administrador = null;
	}

    public void setAdministrador(Utilizador u) {
		this.administrador = (Administrador) u;
    }
}