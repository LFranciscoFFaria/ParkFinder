package pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Precario")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="Discriminator", discriminatorType=DiscriminatorType.STRING)
@Getter
@Setter
public abstract class Precario implements Serializable {
	public Precario() {
	}
	
	@Column(name="ID", nullable=false)
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;
	
	@ManyToOne(targetEntity= TipoLugarEstacionamento.class, fetch=FetchType.EAGER)
	@JoinColumn(name="TipoLugarID", referencedColumnName="ID", nullable=false)
	@JsonUnwrapped(prefix = "tipo_lugar_")
	@JsonIgnoreProperties("id")
	private TipoLugarEstacionamento tipo;
	
	@Column(name="PrecoFixo", nullable=false)
	@JsonProperty("preco_fixo")
	private float precoFixo;

	@Column(name = "Discriminator", insertable = false, updatable = false)
	@JsonProperty("tipo_precario")
	private String discriminator;

	public Precario(int id, TipoLugarEstacionamento tipo, float precoFixo) {
		this.id = id;
		this.tipo = tipo;
		this.precoFixo = precoFixo;
	}

	public abstract float calcular_preco(LocalDateTime data_inicio, LocalDateTime data_fim);

	@Override
	public String toString() {
		return "Precario:" +
				"id=" + id +
				", tipo=" + tipo +
				", precoFixo=" + precoFixo +
				", discriminator='" + discriminator + '\'';
	}
}
