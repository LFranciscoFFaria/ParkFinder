package pt.uminho.di.aa.parkfinder.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.uminho.di.aa.parkfinder.logicaParques.DAOs.TipoLugarEstacionamentoDAO;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueService;
import pt.uminho.di.aa.parkfinder.logicaParques.model.HorarioPeriodo;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioLinear;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apiV1/seed")
public class SeedAPI {

    ParqueService parqueService;
    TipoLugarEstacionamentoDAO tipoLugarEstacionamentoDAO;

    @Autowired
    public SeedAPI(ParqueService parqueService, TipoLugarEstacionamentoDAO tipoLugarEstacionamentoDAO) {
        this.parqueService = parqueService;
        this.tipoLugarEstacionamentoDAO = tipoLugarEstacionamentoDAO;
    }

    @PostMapping
    public ResponseEntity<String> seed(){
        try {
            criarTiposLugarEParques();
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
}
