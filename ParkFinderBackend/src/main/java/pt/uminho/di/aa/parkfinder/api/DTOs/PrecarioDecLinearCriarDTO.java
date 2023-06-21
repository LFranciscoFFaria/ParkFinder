package pt.uminho.di.aa.parkfinder.api.DTOs;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.api.DTOs.deserializers.TimeDeserializer;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class PrecarioDecLinearCriarDTO {
    String tipo_lugar;
    float preco_fixo;
    float preco_max_por_intervalo;
    float preco_min_por_intervalo;
    @JsonDeserialize(using = TimeDeserializer.class)
    LocalTime intervalo_ate_min;
    @JsonDeserialize(using = TimeDeserializer.class)
    LocalTime intervalo;
}
