package pt.uminho.di.aa.parkfinder.logicaParques;

import jakarta.persistence.*;

import java.io.Serializable;
@Embeddable
public class LugarEstacionamentoPK implements Serializable {
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof LugarEstacionamentoPK lugarestacionamentopk))
			return false;
		if (getId_lugar() != lugarestacionamentopk.getId_lugar())
			return false;
		if (getParque() == null) {
			return lugarestacionamentopk.getParque() == null;
		}
		else return getParque().equals(lugarestacionamentopk.getParque());
	}
	
	public int hashCode() {
		int hashcode = 0;
		hashcode = hashcode + (int) getId_lugar();
		if (getParque() != null) {
			hashcode = hashcode + (int) getParque().getORMID();
		}
		return hashcode;
	}
	
	@Column(name="ID", nullable=false, length=10)	
	private int id_lugar;
	
	public void setId_lugar(int value)  {
		this.id_lugar =  value;
	}
	
	public int getId_lugar()  {
		return this.id_lugar;
	}
	
	@ManyToOne(targetEntity= Parque.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="ParqueID", referencedColumnName="ID", nullable=false) }, foreignKey=@ForeignKey(name="FKLugarEstac742434"))	
	private Parque parque;
	
	public void setParque(pt.uminho.di.aa.parkfinder.logicaParques.Parque value)  {
		this.parque =  value;
	}
	
	public pt.uminho.di.aa.parkfinder.logicaParques.Parque getParque()  {
		return this.parque;
	}
	
}
