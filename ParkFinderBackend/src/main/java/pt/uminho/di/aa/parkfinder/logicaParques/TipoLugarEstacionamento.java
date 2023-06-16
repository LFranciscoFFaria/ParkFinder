package pt.uminho.di.aa.parkfinder.logicaParques;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="TipoLugarEstacionamento")
public class TipoLugarEstacionamento implements Serializable {
	public TipoLugarEstacionamento() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Nome", nullable=true, length=255)	
	private String nome;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
