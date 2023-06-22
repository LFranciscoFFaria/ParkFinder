package pt.uminho.di.aa.parkfinder.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pt.uminho.di.aa.parkfinder.api.DTOs.deserializers.TimeDeserializer;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioLinear;

import java.time.LocalTime;

@Getter
@Setter
@ToString
public class PrecarioLinearCriarDTO extends PrecarioBaseDTO{
    float preco_por_intervalo;
    @JsonDeserialize(using = TimeDeserializer.class)
    LocalTime intervalo;

    public PrecarioLinearCriarDTO() {}

    public PrecarioLinearCriarDTO(@JsonProperty("id_parque") Integer id_parque,
                                  @JsonProperty("tipo_lugar") String tipo_lugar,
                                  @JsonProperty("preco_fixo") float preco_fixo,
                                  @JsonProperty("preco_por_intervalo") float preco_por_intervalo,
                                  @JsonProperty("intervalo") LocalTime intervalo) {
        super("linear", id_parque, tipo_lugar, preco_fixo);
        this.preco_por_intervalo = preco_por_intervalo;
        this.intervalo = intervalo;
    }
}
