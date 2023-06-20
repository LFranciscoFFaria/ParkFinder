package pt.uminho.di.aa.parkfinder.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class CondutorEditDTO {
    @JsonProperty
    Optional<String> nome;

    @JsonProperty
    Optional<String> email;

    @JsonProperty
    Optional<Integer> nrTelemovel;

    @JsonProperty
    Optional<String> password;

    @JsonProperty
    Optional<Integer> nif;
}
