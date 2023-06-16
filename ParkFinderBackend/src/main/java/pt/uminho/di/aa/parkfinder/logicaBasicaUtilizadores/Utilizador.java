package pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores;

import jakarta.persistence.*;
import pt.uminho.di.aa.parkfinder.logicaNotificacoes.Notificacao;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Utilizador")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="Discriminator", discriminatorType=DiscriminatorType.STRING)
public abstract class Utilizador implements Serializable {
	public Utilizador() {
	}
	
	@Column(name="ID", nullable=false)
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Nome")
	private String nome;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@OneToMany(mappedBy="utilizador", targetEntity= Reserva.class, cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private Set<Reserva> reservas = new HashSet<>();
	
	@OneToMany(mappedBy="utilizador", targetEntity= Notificacao.class, cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private Set<Notificacao> notificacoes = new HashSet<>();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	private void setReservas(Set<Reserva> value) {
		this.reservas = value;
	}
	
	private Set<Reserva> getReservas() {
		return this.reservas;
	}

	private void setNotificacoes(Set<Notificacao> value) {
		this.notificacoes = value;
	}
	
	private Set<Notificacao> getNotificacoes() {
		return notificacoes;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
}
