package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.api.DTOs.deserializers.GeneroDeserializer;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.regex.Pattern;

@Entity
@Table(name="Condutor")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Condutor")
@PrimaryKeyJoinColumn(name="UtilizadorID", referencedColumnName="ID")
@Getter
@Setter
public class Condutor extends Utilizador implements Serializable {
	public Condutor() {
	}
	
	@Column(name="Nif", nullable=false)
	private Integer nif;
	
	@Column(name="Genero", nullable=false, length=1)
	@JsonDeserialize(using = GeneroDeserializer.class)
	private Boolean genero;

	public Condutor(@JsonProperty("nome") String nome, @JsonProperty("email") String email, @JsonProperty("password") String password,
					@JsonProperty("nif") int nif, @JsonProperty("genero") boolean genero, @JsonProperty("numero_telemovel") int numero_telemovel) {
		this.setNome(nome);
		this.setEmail(email);
		this.setPassword(password);
		this.nif = nif;
		this.genero = genero;
		setNrTelemovel(numero_telemovel);
	}

	/**
	 * Valida atributos do utilizador
	 * @return "null" se todos os atributos estiveram corretos ou uma string que contém o erro
	 */
	public String validarAtributos(){
		String valido = super.validarAtributos();
		if(valido != null) return valido;
		if(nif == null)
			return "O NIF não pode ser nulo!";
		if(genero == null)
			return "O genero não pode ser nulo!";
		if(!Pattern.matches("^\\d{9}$", String.valueOf(this.getNif())))
			return "O NIF contêm 9 digitos numéricos.";
		return null;
	}

	public Condutor clone(){
		return new Condutor(getNome(), getEmail(), getPassword(), nif, genero, getNrTelemovel());
	}

	public String toString() {
		return super.toString();
	}
	
}
