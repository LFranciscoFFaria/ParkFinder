package pt.uminho.di.aa.parkfinder.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo_precario")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PrecarioDecLinearCriarDTO.class, name = "dec_linear"),
        @JsonSubTypes.Type(value = PrecarioLinearCriarDTO.class, name = "linear")
})
public abstract class PrecarioBaseDTO {
    @JsonProperty("tipo_precario")
    String tipo_precario;
    Integer id_parque;
    String tipo_lugar;
    float preco_fixo;

    public PrecarioBaseDTO() {}

    public PrecarioBaseDTO(String tipo_precario, Integer id_parque, String tipo_lugar, float preco_fixo) {
        this.tipo_precario = tipo_precario;
        this.id_parque = id_parque;
        this.tipo_lugar = tipo_lugar;
        this.preco_fixo = preco_fixo;
    }
}
