package pt.uminho.di.aa.parkfinder.logicaParques.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="TipoLugarEstacionamento")
@Getter
@Setter
@AllArgsConstructor
public class TipoLugarEstacionamento implements Serializable {
	public TipoLugarEstacionamento() {
	}

	public TipoLugarEstacionamento(String nome) {
		this.nome = nome;
	}

	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="Nome", unique = true)
	private String nome;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TipoLugarEstacionamento that = (TipoLugarEstacionamento) o;
		return id == that.id && Objects.equals(nome, that.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public String toString() {
		return "TipoLugarEstacionamento{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				'}';
	}
}
