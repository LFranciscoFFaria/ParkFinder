package pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaNotificacoes.Notificacao;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Utilizador")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="Discriminator", discriminatorType=DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
public abstract class Utilizador implements Serializable {
	public Utilizador() {
	}
	
	@Column(name="ID", nullable=false)
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Nome")
	private String nome;
	
	@Column(name="Email", unique = true)
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@OneToMany(mappedBy="utilizador", targetEntity= Reserva.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Reserva> reservas = new HashSet<>();
	
	@OneToMany(mappedBy="utilizador", targetEntity= Notificacao.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Notificacao> notificacoes = new HashSet<>();
	
	public String toString() {
		return String.valueOf(getId());
	}
}
