package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Gestor")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Gestor")
@PrimaryKeyJoinColumn(name="UtilizadorID", referencedColumnName="ID")
@Getter
@Setter
@AllArgsConstructor
public class Gestor extends Utilizador implements Serializable {
	public Gestor() {}

	@OneToMany(targetEntity= Parque.class, fetch=FetchType.LAZY)
	@JoinColumn(name="GestorID")
	private Set<Parque> parques = new HashSet<>();
	
	@OneToMany(mappedBy="gestor", targetEntity= Administrador.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Administrador> admins = new HashSet<>();

	public Gestor(String nome, String email, String password, int nrTelemovel) {
		setNome(nome);
		setEmail(email);
		setPassword(password);
		setNrTelemovel(nrTelemovel);
	}

	public Gestor(String nome, String email, String password, int nrTelemovel, Set<Parque> parques, Set<Administrador> admins) {
		setNome(nome);
		setEmail(email);
		setPassword(password);
		setNrTelemovel(nrTelemovel);
		this.parques = parques;
		this.admins = admins;
	}

	public String toString() {
		return super.toString();
	}
	
}
