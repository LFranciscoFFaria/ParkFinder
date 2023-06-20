package pt.uminho.di.aa.parkfinder.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.api.DTOs.deserializers.GeneroDeserializer;
import pt.uminho.di.aa.parkfinder.api.DTOs.serializers.GeneroSerializer;

@Getter
@Setter
@AllArgsConstructor
public class CondutorDTO {
    @JsonProperty(required = true)
    String nome;

    @JsonProperty(required = true)
    String email;

    @JsonProperty(required = true)
    int nrTelemovel;

    @JsonProperty(required = true)
    String password;

    @JsonProperty(required = true)
    int nif;

    @JsonProperty(required = true)
    @JsonSerialize(using = GeneroSerializer.class)
    @JsonDeserialize(using = GeneroDeserializer.class)
    boolean genero;
}
