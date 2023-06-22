package pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaNotificacoes.Notificacao;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Entity
@Table(name="Utilizador")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="Discriminator", discriminatorType=DiscriminatorType.STRING)
@Getter
@Setter
public abstract class Utilizador implements Serializable {
	public Utilizador() {}
	
	@Column(name="ID", nullable=false)
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Nome", nullable = false)
	private String nome;
	
	@Column(name="Email", unique = true, nullable = false)
	private String email;

	@Column(name="NrTelemovel", nullable=false)
	private Integer nrTelemovel;
	
	@Column(name="Password", nullable = false)
	private String password;
	
	@OneToMany(mappedBy="utilizador", targetEntity= Reserva.class, cascade = {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Reserva> reservas = new HashSet<>();
	
	@OneToMany(mappedBy="utilizador", targetEntity= Notificacao.class, cascade = {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Notificacao> notificacoes = new HashSet<>();

	@Column(name = "Discriminator", insertable = false, updatable = false)
	@JsonIgnore
	private String discriminator;

	/**
	 * Valida atributos do utilizador
	 * @return "null" se todos os atributos estiveram corretos ou uma string que contém o erro
	 */
	public String validarAtributos(){
		if(nome == null)
			return "Nome não pode ser nulo.";
		if(email == null)
			return "Email não pode ser nulo.";
		if(password == null)
			return "Palavra-passe não pode ser nula.";
		if(nrTelemovel == null)
			return "Número de telemóvel não pode ser nulo.";
		if(!Pattern.matches("^\\w[\\w ]*$", this.getNome()))
			return "Um nome pode conter apenas alfanuméricos e espaços. Deve começar com um alfanumérico.";
		if(!Pattern.matches("^\\w[\\w.]*@\\w+(?:\\.\\w+)+$", this.getEmail()))
			return "Formato de email incorreto.";
		if(!Pattern.matches("^\\d{9}$", String.valueOf(this.getNrTelemovel())))
			return "O número de telemóvel português têm de conter 9 digitos numéricos.";
		return null;
	}

	@Override
	public String toString() {
		return "Utilizador{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", email='" + email + '\'' +
				", nrTelemovel=" + nrTelemovel +
				", password='" + password + '\'' +
				", discriminator='" + discriminator + '\'' +
				'}';
	}
}
