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
import pt.uminho.di.aa.parkfinder.logicaParques.Parque;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Gestor")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Gestor")
@PrimaryKeyJoinColumn(name="UtilizadorID", referencedColumnName="ID")
public class Gestor extends Utilizador implements Serializable {
	public Gestor() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_GESTOR_PARQUES) {
			return ORM_parques;
		}
		else if (key == ORMConstants.KEY_GESTOR_ADMINS) {
			return ORM_admins;
		}
		
		return null;
	}
	
	@Transient	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	@OneToMany(targetEntity= Parque.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="GestorUtilizadorID", nullable=true) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_parques = new java.util.HashSet();
	
	@OneToMany(mappedBy="gestor", targetEntity= Administrador.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_admins = new java.util.HashSet();
	
	private void setORM_Parques(java.util.Set value) {
		this.ORM_parques = value;
	}
	
	private java.util.Set getORM_Parques() {
		return ORM_parques;
	}
	
	@Transient	
	public final ParqueSetCollection parques = new ParqueSetCollection(this, _ormAdapter, ORMConstants.KEY_GESTOR_PARQUES, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Admins(java.util.Set value) {
		this.ORM_admins = value;
	}
	
	private java.util.Set getORM_Admins() {
		return ORM_admins;
	}
	
	@Transient	
	public final AdministradorSetCollection admins = new AdministradorSetCollection(this, _ormAdapter, ORMConstants.KEY_GESTOR_ADMINS, ORMConstants.KEY_ADMINISTRADOR_GESTOR, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return super.toString();
	}
	
}
