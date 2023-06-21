package pt.uminho.di.aa.parkfinder.logicaParques.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import pt.uminho.di.aa.parkfinder.api.DTOs.deserializers.TimeDeserializer;
import pt.uminho.di.aa.parkfinder.api.DTOs.serializers.TimeSerializer;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name="HorarioPeriodo")
@Getter
@Setter
@AllArgsConstructor
@Schema(description = "HorarioPeriodo")
public class HorarioPeriodo implements Serializable {
	public HorarioPeriodo() {}
	
	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;
	
	@Column(name="Hora_inicio", nullable=false)
	@Temporal(TemporalType.TIME)
	@JsonSerialize(using = TimeSerializer.class)
	@JsonDeserialize(using = TimeDeserializer.class)
	@Schema(implementation = String.class, format = "HH:mm", example = "21:32")
	private LocalTime hora_inicio;
	
	@Column(name="Hora_fim", nullable=false)
	@Temporal(TemporalType.TIME)
	@JsonSerialize(using = TimeSerializer.class)
	@JsonDeserialize(using = TimeDeserializer.class)
	@Schema(implementation = String.class, format = "HH:mm", example = "19:54")
	private LocalTime hora_fim;
	
	@Column(name="Dia_semana", nullable=false)
	@Schema(description = "Dia da semana, com 1 a corresponder a segunda-feira, e 7 a domingo.", example = "3")
	private int dia_semana;
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
