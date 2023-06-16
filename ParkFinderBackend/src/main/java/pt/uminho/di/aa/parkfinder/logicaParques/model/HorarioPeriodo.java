package pt.uminho.di.aa.parkfinder.logicaParques.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Table(name="HorarioPeriodo")
@Getter
@Setter
@AllArgsConstructor
public class HorarioPeriodo implements Serializable {
	public HorarioPeriodo() {}
	
	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Hora_inicio", nullable=false)	
	private float hora_inicio;
	
	@Column(name="Hora_fim", nullable=false)	
	private float hora_fim;
	
	@Column(name="Dia_semana", nullable=false)
	private int dia_semana;
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
