package pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name="PrecarioDecrementoLinear")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("decrementoLinear")
@PrimaryKeyJoinColumn(name="PrecarioID", referencedColumnName="ID")
@Getter
@Setter
public class PrecarioDecrementoLinear extends Precario implements Serializable {
	public PrecarioDecrementoLinear() {}

	@Column(name="PrecoPorMinutoMax", nullable=false)
	private float precoPorMinutoMax;
	
	@Column(name="PrecoPorMinutoMin", nullable=false)
	private float precoPorMinutoMin;
	
	@Column(name="IntervaloParaAtingirMin", nullable=false)	
	private float intervaloParaAtingirMin; // tempo para atingir o minimo em minutos
	
	public PrecarioDecrementoLinear(float precoFixo, float precoPorIntervaloMax, float precoPorIntervaloMin, float intervalo, TimeUnit tipoIntervalo, float intervaloParaAtingirMin) {
		//TODO Implement Method
		throw new UnsupportedOperationException();
	}

	@Override
	public float calcular_preco(LocalDateTime data_inicio, LocalDateTime data_fim) {
		float tempoEmMinutos = (float) (Duration.between(data_inicio,data_fim).toSeconds() / 60);
		float razao = tempoEmMinutos / intervaloParaAtingirMin;
		float preco_a_pagar_por_min;

		if(razao >= 1)
			preco_a_pagar_por_min = precoPorMinutoMin;
		else {
			float decliveFunc = (precoPorMinutoMin - precoPorMinutoMax) / intervaloParaAtingirMin;
			preco_a_pagar_por_min = decliveFunc * tempoEmMinutos + precoPorMinutoMax;
		}

		return this.getPrecoFixo() + preco_a_pagar_por_min * tempoEmMinutos;
	}

	public String toString() {
		return super.toString();
	}
	
}
