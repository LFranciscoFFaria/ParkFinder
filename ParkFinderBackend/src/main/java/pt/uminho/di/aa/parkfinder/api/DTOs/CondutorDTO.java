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
    @JsonProperty("id")
    Integer id;

    @JsonProperty("nome")
    String nome;

    @JsonProperty("email")
    String email;

    @JsonProperty("nr_telemovel")
    Integer nr_telemovel;

    @JsonProperty("password")
    String password;

    @JsonProperty("nif")
    Integer nif;

    @JsonSerialize(using = GeneroSerializer.class)
    @JsonDeserialize(using = GeneroDeserializer.class)
    @JsonProperty("genero")
    boolean genero;

    public CondutorDTO(@JsonProperty("nome")String nome, @JsonProperty("email") String email,@JsonProperty("nr_telemovel") Integer nr_telemovel, @JsonProperty("password")String password, @JsonProperty("nif")Integer nif,@JsonProperty("genero") boolean genero) {
        this.nome = nome;
        this.email = email;
        this.nr_telemovel = nr_telemovel;
        this.password = password;
        this.nif = nif;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "CondutorDTO{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", nr_telemovel=" + nr_telemovel +
                ", password='" + password + '\'' +
                ", nif=" + nif +
                ", genero=" + genero +
                '}';
    }
}
