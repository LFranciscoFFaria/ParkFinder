package pt.uminho.di.aa.parkfinder.logicaNotificacoes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;

import java.io.Serializable;
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

	@Column(name="UtilizadorID", nullable=false, insertable=false, updatable=false)
	private int UtilizadorID;
	
	@Column(name="Titulo")
	private String titulo;
	
	@Column(name="Texto")
	private String texto;
	
	@Column(name="Lida", nullable=false, length=1)	
	private boolean lida;
	
	@Column(name="Timestamp")
	@Temporal(TemporalType.DATE)	
	private java.util.Date timestamp;

	public String toString() {
		return String.valueOf(getId());
	}
	
}
