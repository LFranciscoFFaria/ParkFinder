package pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.io.Serializable;
@Entity
@Table(name="Precario")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="Discriminator", discriminatorType=DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
public abstract class Precario implements Serializable {
	public Precario() {
	}
	
	@Column(name="ID", nullable=false)
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity= TipoLugarEstacionamento.class, fetch=FetchType.LAZY)
	@JoinColumn(name="TipoLugarID", referencedColumnName="ID", nullable=false)
	private TipoLugarEstacionamento tipo;
	
	@Column(name="PrecoFixo", nullable=false)	
	private float precoFixo;
	
	@Column(name="PrecoPorMinuto", nullable=false)	
	private float precoPorMinuto;

	@Column(name = "Discriminator", insertable = false, updatable = false)
	private String discriminator;

	public abstract float calcular_preco(java.util.Date data_inicio, java.util.Date data_fim);

	public String toString() {
		return String.valueOf(getId());
	}
	
}
