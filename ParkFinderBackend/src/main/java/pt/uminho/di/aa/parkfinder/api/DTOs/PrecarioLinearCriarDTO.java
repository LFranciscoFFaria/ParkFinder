package pt.uminho.di.aa.parkfinder.api.DTOs;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.api.DTOs.deserializers.TimeDeserializer;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class PrecarioLinearCriarDTO {
    String tipo_lugar;
    float preco_fixo;
    float preco_por_intervalo;
    @JsonDeserialize(using = TimeDeserializer.class)
    LocalTime intervalo;
}
