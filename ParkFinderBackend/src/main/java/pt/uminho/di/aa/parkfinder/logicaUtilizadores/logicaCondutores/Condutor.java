package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores;

import pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores.Utilizador;

import jakarta.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="Condutor")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Condutor")
@PrimaryKeyJoinColumn(name="UtilizadorID", referencedColumnName="ID")
public class Condutor extends Utilizador implements Serializable {
	public Condutor() {
	}
	
	@Column(name="Nif", nullable=false, length=10)	
	private int nif;
	
	@Column(name="Genero", nullable=false, length=1)	
	private boolean genero;
	
	public void setNif(int value) {
		this.nif = value;
	}
	
	public int getNif() {
		return nif;
	}
	
	public void setGenero(boolean value) {
		this.genero = value;
	}
	
	public boolean getGenero() {
		return genero;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
