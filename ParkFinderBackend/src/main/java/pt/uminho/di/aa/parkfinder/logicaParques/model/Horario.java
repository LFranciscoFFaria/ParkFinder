package pt.uminho.di.aa.parkfinder.logicaParques.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Horario")
@Getter
@Setter
@AllArgsConstructor
public class Horario implements Serializable {
	public Horario() {}

	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(targetEntity= HorarioPeriodo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="HorarioID", nullable=false)
	private Set<HorarioPeriodo> periodos = new java.util.HashSet<>();

	public boolean estaAberto(float data_inicio, float data_fim) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
