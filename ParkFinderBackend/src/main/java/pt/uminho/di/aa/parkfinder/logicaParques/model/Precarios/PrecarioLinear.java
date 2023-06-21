package pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="PrecarioLinear")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("linear")
@PrimaryKeyJoinColumn(name="PrecarioID", referencedColumnName="ID")
@Getter
@Setter
public class PrecarioLinear extends Precario implements Serializable {
	public PrecarioLinear() {
	}

	@Column(name="PrecoPorMinuto", nullable=false)
	@JsonProperty("preco_por_minuto")
	private float precoPorMinuto;

	public PrecarioLinear(TipoLugarEstacionamento tipoLugar, float precoFixo, float precoPorIntervalo, LocalTime intervalo) {
		setTipo(tipoLugar);
		setPrecoFixo(precoFixo);
		precoPorMinuto = precoPorIntervalo / (intervalo.getHour() * 60 + intervalo.getMinute());
	}

	@Override
	public float calcular_preco(LocalDateTime data_inicio, LocalDateTime data_fim) {
		return this.getPrecoFixo() + ((float) (Duration.between(data_inicio,data_fim).toSeconds() / 60) * this.getPrecoPorMinuto());
	}

	public String toString() {
		return super.toString() +
				", precoPorMinuto= " + precoPorMinuto;
	}
	
}
