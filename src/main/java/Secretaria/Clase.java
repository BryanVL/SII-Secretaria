package Secretaria;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Clase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*@EmbeddedId
	private ClasePK id;*/
	@Id
	@Temporal(TemporalType.DATE)
	private Date Dia;
	@Id
	@Temporal(TemporalType.TIME)
	private Date Hora_inicio;

	@Temporal(TemporalType.TIME)
	private Date Hora_fin;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Asignatura asignatura;

	@ManyToOne @Id
	private Grupo grupo;

	
	public Clase() {
		super();
	}
	
	/*public ClasePK getId() {
		return id;
	}

	public void setId(ClasePK id) {
		this.id = id;
	}*/
	
	
	public Date getHora_fin() {
		return Hora_fin;
	}

	public void setHora_fin(Date hora_fin) {
		Hora_fin = hora_fin;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	
}
