package pt.uminho.di.aa.parkfinder.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.PrecarioBaseDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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

    @PutMapping
    void randomtest(@RequestBody List<PrecarioBaseDTO> lista){
        lista.forEach(System.out::println);
    }
}
