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
	
	@EmbeddedId
	private Clase_PK id;

	@Temporal(TemporalType.TIME)
	private Date Hora_fin;
	
	@ManyToOne
	private Asignatura asignatura;

	@MapsId("idG")
	@ManyToOne
	private Grupo grupo;

	
	public Clase() {
		super();
	}
	
	public Clase_PK getId() {
		return id;
	}

	public void setId(Clase_PK id) {
		this.id = id;
	}
	
	
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
