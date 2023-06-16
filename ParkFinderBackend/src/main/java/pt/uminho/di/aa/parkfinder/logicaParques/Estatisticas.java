package pt.uminho.di.aa.parkfinder.logicaParques;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name="Estatisticas")
public class Estatisticas implements Serializable {
	public Estatisticas() {
	}
	
	@Column(name="ID", nullable=false, length=10)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Volume_de_estacionamento", nullable=false)
	private int volume_de_estacionamento;
	
	@Column(name="Faturacao_total", nullable=false)	
	private float faturacao_total;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setVolume_de_estacionamento(int value) {
		this.volume_de_estacionamento = value;
	}
	
	public int getVolume_de_estacionamento() {
		return volume_de_estacionamento;
	}
	
	public void setFaturacao_total(float value) {
		this.faturacao_total = value;
	}
	
	public float getFaturacao_total() {
		return faturacao_total;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
