package pt.uminho.di.aa.parkfinder;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueServiceBean;
import pt.uminho.di.aa.parkfinder.logicaParques.model.LugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParquesReservas.EstadoReserva;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaReservas.ReservaServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.Condutor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorServiceBean;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class TestesReservaService {

    private final ParqueServiceBean parqueServiceBean;

    private final UtilizadorServiceBean utilizadorServiceBean;

    private final ReservaServiceBean reservaServiceBean;

    @Autowired
    public TestesReservaService(ParqueServiceBean parqueServiceBean, UtilizadorServiceBean utilizadorServiceBean, ReservaServiceBean reservaServiceBean) {
        this.parqueServiceBean = parqueServiceBean;
        this.utilizadorServiceBean = utilizadorServiceBean;
        this.reservaServiceBean = reservaServiceBean;
    }

    @Test
    @Transactional
    void TestarReservaService()throws Exception{

        //FAZER RESERVA

        testeCriarReservaInstantanea();

        testeCriarReservaAgendada();

        //GET RESERVA

        testeGetReserva();

        //REMOVER RESERVA

        testeRemoverReserva();

        //GET RESERVA PARQUE

        testeGetReservaParque();

        //LISTAR RESERVAS USER

        testeListarReservasUser();

        //SET CUSTO

        testeSetCusto();

        //SET ALL

        testeSetAll();

        //GET RESERVA MATRICULA

        testeGetReservaMatricula();
        
    }

    void testeCriarReservaInstantanea() throws Exception{
        System.out.println("testeCriarReservaInstantanea:\n");

        Parque parque9 = parqueServiceBean.procurarParque(9);
        System.out.println(parque9+"\n");

        Condutor condutor = (Condutor) utilizadorServiceBean.getUtilizador(3);
        System.out.println(condutor+"\n");

        LocalDateTime data_inicio = LocalDateTime.of(2023,6,26,11, 0,0);

        Reserva reserva_instantanea = new Reserva(condutor,null,parque9, EstadoReserva.AGENDADA,5.0f,false,null,data_inicio,null);

        reservaServiceBean.criarReserva(reserva_instantanea);

        System.out.println(reserva_instantanea+"\n"+"\n"+"\n");
    }

    void testeCriarReservaAgendada() throws Exception{
        System.out.println("testeCriarReservaAgendada:\n");

        Parque parque9 = parqueServiceBean.procurarParque(9);
        System.out.println(parque9+"\n");

        LugarEstacionamento lugarEstacionamento = parqueServiceBean.getLugarById(55);
        System.out.println(lugarEstacionamento+"\n");

        Condutor condutor = (Condutor) utilizadorServiceBean.getUtilizador(3);
        System.out.println(condutor+"\n");

        LocalDateTime data_inicio = LocalDateTime.of(2023,6,26,11, 0,0);
        LocalDateTime data_fim = LocalDateTime.of(2023,6,26,12, 0,0);

        Reserva reserva_agendada = new Reserva(condutor,lugarEstacionamento,parque9, EstadoReserva.PENDENTE_PAGAMENTO,5.0f,false,null,data_inicio,data_fim);

        reservaServiceBean.criarReserva(reserva_agendada);

        System.out.println(reserva_agendada+"\n"+"\n"+"\n");
    }

    void testeGetReserva() throws Exception{
        System.out.println("testeGetReserva:\n");

        Reserva reserva1 = reservaServiceBean.getReserva(1);

        assert reservaServiceBean.getReserva(1) != null;

        System.out.println(reserva1+"\n"+"\n"+"\n");
    }

    void testeRemoverReserva() throws Exception{
        System.out.println("testeRemoverReserva:\n");

        reservaServiceBean.removerReserva(1);

        Reserva r = null;

        try {r = reservaServiceBean.getReserva(1);}
        catch (Exception ignored){}

        assert r == null;

        System.out.println(r+"\n"+"\n"+"\n");
    }

    void testeGetReservaParque() throws Exception {
        System.out.println("testeGetReservaParque:\n");

        System.out.println(reservaServiceBean.getReservasParque(9)+"\n");
    }

    void testeListarReservasUser() throws Exception {
        System.out.println("testeListarReservasUser:\n");

        System.out.println(reservaServiceBean.listarReservas(3)+"\n"+"\n"+"\n");
    }

    void testeSetCusto() throws Exception {
        System.out.println("testeSetCusto:\n");

        Reserva reserva2 = reservaServiceBean.getReserva(2);

        System.out.println(reserva2+"\n");

        reservaServiceBean.setCusto(2,3.0f);

        Reserva reserva2_new =  reservaServiceBean.getReserva(2);

        System.out.println(reserva2_new+"\n"+"\n"+"\n");
    }

    void testeSetAll() throws Exception {
        System.out.println("testeSetAll:\n");

        Reserva reserva2 = reservaServiceBean.getReserva(2);

        System.out.println(reserva2+"\n");

        LocalDateTime data_inicio = LocalDateTime.of(2023,6,25,11, 0,0);
        LocalDateTime data_fim = LocalDateTime.of(2023,6,25,12, 0,0);

        Reserva reserva2_new = reservaServiceBean.setAll(2,Optional.of(EstadoReserva.OCUPADA),Optional.of(false), Optional.of(4.0f),Optional.of(data_inicio),Optional.of(data_fim),Optional.of("37-WN-88"));

        System.out.println(reserva2_new+"\n"+"\n"+"\n");
    }

    void testeGetReservaMatricula() throws Exception {
        System.out.println("testeGetReservaMatricula:\n");

        Reserva reserva = reservaServiceBean.getReservaMatricula(9,"37-WN-88");

        System.out.println(reserva+"\n"+"\n"+"\n");
    }

}
