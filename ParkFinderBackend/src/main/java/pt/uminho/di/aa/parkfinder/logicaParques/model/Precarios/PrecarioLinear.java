package pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name="PrecarioLinear")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("linear")
@PrimaryKeyJoinColumn(name="PrecarioID", referencedColumnName="ID")
public class PrecarioLinear extends Precario implements Serializable {
	public PrecarioLinear() {
	}
	
	public PrecarioLinear(float precoFixo, float precoPorIntervalo, float intervalo, TimeUnit tipoIntervalo) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}

	@Override
	public float calcular_preco(Date data_inicio, Date data_fim) {
		//TODO
		throw new UnsupportedOperationException();
	}

	public String toString() {
		return super.toString();
	}
	
}
