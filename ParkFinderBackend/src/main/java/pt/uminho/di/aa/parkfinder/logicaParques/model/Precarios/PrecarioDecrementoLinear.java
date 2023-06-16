/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Alexandre Martins(Universidade do Minho)
 * License Type: Academic
 */
package pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
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

	@Column(name="PrecoPorIntervaloMax", nullable=false)	
	private float precoPorIntervaloMax;
	
	@Column(name="PrecoPorIntervaloMin", nullable=false)	
	private float precoPorIntervaloMin;
	
	@Column(name="IntervaloParaAtingirMin", nullable=false)	
	private float intervaloParaAtingirMin;
	
	public PrecarioDecrementoLinear(float precoFixo, float precoPorIntervaloMax, float precoPorIntervaloMin, float intervalo, TimeUnit tipoIntervalo, float intervaloParaAtingirMin) {
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
