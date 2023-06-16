package pt.uminho.di.aa.parkfinder.logicaParques.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="TipoLugarEstacionamento")
@Getter
@Setter
@AllArgsConstructor
public class TipoLugarEstacionamento implements Serializable {
	public TipoLugarEstacionamento() {
	}
	
	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Nome")
	private String nome;
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
