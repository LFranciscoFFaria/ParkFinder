package pt.uminho.di.aa.parkfinder.logicaParques;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name="Precario")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="Discriminator", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("Precario")
public abstract class Precario implements Serializable {
	public Precario() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity= TipoLugarEstacionamento.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="TipoLugarEstacionamentoID", referencedColumnName="ID", nullable=false) })
	private TipoLugarEstacionamento tipo;
	
	@Column(name="PrecoFixo", nullable=false)	
	private float precoFixo;
	
	@Column(name="PrecoPorMinuto", nullable=false)	
	private float precoPorMinuto;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setPrecoFixo(float value) {
		this.precoFixo = value;
	}
	
	public float getPrecoFixo() {
		return precoFixo;
	}
	
	public void setPrecoPorMinuto(float value) {
		this.precoPorMinuto = value;
	}
	
	public float getPrecoPorMinuto() {
		return precoPorMinuto;
	}
	
	public void setTipo(TipoLugarEstacionamento value) {
		this.tipo = value;
	}
	
	public TipoLugarEstacionamento getTipo() {
		return tipo;
	}
	
	public float calcular_preco(java.util.Date data_inicio, java.util.Date data_fim) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
