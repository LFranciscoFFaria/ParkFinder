package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class CondutorEdit {
    @JsonProperty
    Optional<String> nome;

    @JsonProperty
    Optional<String> email;

    @JsonProperty
    Optional<Integer> nr_telemovel;

    @JsonProperty
    Optional<String> password;

    @JsonProperty
    Optional<Integer> nif;
}
