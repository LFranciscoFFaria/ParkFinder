package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores.Utilizador;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Programador")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Programador")
@PrimaryKeyJoinColumn(name="UtilizadorID", referencedColumnName="ID")
public class Programador extends Utilizador implements Serializable {
	public Programador() {
	}
	
	public String toString() {
		return super.toString();
	}
	
}
