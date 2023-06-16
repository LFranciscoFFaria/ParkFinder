package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais;

import jakarta.persistence.*;
import pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaParques.Parque;

import java.io.Serializable;
import java.util.Set;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Gestor")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Gestor")
@PrimaryKeyJoinColumn(name="UtilizadorID", referencedColumnName="ID")
public class Gestor extends Utilizador implements Serializable {
	public Gestor() {
	}

	@OneToMany(targetEntity= Parque.class, fetch=FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name="GestorUtilizadorID", nullable=true) })
	private Set<Parque> ORM_parques = new java.util.HashSet();
	
	@OneToMany(mappedBy="gestor", targetEntity= Administrador.class, fetch=FetchType.LAZY)
	private Set<Administrador> ORM_admins = new java.util.HashSet();
	
	private void setORM_Parques(Set<Parque> value) {
		this.ORM_parques = value;
	}
	
	private Set<Parque> getORM_Parques() {
		return ORM_parques;
	}
	
	@Transient	
	public final ParqueSetCollection parques = new ParqueSetCollection(this, _ormAdapter, ORMConstants.KEY_GESTOR_PARQUES, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Admins(Set<Administrador> value) {
		this.ORM_admins = value;
	}
	
	private Set<Administrador> getORM_Admins() {
		return ORM_admins;
	}
	
	@Transient	
	public final AdministradorSetCollection admins = new AdministradorSetCollection(this, _ormAdapter, ORMConstants.KEY_GESTOR_ADMINS, ORMConstants.KEY_ADMINISTRADOR_GESTOR, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return super.toString();
	}
	
}
