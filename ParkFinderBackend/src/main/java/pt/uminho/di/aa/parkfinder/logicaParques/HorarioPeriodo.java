package pt.uminho.di.aa.parkfinder.logicaParques;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name="HorarioPeriodo")
public class HorarioPeriodo implements Serializable {
	public HorarioPeriodo() {
	}
	
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
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setHora_inicio(float value) {
		this.hora_inicio = value;
	}
	
	public float getHora_inicio() {
		return hora_inicio;
	}
	
	public void setHora_fim(float value) {
		this.hora_fim = value;
	}
	
	public float getHora_fim() {
		return hora_fim;
	}
	
	public void setDia_semana(int value) {
		this.dia_semana = value;
	}
	
	public int getDia_semana() {
		return dia_semana;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
