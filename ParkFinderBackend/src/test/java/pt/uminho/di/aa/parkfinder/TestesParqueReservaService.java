package pt.uminho.di.aa.parkfinder;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParquesReservas.EstadoReserva;
import pt.uminho.di.aa.parkfinder.logicaParquesReservas.ParqueReservaServiceBean;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaReservas.ReservaServiceBean;

import java.time.LocalDateTime;

@SpringBootTest
public class TestesParqueReservaService {

    private final ParqueReservaServiceBean parqueReservaServiceBean;

    private final ReservaServiceBean reservaServiceBean;

    @Autowired
    public TestesParqueReservaService(ParqueReservaServiceBean parqueReservaServiceBean, ReservaServiceBean reservaServiceBean) {
        this.parqueReservaServiceBean = parqueReservaServiceBean;
        this.reservaServiceBean = reservaServiceBean;
    }

    void inicializarValores() throws Exception{
        reservaServiceBean.setEstado(2, EstadoReserva.PENDENTE_PAGAMENTO);
        reservaServiceBean.setPago(2,false);
        reservaServiceBean.setCusto(2,0f);
        reservaServiceBean.setMatricula(2, null);
    }

    @Test
    @Transactional
    void TestarParqueReservaService()throws Exception{

        inicializarValores();

        testarPagarReserva();

        testarMarcarEntradaParque();

        testarMarcarSaidaParque();

        testarCriarReservaInstantanea();

        testarCriarReservaAgendada();

    }

    void testarPagarReserva() throws Exception{
        Reserva reserva2 = reservaServiceBean.getReserva(2);

        assert reserva2.getEstado() == EstadoReserva.PENDENTE_PAGAMENTO;

        assert !reserva2.isPago();

        parqueReservaServiceBean.pagarReserva(2);

        System.out.println("testarPagarReserva:\n");

        System.out.println(reservaServiceBean.getReserva(2)+"\n"+"\n"+"\n");
    }

    void testarMarcarEntradaParque() throws Exception{
        Reserva reserva2 = reservaServiceBean.getReserva(2);

        assert reserva2.getEstado() == EstadoReserva.AGENDADA;

        assert reserva2.getMatricula() == null;

        parqueReservaServiceBean.marcarEntradaParque(2,"24-IN-64");

        assert reserva2.getMatricula().equals("24-IN-64");

        assert reserva2.getEstado() == EstadoReserva.OCUPADA;

        System.out.println("testarMarcarEntradaParque:\n");

        System.out.println(reservaServiceBean.getReserva(2)+"\n"+"\n"+"\n");
    }

    void testarMarcarSaidaParque() throws Exception{
        Reserva reserva2 = reservaServiceBean.getReserva(2);

        assert reserva2.getEstado() == EstadoReserva.OCUPADA;

        parqueReservaServiceBean.marcarSaidaParque(2);

        assert reserva2.getEstado() == EstadoReserva.CONCLUIDA;

        System.out.println("testarMarcarSaidaParque:\n");

        System.out.println(reservaServiceBean.getReserva(2)+"\n"+"\n"+"\n");
    }

    void testarCriarReservaInstantanea() throws Exception{

        Reserva reserva_inst = parqueReservaServiceBean.criarReservaInstantanea(3,9);

        assert reserva_inst != null;

        System.out.println("testarCriarReservaInstantanea:\n");

        System.out.println(reserva_inst+"\n"+"\n"+"\n");
    }

    void testarCriarReservaAgendada() throws Exception{
        TipoLugarEstacionamento tipoLugarEstacionamento = new TipoLugarEstacionamento("Agendado");

        LocalDateTime data_inicio = LocalDateTime.of(2023,6,21,11, 0,0);
        LocalDateTime data_fim = LocalDateTime.of(2023,7,15,12, 0,0);

        Reserva reserva_agendada = parqueReservaServiceBean.criarReservaAgendada(3,9,tipoLugarEstacionamento,data_inicio,data_fim);

        assert reserva_agendada != null;

        System.out.println("testarCriarReservaAgendada:\n");

        System.out.println(reserva_agendada+"\n"+"\n"+"\n");
    }


    @Test
    void testarCriarReservaAgendada2() throws Exception{
        TipoLugarEstacionamento tipoLugarEstacionamento = new TipoLugarEstacionamento("Agendado");

        LocalDateTime data_inicio = LocalDateTime.of(2023,6,22,11, 0,0);
        LocalDateTime data_fim = LocalDateTime.of(2023,6,22,12, 0,0);

        Reserva reserva_agendada = parqueReservaServiceBean.criarReservaAgendada(3,21,tipoLugarEstacionamento,data_inicio,data_fim);

        assert reserva_agendada != null;

        System.out.println("testarCriarReservaAgendada:\n");

        System.out.println(reserva_agendada+"\n"+"\n"+"\n");
    }

}
