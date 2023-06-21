package pt.uminho.di.aa.parkfinder.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.api.DTOs.serializers.DateTimeSerializer;
import pt.uminho.di.aa.parkfinder.api.DTOs.serializers.ReservaEstadoSerializer;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ReservaDTO {
    @JsonProperty("id")
    int id;

    @JsonProperty("id_utilizador")
    int id_utilizador;

    @JsonProperty("id_parque")
    int id_parque;

    @JsonProperty("tipo_lugar")
    String tipo_lugar;

    @JsonSerialize(using = ReservaEstadoSerializer.class)
    @JsonProperty("estado")
    int estado;

    @JsonProperty("custo")
    float custo;

    @JsonProperty("pago")
    boolean pago;

    @JsonProperty("matricula")
    String matricula;

    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonProperty("data_inicio")
    LocalDateTime data_inicio;

    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonProperty("data_fim")
    LocalDateTime data_fim;
}
