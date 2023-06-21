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
    String nome;

    String email;

    Integer nr_telemovel;

    String password;

    Integer nif;

    @JsonSerialize(using = GeneroSerializer.class)
    @JsonDeserialize(using = GeneroDeserializer.class)
    boolean genero;
}
