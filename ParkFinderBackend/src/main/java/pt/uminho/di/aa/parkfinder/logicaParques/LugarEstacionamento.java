package pt.uminho.di.aa.parkfinder.logicaParques;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name="LugarEstacionamento")
@IdClass(LugarEstacionamentoPK.class)
public class LugarEstacionamento implements Serializable {
	public LugarEstacionamento() {
	}
	
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof LugarEstacionamento lugarestacionamento))
			return false;
		if (getId_lugar() != lugarestacionamento.getId_lugar())
			return false;
		if (getParque() == null) {
			return lugarestacionamento.getParque() == null;
		}
		else return getParque().equals(lugarestacionamento.getParque());
	}
	
	public int hashCode() {
		int hashcode = 0;
		hashcode = hashcode + (int) getId_lugar();
		if (getParque() != null) {
			hashcode = hashcode + (int) getParque().getORMID();
		}
		return hashcode;
	}

	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_lugar;
	
	@PrimaryKeyJoinColumn	
	@ManyToOne(targetEntity= Parque.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="ParqueID", referencedColumnName="ID", nullable=false) })
	private Parque parque;
	
	@Column(name="ParqueID", nullable=false, insertable=false, updatable=false)	
	@Id
	private int parqueId;
	
	private void setParqueId(int value) {
		this.parqueId = value;
	}
	
	public int getParqueId() {
		return parqueId;
	}
	
	@ManyToOne(targetEntity= TipoLugarEstacionamento.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="TipoLugarEstacionamentoID", referencedColumnName="ID", nullable=false) }, foreignKey=@ForeignKey(name="FKLugarEstac319247"))	
	private TipoLugarEstacionamento tipo;
	
	@Column(name="Id_parque", nullable=false, length=10)	
	private int id_parque;
	
	@Column(name="Utilizavel", nullable=false, length=1)	
	private boolean utilizavel;
	
	@Column(name="Ocupado", nullable=false, length=1)	
	private boolean ocupado;
	
	public void setId_lugar(int value) {
		this.id_lugar = value;
	}
	
	public int getId_lugar() {
		return id_lugar;
	}
	
	public void setId_parque(int value) {
		this.id_parque = value;
	}
	
	public int getId_parque() {
		return id_parque;
	}
	
	public void setUtilizavel(boolean value) {
		this.utilizavel = value;
	}
	
	public boolean getUtilizavel() {
		return utilizavel;
	}
	
	public void setOcupado(boolean value) {
		this.ocupado = value;
	}
	
	public boolean getOcupado() {
		return ocupado;
	}
	
	public void setParque(Parque value) {
		if (parque != null) {
			parque.lugaresEspeciais.remove(this);
		}
		if (value != null) {
			value.lugaresEspeciais.add(this);
		}
	}
	
	public Parque getParque() {
		return parque;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Parque(Parque value) {
		this.parque = value;
	}
	
	private Parque getORM_Parque() {
		return parque;
	}
	
	public void setTipo(TipoLugarEstacionamento value) {
		this.tipo = value;
	}
	
	public TipoLugarEstacionamento getTipo() {
		return tipo;
	}
	
	public String toString() {
		return String.valueOf(getId_lugar() + " " + ((getParque() == null) ? "" : String.valueOf(getParque().getORMID())));
	}
	
}
