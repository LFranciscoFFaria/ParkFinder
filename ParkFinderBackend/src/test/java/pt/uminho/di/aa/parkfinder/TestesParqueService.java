package pt.uminho.di.aa.parkfinder;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueServiceBean;
import pt.uminho.di.aa.parkfinder.logicaParques.model.*;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioLinear;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
public class TestesParqueService {

    private final ParqueServiceBean parqueServiceBean;

    private Parque parque;


    @Autowired
    public TestesParqueService(ParqueServiceBean parqueServiceBean) {
        this.parqueServiceBean = parqueServiceBean;
    }

    private List<HorarioPeriodo> createHorario(LocalTime inicio, LocalTime fim) {
        List<HorarioPeriodo> horarioPeriodos = new ArrayList<>();
        for (int dia = DayOfWeek.MONDAY.getValue(); dia <= DayOfWeek.SUNDAY.getValue(); dia++) {
            horarioPeriodos.add(new HorarioPeriodo(0, inicio, fim, dia));
        }
        return horarioPeriodos;
    }

    @Test
    @Transactional
    void TestarParqueService() throws Exception {

        //LISTAR PARQUES

        listarParques();

        //ADICIONAR PRECARIOS

        adicionarPrecarios();

        //REMOVER PRECARIOS

        removerPrecarios();

        //PROCURAR LUGARES DISPONIVEIS

        procurarLugaresDisponiveis();

        // CRIAR PARQUE

        criarParque();

        // REMOVER PARQUE

        removerParque();

        // ADICIONAR LUGARES AO PARQUE

        adicionarLugaresAoParque();

        // REMOVER LUGARES AO PARQUE

        removerLugaresAoParque();

        // ADICIONAR HORARIOS

        adicionarHorario();

        // REMOVER HORARIOS

        //removerHorario();

        // VISUALIZAR ESTATISTICAS

        visualizarHorarios();

        //PROCURAR LUGAR DE ESTACIONAMENTO

        procurarLugarDeEstacionamento();

        //PROCURAR PARQUE

        procurarParque();

        //TODO: É um filtro se tivermos tempo vamos corrigir
        //LISTAR PARQUES MAIS LUGARES LIVRES

        //listarParquesMaisLugaresLivres();

    }

    //LISTAR PARQUES

    void listarParques() throws Exception {
        System.out.println("listarParques:\n");

        List<Parque> parqueList = parqueServiceBean.listarParques();

        if (parqueList.size() > 0) {
            for (Parque parque : parqueList) {
                System.out.println(parque.toString());
            }
        }

        System.out.println("\n\n");
    }

    //ADICIONAR PRECARIOS

    void adicionarPrecarios() throws Exception {
        System.out.println("adicionarPrecarios():\n");

        TipoLugarEstacionamento tipoAgendado = new TipoLugarEstacionamento("Agendado");
        LocalTime localTime = LocalTime.of(0, 15);
        Precario precario = new PrecarioLinear(tipoAgendado, 0.5f, 0.15f, localTime);
        parqueServiceBean.criarPrecario(9, precario);

        System.out.println(precario);

        System.out.println("\n\n");
    }

    //REMOVER PRECARIOS

    void removerPrecarios() throws Exception {
        System.out.println("removerPrecarios():\n");

        TipoLugarEstacionamento tipoAgendado = new TipoLugarEstacionamento("Agendado");
        parqueServiceBean.removerPrecario(1, tipoAgendado);

        List<Precario> precarioList = parqueServiceBean.getPrecarios(1);

        for (Precario p : precarioList) {
            assert p.getTipo().getNome().equals("Agendado");
        }

        System.out.println("null");

        System.out.println("\n\n");
    }

    //PROCURAR LUGARES DISPONIVEIS

    void procurarLugaresDisponiveis() throws Exception {
        //System.out.println("procurarLugaresDisponiveis():\n");

        //TODO: Ainda não está funcional
        //procurarLugaresDisponiveis(int id_parque,@NotNull  TipoLugarEstacionamento tipo,LocalDateTime data_inicio,LocalDateTime data_fim)
        //TipoLugarEstacionamento tipoAgendado1 = new TipoLugarEstacionamento("Agendado");
        //LocalDateTime data_inicio = LocalDateTime.of(2023,6,24,11, 0,0);
        //LocalDateTime data_fim = LocalDateTime.of(2023,6,24,12, 0,0);
        //List<Integer> ids = parqueServiceBean.procurarLugaresDisponiveis(1,tipoAgendado1,data_inicio,data_fim);
        //if (ids.size()>0){
        //    for (int id:ids){
        //        System.out.println(ids);
        //    }
        //}
    }

    // CRIAR PARQUE


    void criarParque() throws Exception{
        System.out.println("criarParque():\n");

        TipoLugarEstacionamento tipoAgendado1 = new TipoLugarEstacionamento("Agendado");
        Parque parque_new = new Parque("PARQUE PARA TESTE DO PARQUE SERVICE", "rua do novo parque", "Public covered Parking\\nUnder the citizen's house from Braga\\nAccessible 24/7",
                41.2385948F,-8.4960386F,true, 200,200,200,
                "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c");
        this.parque = parqueServiceBean.criarParque(parque_new);

        System.out.println(this.parque);

        Parque parque_remover = new Parque("PARQUE REMOVER", "rua do novo parque", "Public covered Parking\\nUnder the citizen's house from Braga\\nAccessible 24/7",
                41.2385948F,-8.4960386F,true, 200,200,200,
                "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c");
        parqueServiceBean.criarParque(parque_remover);

        System.out.println("\n\n");
    }

    // REMOVER PARQUE

    void removerParque() throws Exception{
        System.out.println("removerParque():\n");

        List<Parque> parques = parqueServiceBean.procurarParque("PARQUE REMOVER");

        assert parques.size()>0;

        parqueServiceBean.removerParque(parques.get(0).getId());

        System.out.println("\n\n");
    }

    // ADICIONAR LUGARES AO PARQUE

    void adicionarLugaresAoParque() throws Exception{
        System.out.println("adicionarLugaresAoParque():\n");

        TipoLugarEstacionamento tipoAgendado = new TipoLugarEstacionamento("Agendado");

        Parque p_antes = parqueServiceBean.procurarParque(this.parque.getId());

        System.out.println(p_antes);

        parqueServiceBean.addLugaresInstantaneos(this.parque.getId(),10);

        Parque p_meio = parqueServiceBean.procurarParque(this.parque.getId());

        System.out.println(p_meio);

        parqueServiceBean.addLugares(this.parque.getId(),tipoAgendado,10);

        Parque p_fim = parqueServiceBean.procurarParque(this.parque.getId());

        System.out.println(p_fim);

        System.out.println("\n\n");
    }

    // REMOVER LUGARES AO PARQUE

    void removerLugaresAoParque() throws Exception{
        System.out.println("removerLugaresAoParque():\n");

        TipoLugarEstacionamento tipoAgendado = new TipoLugarEstacionamento("Agendado");

        Parque p_antes = parqueServiceBean.procurarParque(this.parque.getId());

        System.out.println(p_antes);

        parqueServiceBean.removeLugaresInstantaneos(this.parque.getId(),5);

        Parque p_meio = parqueServiceBean.procurarParque(this.parque.getId());

        System.out.println(p_meio);

        parqueServiceBean.removerLugares(this.parque.getId(),tipoAgendado,5);

        Parque p_fim = parqueServiceBean.procurarParque(this.parque.getId());

        System.out.println(p_fim);

        System.out.println("\n\n");
    }

    // ADICIONAR HORARIOS
    // Se já houver um horário adicionado ao parque. Adicionar um horário deita o antigo fora

    void adicionarHorario()  throws Exception{
        System.out.println("adicionarHorario():\n");

        Horario h = new Horario(0, new HashSet<>(createHorario(LocalTime.of(9,0), LocalTime.of(23,0))));
        parqueServiceBean.setHorario(this.parque.getId(),h);

        System.out.println(parqueServiceBean.getHorario(this.parque.getId()));
        Horario h1 = new Horario(0, new HashSet<>(createHorario(LocalTime.of(7,0), LocalTime.of(22,0))));
        parqueServiceBean.setHorario(this.parque.getId(),h1);

        System.out.println(parqueServiceBean.getHorario(9));

        System.out.println("\n\n");
    }

    // VISUALIZAR ESTATISTICAS

    void visualizarHorarios() throws Exception {
        System.out.println("visualizarHorarios():\n");

        System.out.println(parqueServiceBean.getEstatisticasParque(this.parque.getId()));

        System.out.println("\n\n");
    }

    //PROCURAR LUGAR DE ESTACIONAMENTO

    void procurarLugarDeEstacionamento() throws Exception {
        System.out.println("procurarLugarDeEstacionamento:\n");

        TipoLugarEstacionamento tipoAgendado = new TipoLugarEstacionamento("Agendado");
        LocalDateTime data_inicio = LocalDateTime.of(2023,6,24,11, 0,0);
        LocalDateTime data_fim = LocalDateTime.of(2023,6,24,12, 0,0);
        List<Integer> lugar_ids = parqueServiceBean.procurarLugaresDisponiveis(this.parque.getId(),tipoAgendado,data_inicio,data_fim);
        System.out.println(lugar_ids);

        System.out.println("\n\n");
    }

    //PROCURAR PARQUE

    void procurarParque() throws Exception {
        System.out.println("procurarParque():\n");

        List<Parque> parqueList = parqueServiceBean.procurarParque("PARQUE PARA TESTE DO PARQUE SERVICE");

        System.out.println(parqueList.get(0));

        System.out.println("\n\n");
    }

    //TODO: É um filtro se tivermos tempo vamos corrigir
    //LISTAR PARQUES MAIS LUGARES LIVRES

    void listarParquesMaisLugaresLivres()throws Exception{
        //System.out.println(parqueServiceBean.listarParquesMaisLugaresLivres());
    }

    @Test
    void criarParque2() throws Exception{
        System.out.println("criarParque():\n");

        TipoLugarEstacionamento tipoAgendado1 = new TipoLugarEstacionamento("Agendado");
        Parque parque_new = new Parque("PARQUE PARA TESTE DO PARQUE SERVICE", "rua do novo parque", "Public covered Parking\\nUnder the citizen's house from Braga\\nAccessible 24/7",
                41.2385948F,-8.4960386F,true, 200,200,200,
                "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c");
        this.parque = parqueServiceBean.criarParque(parque_new);

        Horario h1 = new Horario(0, new HashSet<>(createHorario(LocalTime.of(0,0), LocalTime.of(23,59))));
        parqueServiceBean.setHorario(this.parque.getId(),h1);

        TipoLugarEstacionamento tipoAgendado = new TipoLugarEstacionamento("Agendado");

        parqueServiceBean.addLugares(this.parque.getId(),tipoAgendado,10);

        System.out.println(this.parque);

        System.out.println("\n\n");
    }

}
