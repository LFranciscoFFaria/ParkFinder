package pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
	@Temporal(TemporalType.TIME)
	private LocalTime intervaloParaAtingirMin; // tempo para atingir o minimo em minutos
	
	public PrecarioDecrementoLinear(TipoLugarEstacionamento tipoLugarEstacionamento, float precoFixo, float precoPorIntervaloMax, float precoPorIntervaloMin, LocalTime intervalo, LocalTime intervaloParaAtingirMin) {
		this.setPrecoFixo(precoFixo);
		this.setTipo(tipoLugarEstacionamento);
		this.precoPorMinutoMax = precoPorIntervaloMax / convertLocalTimeToMinutes(intervalo);
		this.precoPorMinutoMin = precoPorIntervaloMin / convertLocalTimeToMinutes(intervalo);
		this.setIntervaloParaAtingirMin(intervaloParaAtingirMin);
	}

	@Override
	public float calcular_preco(LocalDateTime data_inicio, LocalDateTime data_fim) {
		float tempoEmMinutos = (float) (Duration.between(data_inicio,data_fim).toMinutes());
		float intervaloParaMinEmMinutos = convertLocalTimeToMinutes(intervaloParaAtingirMin);
		float razao = tempoEmMinutos / intervaloParaMinEmMinutos;
		float preco_a_pagar_por_min;

		if(razao >= 1)
			preco_a_pagar_por_min = precoPorMinutoMin;
		else {
			float decliveFunc = (precoPorMinutoMin - precoPorMinutoMax) / intervaloParaMinEmMinutos;
			preco_a_pagar_por_min = decliveFunc * tempoEmMinutos + precoPorMinutoMax;
		}

		return this.getPrecoFixo() + preco_a_pagar_por_min * tempoEmMinutos;
	}

	private long convertLocalTimeToMinutes(LocalTime localTime){
		return localTime.getHour() * 60 + localTime.getMinute();
	}

	public String toString() {
		return super.toString();
	}
	
}
