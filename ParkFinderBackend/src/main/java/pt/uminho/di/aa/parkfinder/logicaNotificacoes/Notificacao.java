package pt.uminho.di.aa.parkfinder.logicaNotificacoes;

import jakarta.persistence.*;
import pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores.Utilizador;

import java.io.Serializable;
@Entity
@Table(name="Notificacao")
public class Notificacao implements Serializable {
	public Notificacao() {
	}

	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity= Utilizador.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="UtilizadorID", referencedColumnName="ID", nullable=false) })
	private Utilizador utilizador;
	
	@Column(name="Titulo")
	private String titulo;
	
	@Column(name="Texto")
	private String texto;
	
	@Column(name="Lida", nullable=false, length=1)	
	private boolean lida;
	
	@Column(name="Timestamp")
	@Temporal(TemporalType.DATE)	
	private java.util.Date timestamp;
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setTitulo(String value) {
		this.titulo = value;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTexto(String value) {
		this.texto = value;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setLida(boolean value) {
		this.lida = value;
	}
	
	public boolean getLida() {
		return lida;
	}
	
	public void setTimestamp(java.util.Date value) {
		this.timestamp = value;
	}
	
	public java.util.Date getTimestamp() {
		return timestamp;
	}
	
	//public void setUtilizador(Utilizador value) {
	//	if (utilizador != null) {
	//		utilizador.notificacoes.remove(this);
	//	}
	//	if (value != null) {
	//		value.notificacoes.add(this);
	//	}
	//}
	
	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador value) {
		this.utilizador = value;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
