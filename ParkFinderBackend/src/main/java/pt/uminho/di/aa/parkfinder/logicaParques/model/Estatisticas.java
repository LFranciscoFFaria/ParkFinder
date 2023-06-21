package pt.uminho.di.aa.parkfinder.logicaParques.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Table(name="Estatisticas")
@Getter
@Setter
@AllArgsConstructor
public class Estatisticas implements Serializable {
	public Estatisticas() {
		volume_de_estacionamento = 0;
		faturacao_total = 0F;
	}
	
	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Volume_de_estacionamento", nullable=false)
	private int volume_de_estacionamento;
	
	@Column(name="Faturacao_total", nullable=false)	
	private float faturacao_total;

	public void incVolumeDeEstacionamento(){
		volume_de_estacionamento++;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
