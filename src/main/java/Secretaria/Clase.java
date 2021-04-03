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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Dia == null) ? 0 : Dia.hashCode());
		result = prime * result + ((Hora_inicio == null) ? 0 : Hora_inicio.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clase other = (Clase) obj;
		if (Dia == null) {
			if (other.Dia != null)
				return false;
		} else if (!Dia.equals(other.Dia))
			return false;
		if (Hora_inicio == null) {
			if (other.Hora_inicio != null)
				return false;
		} else if (!Hora_inicio.equals(other.Hora_inicio))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		return true;
	}
	
	
}
