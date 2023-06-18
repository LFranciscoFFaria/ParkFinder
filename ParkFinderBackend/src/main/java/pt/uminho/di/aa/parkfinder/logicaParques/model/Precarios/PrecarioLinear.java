package pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

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
	private float precoPorMinuto;

	//TODO - ver isto depois
	//public PrecarioLinear(TipoLugarEstacionamento tipoLugar, float precoFixo, float precoPorIntervalo, float intervalo, TimeUnit tipoIntervalo) {
	//	long duracaoInput = (long) (intervalo * tipoIntervalo.toNanos(1)); // Converte intervalo para nanosegundos
	//	long duracaoEmMinutos = TimeUnit.MINUTES.convert(duracaoInput, TimeUnit.NANOSECONDS); // Converte duracao de nanosegundos para minutos
	//
	//}

	public PrecarioLinear(int id, TipoLugarEstacionamento tipo, float precoFixo, float precoPorMinuto) {
		super(id, tipo, precoFixo);
		this.precoPorMinuto = precoPorMinuto;
	}

	@Override
	public float calcular_preco(LocalDateTime data_inicio, LocalDateTime data_fim) {
		return this.getPrecoFixo() + ((float) (Duration.between(data_inicio,data_fim).toSeconds() / 60) * this.getPrecoPorMinuto());
	}

	public String toString() {
		return super.toString();
	}
	
}
