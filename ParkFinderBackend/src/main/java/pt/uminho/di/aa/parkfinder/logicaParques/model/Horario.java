package pt.uminho.di.aa.parkfinder.logicaParques.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
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
	
	@OneToMany(targetEntity= HorarioPeriodo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="HorarioID", nullable=false)
	private Set<HorarioPeriodo> periodos = new java.util.HashSet<>();

	public boolean estaAberto(DayOfWeek dayOfWeek, float data_inicio, float data_fim) {
		int dia = dayOfWeek.getValue();
		return periodos.stream().anyMatch(p -> p.getDia_semana() == dia
								  			&& p.getHora_inicio() <= data_inicio
								  			&& p.getHora_fim() >= data_fim);
	}

	public boolean estaAberto(){
		LocalDateTime agora = LocalDateTime.now();
		DayOfWeek diaDaSemana = agora.getDayOfWeek();
		int hora = agora.getHour();
		int minuto = agora.getMinute();
		float horas_e_minutos = (float) ((hora * 60 + minuto) / 60);
		return estaAberto(diaDaSemana, horas_e_minutos, horas_e_minutos);
	}

	@Override
	public String toString() {
		return "Horario{" +
				"id=" + id +
				", periodos=" + periodos +
				'}';
	}
}
