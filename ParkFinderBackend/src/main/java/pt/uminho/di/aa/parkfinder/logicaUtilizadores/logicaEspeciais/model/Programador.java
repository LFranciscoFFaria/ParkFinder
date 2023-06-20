package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model;

import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Programador")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Programador")
@PrimaryKeyJoinColumn(name="UtilizadorID", referencedColumnName="ID")
public class Programador extends Utilizador implements Serializable {
	public Programador() {}

	public Programador(String nome, String email, String password, int nrTelemovel) {
		setNome(nome);
		setEmail(email);
		setPassword(password);
		setNrTelemovel(nrTelemovel);
	}

	public String toString() {
		return super.toString();
	}
	
}
