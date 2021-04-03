package Secretaria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Grupos_por_Asignatura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @Column(length=9)
	private String Curso_Academico;
	@Column(length=10)
	private String Oferta;
	
	@ManyToMany
	private List<Encuesta> encuestas;
	
	@Id @ManyToOne
	private Grupo grupo;
	@Id @ManyToOne
	private Asignatura asignatura;
	
	
	public Grupos_por_Asignatura() {
		super();
	}
	
	public String getCurso_Academico() {
		return Curso_Academico;
	}
	public void setCurso_Academico(String curso_Academico) {
		Curso_Academico = curso_Academico;
	}
	public String getOferta() {
		return Oferta;
	}
	public void setOferta(String oferta) {
		Oferta = oferta;
	}
	public List<Encuesta> getEncuestas() {
		return encuestas;
	}
	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Curso_Academico == null) ? 0 : Curso_Academico.hashCode());
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
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
		Grupos_por_Asignatura other = (Grupos_por_Asignatura) obj;
		if (Curso_Academico == null) {
			if (other.Curso_Academico != null)
				return false;
		} else if (!Curso_Academico.equals(other.Curso_Academico))
			return false;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		return true;
	}
	
	
	
	
}
