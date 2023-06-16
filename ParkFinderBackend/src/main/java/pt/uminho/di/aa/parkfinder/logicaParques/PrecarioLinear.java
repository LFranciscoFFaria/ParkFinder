package pt.uminho.di.aa.parkfinder.logicaParques;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
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
	
	public String toString() {
		return super.toString();
	}
	
}
