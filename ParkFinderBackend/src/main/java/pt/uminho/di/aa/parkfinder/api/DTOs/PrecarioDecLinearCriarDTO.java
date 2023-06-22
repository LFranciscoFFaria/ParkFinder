package pt.uminho.di.aa.parkfinder.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pt.uminho.di.aa.parkfinder.api.DTOs.deserializers.TimeDeserializer;

import java.time.LocalTime;

@Getter
@Setter
@ToString
public class PrecarioDecLinearCriarDTO extends PrecarioBaseDTO{
    float preco_max_por_intervalo;
    float preco_min_por_intervalo;
    @JsonDeserialize(using = TimeDeserializer.class)
    LocalTime intervalo_ate_min;
    @JsonDeserialize(using = TimeDeserializer.class)
    LocalTime intervalo;

    public PrecarioDecLinearCriarDTO() {
    }

    public PrecarioDecLinearCriarDTO(@JsonProperty("id_parque") Integer id_parque, @JsonProperty("tipo_lugar") String tipo_lugar,
                                     @JsonProperty("preco_fixo") float preco_fixo,
                                     @JsonProperty("preco_max_por_intervalo") float preco_max_por_intervalo,
                                     @JsonProperty("preco_min_por_intervalo") float preco_min_por_intervalo,
                                     @JsonProperty("intervalo_ate_min") LocalTime intervalo_ate_min,
                                     @JsonProperty("intervalo") LocalTime intervalo) {
        super("dec_linear", id_parque, tipo_lugar, preco_fixo);
        this.preco_max_por_intervalo = preco_max_por_intervalo;
        this.preco_min_por_intervalo = preco_min_por_intervalo;
        this.intervalo_ate_min = intervalo_ate_min;
        this.intervalo = intervalo;
    }
}
