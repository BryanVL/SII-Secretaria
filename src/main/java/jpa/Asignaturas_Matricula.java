package jpa;

import java.io.Serializable;
import java.util.StringJoiner;

import javax.persistence.*;

@Entity
public class Asignaturas_Matricula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Asignaturas_Matricula_PK id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Grupo grupo;
	
	@MapsId("idAsig")
	@ManyToOne
	private Asignatura asignatura;
	
	@MapsId("idM")
	@Id @ManyToOne
	private Matricula matricula;

	private Boolean asignacionAutomatica; //Si es true la asignación ha sido automatica
	
	
	public Asignaturas_Matricula() {
		super();
	}

	public Asignaturas_Matricula_PK getId() {
		return id;
	}



	public void setId(Asignaturas_Matricula_PK id) {
		this.id = id;
	}



	public Grupo getGrupo() {
		return grupo;
	}



	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}



	public Asignatura getAsignatura() {
		return asignatura;
	}



	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}



	public Matricula getMatricula() {
		return matricula;
	}

	
	public Boolean getAsignacionAutomatica() {
		return asignacionAutomatica;
	}


	public void setAsignacionAutomatica(Boolean asignacionAutomatica) {
		this.asignacionAutomatica = asignacionAutomatica;
	}



	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
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
		Asignaturas_Matricula other = (Asignaturas_Matricula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ","Asignaturas_Matricula [","]");
		if(id != null) 				{sj.add("id=" + id.toString());}
		if(grupo != null) 			{sj.add("grupo=" + grupo.getID());}
		if(asignatura != null) 		{sj.add("asignatura=" + asignatura.getReferencia());}
		if(matricula != null) 		{sj.add("matricula=" + matricula.getId().toString());}
		return sj.toString();
	}
	
}
