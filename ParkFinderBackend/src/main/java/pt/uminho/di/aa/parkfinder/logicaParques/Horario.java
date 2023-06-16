package pt.uminho.di.aa.parkfinder.logicaParques;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Horario")
public class Horario implements Serializable {
	public Horario() {
	}
	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(targetEntity= HorarioPeriodo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumns({ @JoinColumn(name="HorarioID", nullable=false) })
	private Set<HorarioPeriodo> periodos = new java.util.HashSet<>();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	private void setPeriodos(Set<HorarioPeriodo> value) {
		this.periodos = value;
	}
	
	private Set<HorarioPeriodo> getPeriodos() {
		return periodos;
	}

	public boolean estaAberto(float data_inicio, float data_fim) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
