package pt.uminho.di.aa.parkfinder.logicaParques.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Parque")
@AllArgsConstructor
@Getter
@Setter
public class Parque implements Serializable {
	public Parque() {}

	@Column(name="ID", nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="Nome")
	private String nome;

	@Column(name="Morada")
	private String morada;

	@Column(name="Descricao", columnDefinition = "TEXT")
	private String descricao;
	
	@Column(name="Latitude", nullable=false)	
	private Float latitude;
	
	@Column(name="Longitude", nullable=false)	
	private Float longitude;
	
	@Column(name="Disponivel", nullable=false, length=1)	
	private boolean disponivel;
	
	@Column(name="Instantaneos_livres", nullable=false)
	private int instantaneos_livres;
	
	@Column(name="Instantaneos_total", nullable=false)
	private int instantaneos_total;
	
	@Column(name="Total_lugares", nullable=false)
	private int total_lugares;
	
	@Column(name="Caminho_foto", columnDefinition = "TEXT")
	private String caminho_foto;
	
	@OneToMany(mappedBy="parque", targetEntity= LugarEstacionamento.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<LugarEstacionamento> lugaresEspeciais = new HashSet<>();
	
	@OneToMany(targetEntity= Precario.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="ParqueID", nullable=false)
	private Set<Precario> precarios = new HashSet<>();

	@ManyToOne(targetEntity= Estatisticas.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="EstatisticasID", referencedColumnName="ID", nullable=false)
	private Estatisticas estatisticas;

	@ManyToOne(targetEntity= Horario.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="HorarioID", referencedColumnName="ID")
	private Horario horario;

	public Parque(String nome, String morada, String descricao, Float latitude, Float longitude, boolean disponivel, int instantaneos_livres, int instantaneos_total, int total_lugares, String caminho_foto) {
		this.estatisticas = new Estatisticas();
		this.horario = new Horario();
		this.nome = nome;
		this.morada = morada;
		this.descricao = descricao;
		this.latitude = latitude;
		this.longitude = longitude;
		this.disponivel = disponivel;
		this.instantaneos_livres = instantaneos_livres;
		this.instantaneos_total = instantaneos_total;
		this.total_lugares = total_lugares;
		this.caminho_foto = caminho_foto;
	}

	public void addPrecario(Precario precario){
		if(precarios == null)
			precarios = new HashSet<>();
		precarios.add(precario);
	}

	public void addLugarEstacionamento(LugarEstacionamento lugar){
		lugaresEspeciais.add(lugar);
	}
	public void incLugaresInstantaneos(int n){
		this.instantaneos_livres = Math.min(instantaneos_total, instantaneos_livres + n);
	}
	public void decLugaresInstantaneos(int n){
		this.instantaneos_livres = Math.max(0, instantaneos_livres - n);
	}

	@Override
	public String toString() {
		return "Parque{" +
				"id=" + id +
				", estatisticas=" + estatisticas +
				", horario=" + horario +
				", nome='" + nome + '\'' +
				", descricao='" + descricao + '\'' +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", disponivel=" + disponivel +
				", instantaneos_livres=" + instantaneos_livres +
				", instantaneos_total=" + instantaneos_total +
				", total_lugares=" + total_lugares +
				", caminho_foto='" + caminho_foto + '\'' +
				", lugaresEspeciais=" + lugaresEspeciais +
				", precarios=" + precarios +
				'}';
	}
}
