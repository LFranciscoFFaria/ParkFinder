package pt.uminho.di.aa.parkfinder.logicaParques.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Table(name="LugarEstacionamento")
@Getter
@Setter
@AllArgsConstructor
public class LugarEstacionamento implements Serializable {
	public LugarEstacionamento() {}

	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lugarId;

	@ManyToOne(targetEntity= Parque.class, fetch=FetchType.LAZY)
	@JoinColumn(name="ParqueID", referencedColumnName="ID", nullable=false)
	private Parque parque;
	
	@Column(name="ParqueID", nullable=false, insertable=false, updatable=false)
	private int parqueId;
	
	@ManyToOne(targetEntity= TipoLugarEstacionamento.class, fetch=FetchType.LAZY)
	@JoinColumn(name="TipoLugarID", referencedColumnName="ID", nullable=false)
	private TipoLugarEstacionamento tipo;
	
	@Column(name="Utilizavel", nullable=false, length=1)	
	private boolean utilizavel;
	
	@Column(name="Ocupado", nullable=false, length=1)	
	private boolean ocupado;

	public String toString() {
		return String.valueOf(getLugarId() + " " + ((getParque() == null) ? "" : String.valueOf(getParque().getId())));
	}
	
}
