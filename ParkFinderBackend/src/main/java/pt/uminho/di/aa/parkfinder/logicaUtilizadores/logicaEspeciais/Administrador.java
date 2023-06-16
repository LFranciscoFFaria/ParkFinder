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
@Table(name="Administrador")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Admin")
@PrimaryKeyJoinColumn(name="UtilizadorID", referencedColumnName="ID")
public class Administrador extends Utilizador implements Serializable {
	public Administrador() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_ADMINISTRADOR_PARQUES) {
			return ORM_parques;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_ADMINISTRADOR_GESTOR) {
			this.gestor = (Gestor) owner;
		}
	}
	
	@Transient	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	@ManyToOne(targetEntity= Gestor.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="GestorUtilizadorID", referencedColumnName="UtilizadorID", nullable=false) }, foreignKey=@ForeignKey(name="FKAdministra94501"))	
	private Gestor gestor;
	
	@ManyToMany(targetEntity= Parque.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="Parque_Administrador", joinColumns={ @JoinColumn(name="AdministradorUtilizadorID") }, inverseJoinColumns={ @JoinColumn(name="ParqueID") })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_parques = new java.util.HashSet();
	
	private void setORM_Parques(java.util.Set value) {
		this.ORM_parques = value;
	}
	
	private java.util.Set getORM_Parques() {
		return ORM_parques;
	}
	
	@Transient	
	public final ParqueSetCollection parques = new ParqueSetCollection(this, _ormAdapter, ORMConstants.KEY_ADMINISTRADOR_PARQUES, ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	public void setGestor(Gestor value) {
		if (gestor != null) {
			gestor.admins.remove(this);
		}
		if (value != null) {
			value.admins.add(this);
		}
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
