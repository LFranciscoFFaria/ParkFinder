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
package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores.Utilizador;

import java.io.Serializable;
import javax.persistence.*;
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
