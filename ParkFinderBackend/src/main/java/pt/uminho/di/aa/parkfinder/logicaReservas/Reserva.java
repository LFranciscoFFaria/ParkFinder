package pt.uminho.di.aa.parkfinder.logicaReservas;

import jakarta.persistence.*;
import pt.uminho.di.aa.parkfinder.logicaBasicaUtilizadores.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaParques.LugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParques.Parque;

import java.io.Serializable;
@Entity
@Table(name="Reserva")
public class Reserva implements Serializable {
	public Reserva() {
	}
	
	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity= Utilizador.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="UtilizadorID", referencedColumnName="ID", nullable=false) })
	private Utilizador utilizador;
	
	@ManyToOne(targetEntity= LugarEstacionamento.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="LugarEstacionamentoID", referencedColumnName="ID", nullable=false), @JoinColumn(name="ParqueID", referencedColumnName="ParqueID", nullable=false) })
	private LugarEstacionamento lugar;
	
	@ManyToOne(targetEntity= Parque.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="ParqueID", referencedColumnName="ID", nullable=false) })
	private Parque parque;
	
	@Column(name="Estado", nullable=false, length=10)	
	private int estado;
	
	@Column(name="Custo")
	private Float custo = null;
	
	@Column(name="Pago", nullable=false, length=1)	
	private boolean pago;
	
	@Column(name="Matricula")
	private String matricula;
	
	@Column(name="Data_inicio")
	@Temporal(TemporalType.DATE)	
	private java.util.Date data_inicio;
	
	@Column(name="Data_fim")
	@Temporal(TemporalType.DATE)	
	private java.util.Date data_fim = null;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setData_inicio(java.util.Date value) {
		this.data_inicio = value;
	}
	
	public java.util.Date getData_inicio() {
		return data_inicio;
	}
	
	public void setData_fim(java.util.Date value) {
		this.data_fim = value;
	}
	
	public java.util.Date getData_fim() {
		return data_fim;
	}
	
	public void setEstado(int value) {
		this.estado = value;
	}
	
	public int getEstado() {
		return estado;
	}
	
	public void setCusto(float value) {
		this.custo = value;
	}
	
	public float getCusto() {
		return custo;
	}
	
	public void setPago(boolean value) {
		this.pago = value;
	}
	
	public boolean getPago() {
		return pago;
	}
	
	public void setMatricula(String value) {
		this.matricula = value;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	//public void setUtilizador(Utilizador value) {
	//	if (utilizador != null) {
	//		utilizador.reservas.remove(this);
	//	}
	//	if (value != null) {
	//		value.reservas.add(this);
	//	}
	//}
	
	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador value) {
		this.utilizador = value;
	}
	
	public void setLugar(LugarEstacionamento value) {
		this.lugar = value;
	}
	
	public LugarEstacionamento getLugar() {
		return lugar;
	}
	
	public void setParque(Parque value) {
		this.parque = value;
	}
	
	public Parque getParque() {
		return parque;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
