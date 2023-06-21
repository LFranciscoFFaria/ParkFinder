package pt.uminho.di.aa.parkfinder.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.uminho.di.aa.parkfinder.logicaNotificacoes.Notificacao;
import pt.uminho.di.aa.parkfinder.logicaNotificacoes.NotificationService;
import pt.uminho.di.aa.parkfinder.logicaParques.DAOs.LugarEstacionamentoDAO;
import pt.uminho.di.aa.parkfinder.logicaParques.DAOs.TipoLugarEstacionamentoDAO;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueService;
import pt.uminho.di.aa.parkfinder.logicaParques.model.HorarioPeriodo;
import pt.uminho.di.aa.parkfinder.logicaParques.model.LugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioLinear;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParquesReservas.EstadoReserva;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaReservas.ReservaService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.Condutor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.CondutorService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.CondutorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorService;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/apiV1/seed")
public class SeedAPI {

    ParqueService parqueService;
    TipoLugarEstacionamentoDAO tipoLugarEstacionamentoDAO;
    UtilizadorService utilizadorService;
    ReservaService reservaService;
    LugarEstacionamentoDAO lugarEstacionamentoDAO;
    NotificationService notificationService;
    CondutorServiceBean condutorServiceBean;

    @Autowired
    public SeedAPI(ParqueService parqueService, TipoLugarEstacionamentoDAO tipoLugarEstacionamentoDAO, UtilizadorService utilizadorService, ReservaService reservaService, LugarEstacionamentoDAO lugarEstacionamentoDAO, NotificationService notificationService, CondutorServiceBean condutorServiceBean) {
        this.parqueService = parqueService;
        this.tipoLugarEstacionamentoDAO = tipoLugarEstacionamentoDAO;
        this.utilizadorService = utilizadorService;
        this.reservaService = reservaService;
        this.lugarEstacionamentoDAO = lugarEstacionamentoDAO;
        this.notificationService = notificationService;
        this.condutorServiceBean = condutorServiceBean;
    }

    @PostMapping
    public ResponseEntity<String> seed(){
        try {
            criarTiposLugarEParques();
            criarUtilizadoresSimples();
            criarReservas();
            criarNotificacoes();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private void criarTiposLugarEParques() throws Exception {
        //Criacao dos tipos de lugares
        TipoLugarEstacionamento tipoAgendado = new TipoLugarEstacionamento("Agendado");
        tipoAgendado = tipoLugarEstacionamentoDAO.save(tipoAgendado);
        TipoLugarEstacionamento tipoInstantaneo = new TipoLugarEstacionamento("Instantaneo");
        tipoInstantaneo = tipoLugarEstacionamentoDAO.save(tipoInstantaneo);

        //Criacao 1º parque
        Parque p1 = new Parque("PARQUE VISCONDE DO RAIO", "rua dos reis", "Public covered parking\\n7 min. walk from the heart of the city\\nAccessible from Monday to Friday from 8:00 am to 8:00 pm and Saturdays from 10:00 am to 8:00 pm.",
                41.5495639F,-8.4216469F,true, 80,80,80,
                "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c");
        p1.getHorario().addPeriodos(createHorario(LocalTime.of(9,0), LocalTime.of(18,0)));
        p1 = parqueService.criarParque(p1);
        parqueService.addLugares(p1.getId(), tipoAgendado, 10);
        PrecarioLinear precarioLinear1 = new PrecarioLinear();

        //Criacao 2º parque
        Parque p2 = new Parque("B&B BRAGA LAMAÇÃES", "rua das rainhas", "Covered Hotel Parking\\n10 min. from University of Minho\\ntaxi service Accessible 24/7",
                41.5526295F,-8.3975434F,true, 50,50,50,
                "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c");
        p2.getHorario().addPeriodos(createHorario(LocalTime.of(9,0), LocalTime.of(13,0)));
        p2.getHorario().addPeriodos(createHorario(LocalTime.of(14,0), LocalTime.of(19,0)));
        p2 = parqueService.criarParque(p2);
        parqueService.addLugares(p2.getId(), tipoAgendado, 30);

        //Criacao 3º parque
        Parque p3 = new Parque("BRAGA PARQUE", "rua do braga parque", "Public covered Parking\\nUnder the citizen's house from Braga\\nAccessible 24/7",
                41.5577709F,-8.4086352F,true, 300,300,300,
                "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c");
        p3.getHorario().addPeriodos(createHorario(LocalTime.of(9,30), LocalTime.of(23,30)));
        p3 = parqueService.criarParque(p3);
    }

    private List<HorarioPeriodo> createHorario(LocalTime inicio, LocalTime fim){
        List<HorarioPeriodo> horarioPeriodos = new ArrayList<>();
        for(int dia = DayOfWeek.MONDAY.getValue(); dia <= DayOfWeek.SUNDAY.getValue(); dia++){
            horarioPeriodos.add(new HorarioPeriodo(0, inicio, fim, dia));
        }
        return horarioPeriodos;
    }

    private void criarUtilizadoresSimples() throws Exception{
        //Criar Gestor
        //public Gestor(String nome,String email,String password, int nrTelemovel, Set<Parque> parques, Set<Administrador> admins)
         Gestor gestor = new Gestor("Gestor" + 1, "gestor" + 1 + "@yahoo.com", "g" + 1, 960000000 + 1, new HashSet<>(), new HashSet<>());
        utilizadorService.criarUtilizador(gestor);
        //Criar Admins
        //Administrador(String nome, String email, String password, int nrTelemovel, Gestor gestor, Set<Parque> parques)
        Administrador administrador = new Administrador("Administrador" + 2, "administrador" + 2 + "@hotmail.com", "a" + 2, 930000000 + 2, gestor, new HashSet<>());
        utilizadorService.criarUtilizador(administrador);
        //Criar Condutores
        //Condutor condutor = new Condutor(@JsonProperty("nome") String nome, @JsonProperty("email") String email, @JsonProperty("password") String password,
        //@JsonProperty("nif") int nif, @JsonProperty("genero") boolean genero, @JsonProperty("numero_telemovel") int numero_telemovel));
        Condutor condutor = new Condutor("Condutor" + 3, "condutor" + 3 + "@gmail.com", "c" + 3, 100000000 + 3,true, 910000000 + 3);
        utilizadorService.criarUtilizador(condutor);
    }

    /**
     * Introduzir multiplos de 10 nesta função
     * @param n numero de utilizadores a criar
     */
    private void criarUtilizadores (int n) throws Exception{
        for (int j = 0; j < n/10; j++) {
            Gestor gestor = new Gestor();
            for (int i = 1; i < 11; i++) {
                int k = (i + 10 * j);
                if (i % 10 == 1) {
                    //Criar Gestor
                    //public Gestor(String nome,String email,String password, int nrTelemovel, Set<Parque> parques, Set<Administrador> admins)
                    gestor = new Gestor("Gestor" + k, "gestor" + k + "@yahoo.com", "g" + k, 960000000 + k, new HashSet<>(), new HashSet<>());
                    utilizadorService.criarUtilizador(gestor);
                } else if (i % 10 == 2 || i % 10 == 3) {
                    //Criar Admins
                    //Administrador(String nome, String email, String password, int nrTelemovel, Gestor gestor, Set<Parque> parques)
                    Administrador administrador = new Administrador("Administrador" + k, "administrador" + k + "@hotmail.com", "a" + k, 930000000 + k, gestor, new HashSet<>());
                    utilizadorService.criarUtilizador(administrador);
                } else {
                    //Criar Condutores
                    //Condutor condutor = new Condutor(@JsonProperty("nome") String nome, @JsonProperty("email") String email, @JsonProperty("password") String password,
                    //@JsonProperty("nif") int nif, @JsonProperty("genero") boolean genero, @JsonProperty("numero_telemovel") int numero_telemovel));
                    Condutor condutor = new Condutor("Condutor" + k, "condutor" + k + "@gmail.com", "c" + k, 100000000 + k, i % 2 == 1, 910000000 + k);
                    utilizadorService.criarUtilizador(condutor);
                }
            }
        }
    }

    //private void criarReservas() throws Exception{
    //    Condutor condutor = (Condutor) utilizadorService.getUtilizador(3);
    //    Parque parque1 = parqueService.procurarParque(1);
    //    TipoLugarEstacionamento tipoAgendado = new TipoLugarEstacionamento("Agendado");
    //    LocalDateTime data_inicio = LocalDateTime.of(2023,6,24,11, 0,0);
    //    LocalDateTime data_fim = LocalDateTime.of(2023,6,24,12, 0,0);
    //    List<Integer> lugaresDisponiveis_parque1 = parqueService.procurarLugaresDisponiveis(1,tipoAgendado,data_inicio,data_fim);
    //    LugarEstacionamento lugarEstacionamento = null;
    //    Parque parque3 = parqueService.procurarParque(3);
    //    Reserva reserva_inst = new Reserva(condutor, null, parque3, EstadoReserva.AGENDADA, null, false, null, data_inicio, null);
    //    reservaService.criarReserva(reserva_inst);
    //    if(lugaresDisponiveis_parque1.size()>0)
    //        lugarEstacionamento = lugarEstacionamentoDAO.findById(lugaresDisponiveis_parque1.get(0)).orElse(null);
    //    else
    //        throw new Exception("Não existem lugares disponíveis.");
    //    //Reserva(Utilizador utilizador, LugarEstacionamento lugar, Parque parque, int estado, Float custo, boolean pago, String matricula, LocalDateTime dataInicio, LocalDateTime dataFim)
    //    Reserva reserva_agendada = new Reserva(condutor, lugarEstacionamento, parque1, EstadoReserva.PENDENTE_PAGAMENTO, null, false, null, data_inicio, data_fim);
    //    reservaService.criarReserva(reserva_agendada);
    //}

    private void criarReservas() throws Exception{
        Condutor condutor = (Condutor) utilizadorService.getUtilizador(3);
        TipoLugarEstacionamento tipoAgendado = new TipoLugarEstacionamento("Agendado");
        LocalDateTime data_inicio = LocalDateTime.of(2023,6,24,11, 0,0);
        LocalDateTime data_fim = LocalDateTime.of(2023,6,24,12, 0,0);
        condutorServiceBean.setCondutor(condutor);
        condutorServiceBean.fazerReservaInstantanea(3);
        condutorServiceBean.fazerReservaAgendada(1,tipoAgendado.getNome(),data_inicio,data_fim);
    }

    private void criarNotificacoes() throws Exception{
        Condutor condutor = (Condutor) utilizadorService.getUtilizador(3);
        Parque parque1 = parqueService.procurarParque(1);
        String nome_parque = parque1.getNome();
        LocalDateTime hora_notificacao = LocalDateTime.of(2023,6,20,11, 55,0);
        Notificacao notificacao = new Notificacao(0,condutor,condutor.getId(),"Reserva agendada a acabar!", "A sua reserva agendada do "+nome_parque+" vai a acabar em 5 minutos!",false,hora_notificacao);
        notificationService.addNotificacao(notificacao);
    }

}
