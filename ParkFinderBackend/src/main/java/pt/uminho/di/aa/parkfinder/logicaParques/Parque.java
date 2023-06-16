package pt.uminho.di.aa.parkfinder.logicaParques;

import jakarta.persistence.*;
import java.io.Serializable;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Parque")
public class Parque implements Serializable {
	public Parque() {
	}

	@Column(name="ID", nullable=false, length=10)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity= Estatisticas.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="EstatisticasID", referencedColumnName="ID", nullable=false) }, foreignKey=@ForeignKey(name="FKParque820715"))	
	private Estatisticas estatisticas;
	
	@ManyToOne(targetEntity= Horario.class, fetch=FetchType.LAZY)
	@JoinColumns(value={ @JoinColumn(name="HorarioID", referencedColumnName="ID") }, foreignKey=@ForeignKey(name="FKParque800300"))	
	private Horario horario;

	// TODO: Alterei a varialvel "name" do Parque para "nome" para ficar em português mas pode dar erro algures
	@Column(name="Nome", nullable=true, length=255)
	private String nome;
	
	@Column(name="Descricao", nullable=true, length=255)	
	private String descricao;
	
	@Column(name="Latitude", nullable=false)	
	private float latitude;
	
	@Column(name="Longitude", nullable=false)	
	private float longitude;
	
	@Column(name="Disponivel", nullable=false, length=1)	
	private boolean disponivel;
	
	@Column(name="Instantaneos_livres", nullable=false, length=10)	
	private int instantaneos_livres;
	
	@Column(name="Instantaneos_total", nullable=false, length=10)	
	private int instantaneos_total;
	
	@Column(name="Total_lugares", nullable=false, length=10)	
	private int total_lugares;
	
	@Column(name="Caminho_foto", nullable=true, length=255)	
	private String caminho_foto;
	
	@OneToMany(mappedBy="parque", targetEntity= LugarEstacionamento.class, fetch=FetchType.LAZY)
	private java.util.Set ORM_lugaresEspeciais = new java.util.HashSet();
	
	@OneToMany(targetEntity= Precario.class, fetch=FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name="ParqueID", nullable=false) })
	private java.util.Set ORM_precario = new java.util.HashSet();
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setDescricao(String value) {
		this.descricao = value;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setLatitude(float value) {
		this.latitude = value;
	}
	
	public float getLatitude() {
		return latitude;
	}
	
	public void setLongitude(float value) {
		this.longitude = value;
	}
	
	public float getLongitude() {
		return longitude;
	}
	
	public void setDisponivel(boolean value) {
		this.disponivel = value;
	}
	
	public boolean getDisponivel() {
		return disponivel;
	}
	
	public void setInstantaneos_livres(int value) {
		this.instantaneos_livres = value;
	}
	
	public int getInstantaneos_livres() {
		return instantaneos_livres;
	}
	
	public void setInstantaneos_total(int value) {
		this.instantaneos_total = value;
	}
	
	public int getInstantaneos_total() {
		return instantaneos_total;
	}
	
	public void setTotal_lugares(int value) {
		this.total_lugares = value;
	}
	
	public int getTotal_lugares() {
		return total_lugares;
	}
	
	public void setCaminho_foto(String value) {
		this.caminho_foto = value;
	}
	
	public String getCaminho_foto() {
		return caminho_foto;
	}
	
	private void setORM_LugaresEspeciais(java.util.Set value) {
		this.ORM_lugaresEspeciais = value;
	}
	
	private java.util.Set getORM_LugaresEspeciais() {
		return ORM_lugaresEspeciais;
	}
	
	@Transient	
	public final LugarEstacionamentoSetCollection lugaresEspeciais = new LugarEstacionamentoSetCollection(this, _ormAdapter, ORMConstants.KEY_PARQUE_LUGARESESPECIAIS, ORMConstants.KEY_LUGARESTACIONAMENTO_PARQUE, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Precario(java.util.Set value) {
		this.ORM_precario = value;
	}
	
	private java.util.Set getORM_Precario() {
		return ORM_precario;
	}
	
	@Transient	
	public final PrecarioSetCollection precario = new PrecarioSetCollection(this, _ormAdapter, ORMConstants.KEY_PARQUE_PRECARIO, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void setEstatisticas(Estatisticas value) {
		this.estatisticas = value;
	}
	
	public Estatisticas getEstatisticas() {
		return estatisticas;
	}
	
	public void setHorario(Horario value) {
		this.horario = value;
	}
	
	public Horario getHorario() {
		return horario;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
