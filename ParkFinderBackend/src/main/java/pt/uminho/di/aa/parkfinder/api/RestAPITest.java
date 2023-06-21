package pt.uminho.di.aa.parkfinder.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioLinear;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@RestController()
@RequestMapping("/testes")
public class RestAPITest {

    @Getter
    @Setter
    public static class Teste{
        @JsonProperty(value = "date")
        LocalDateTime dateTime;
        Optional<LocalTime> time;
    }

    @GetMapping
    Precario randomtest(@RequestBody PrecarioLinear precario){
        System.out.println(precario);
        return precario;
    }
}
