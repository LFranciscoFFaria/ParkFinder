package pt.uminho.di.aa.parkfinder.logicaReservas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaParques.model.LugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Reserva")
@Getter
@Setter
@AllArgsConstructor
public class Reserva implements Serializable {
	public Reserva() {
	}
	
	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity= Utilizador.class, fetch=FetchType.LAZY)
	@JoinColumn(name="UtilizadorID", referencedColumnName="ID", nullable=false)
	private Utilizador utilizador;
	
	@ManyToOne(targetEntity= LugarEstacionamento.class, fetch=FetchType.LAZY)
	@JoinColumn(name="LugarID", referencedColumnName="ID")
	private LugarEstacionamento lugar;
	
	@ManyToOne(targetEntity= Parque.class, fetch=FetchType.LAZY)
	@JoinColumn(name="ParqueID", referencedColumnName="ID", nullable=false)
	private Parque parque;
	
	@Column(name="Estado", nullable=false)
	private int estado;
	
	@Column(name="Custo")
	private Float custo = null;
	
	@Column(name="Pago", nullable=false, length=1)	
	private boolean pago;
	
	@Column(name="Matricula")
	private String matricula;
	
	@Column(name="Data_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataInicio;
	
	@Column(name="Data_fim")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataFim = null;

	public String toString() {
		return String.valueOf(getId());
	}
	
}
