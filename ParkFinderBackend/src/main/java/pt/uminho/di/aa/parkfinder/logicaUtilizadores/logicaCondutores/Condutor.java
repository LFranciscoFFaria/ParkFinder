package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores.Utilizador;

import jakarta.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="Condutor")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Condutor")
@PrimaryKeyJoinColumn(name="UtilizadorID", referencedColumnName="ID")
@Getter
@Setter
@AllArgsConstructor
public class Condutor extends Utilizador implements Serializable {
	public Condutor() {
	}
	
	@Column(name="Nif", nullable=false, length=10)	
	private int nif;
	
	@Column(name="Genero", nullable=false, length=1)	
	private boolean genero;
	
	public String toString() {
		return super.toString();
	}
	
}
