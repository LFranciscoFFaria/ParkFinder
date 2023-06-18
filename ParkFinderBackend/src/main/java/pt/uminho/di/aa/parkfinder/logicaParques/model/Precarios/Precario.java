package pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios;

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
	private int id;
	
	@ManyToOne(targetEntity= TipoLugarEstacionamento.class, fetch=FetchType.EAGER)
	@JoinColumn(name="TipoLugarID", referencedColumnName="ID", nullable=false)
	private TipoLugarEstacionamento tipo;
	
	@Column(name="PrecoFixo", nullable=false)	
	private float precoFixo;

	@Column(name = "Discriminator", insertable = false, updatable = false)
	private String discriminator;

	public Precario(int id, TipoLugarEstacionamento tipo, float precoFixo) {
		this.id = id;
		this.tipo = tipo;
		this.precoFixo = precoFixo;
	}

	public abstract float calcular_preco(LocalDateTime data_inicio, LocalDateTime data_fim);

	public String toString() {
		return String.valueOf(getId());
	}
	
}
