package pt.uminho.di.aa.parkfinder.logicaNotificacoes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Notificacao")
@Getter
@Setter
@AllArgsConstructor
public class Notificacao implements Serializable {
	public Notificacao() {
	}

	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity= Utilizador.class, fetch=FetchType.LAZY)
	@JoinColumn(name="UtilizadorID", referencedColumnName="ID", nullable=false)
	private Utilizador utilizador;

	@Column(name="UtilizadorID", updatable = false, insertable = false, nullable = false)
	private int utilizadorID;
	
	@Column(name="Titulo")
	private String titulo;
	
	@Column(name="Texto", columnDefinition = "TEXT")
	private String texto;
	
	@Column(name="Lida", nullable=false, length=1)	
	private boolean lida;
	
	@Column(name="Timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime timestamp;

	public String toString() {
		return String.valueOf(getId());
	}
	
}
