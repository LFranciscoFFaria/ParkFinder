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
package pt.uminho.di.aa.parkfinder.logicaParques;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="PrecarioDecrementoLinear")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("decrementoLinear")
@PrimaryKeyJoinColumn(name="PrecarioID", referencedColumnName="ID")
public class PrecarioDecrementoLinear extends Precario implements Serializable {
	public PrecarioDecrementoLinear() {
	}
	
	@Column(name="PrecoPorIntervaloMax", nullable=false)	
	private float precoPorIntervaloMax;
	
	@Column(name="PrecoPorIntervaloMin", nullable=false)	
	private float precoPorIntervaloMin;
	
	@Column(name="IntervaloParaAtingirMin", nullable=false)	
	private float intervaloParaAtingirMin;
	
	public void setPrecoPorIntervaloMax(float value) {
		this.precoPorIntervaloMax = value;
	}
	
	public float getPrecoPorIntervaloMax() {
		return precoPorIntervaloMax;
	}
	
	public void setPrecoPorIntervaloMin(float value) {
		this.precoPorIntervaloMin = value;
	}
	
	public float getPrecoPorIntervaloMin() {
		return precoPorIntervaloMin;
	}
	
	public void setIntervaloParaAtingirMin(float value) {
		this.intervaloParaAtingirMin = value;
	}
	
	public float getIntervaloParaAtingirMin() {
		return intervaloParaAtingirMin;
	}
	
	public PrecarioDecrementoLinear(float precoFixo, float precoPorIntervaloMax, float precoPorIntervaloMin, float intervalo, TimeUnit tipoIntervalo, float intervaloParaAtingirMin) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return super.toString();
	}
	
}
