/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Alexandre Martins(Universidade do Minho)
 * License Type: Academic
 */
package pt.uminho.di.aa.parkfinder.logicaParques;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="TipoLugarEstacionamento")
public class TipoLugarEstacionamento implements Serializable {
	public TipoLugarEstacionamento() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="PT_UMINHO_DI_EA_PARKFINDER_LOGICAPARQUES_TIPOLUGARESTACIONAMENTO_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="PT_UMINHO_DI_EA_PARKFINDER_LOGICAPARQUES_TIPOLUGARESTACIONAMENTO_ID_GENERATOR", strategy="native")	
	private int id;
	
	@Column(name="Nome", nullable=true, length=255)	
	private String nome;
	
	private void setId(int value) {
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
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
