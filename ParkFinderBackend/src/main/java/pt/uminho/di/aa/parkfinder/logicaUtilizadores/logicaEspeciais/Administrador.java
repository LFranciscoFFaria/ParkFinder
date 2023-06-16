package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import jakarta.persistence.*;
import pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaParques.Parque;

import java.io.Serializable;
import java.util.Set;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Administrador")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorValue("Admin")
@PrimaryKeyJoinColumn(name="UtilizadorID", referencedColumnName="ID")
public class Administrador extends Utilizador implements Serializable {
	public Administrador() {
	}

	@ManyToOne(targetEntity= Gestor.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="GestorUtilizadorID", referencedColumnName="UtilizadorID", nullable=false) }, foreignKey=@ForeignKey(name="FKAdministra94501"))	
	private Gestor gestor;
	
	@ManyToMany(targetEntity= Parque.class, fetch= FetchType.LAZY)
	@JoinTable(name="Parque_Administrador", joinColumns={ @JoinColumn(name="AdministradorUtilizadorID") }, inverseJoinColumns={ @JoinColumn(name="ParqueID") })

	private Set<Parque> parques = new java.util.HashSet();
	
	private void setORM_Parques(java.util.Set value) {
		this.parques = value;
	}
	
	private java.util.Set getORM_Parques() {
		return parques;
	}


	
	public Gestor getGestor() {
		return gestor;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Gestor(Gestor value) {
		this.gestor = value;
	}
	
	private Gestor getORM_Gestor() {
		return gestor;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
