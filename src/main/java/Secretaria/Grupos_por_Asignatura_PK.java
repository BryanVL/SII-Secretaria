package Secretaria;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Grupos_por_Asignatura_PK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Grupos_por_Asignatura_PK() {
		super();
	}
	
	@Column(length=9)
	private String Curso_Academico;
	
	private Long idG;
	@Column(length=5)
	private Integer idAsig;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Curso_Academico == null) ? 0 : Curso_Academico.hashCode());
		result = prime * result + ((idAsig == null) ? 0 : idAsig.hashCode());
		result = prime * result + ((idG == null) ? 0 : idG.hashCode());
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
		Grupos_por_Asignatura_PK other = (Grupos_por_Asignatura_PK) obj;
		if (Curso_Academico == null) {
			if (other.Curso_Academico != null)
				return false;
		} else if (!Curso_Academico.equals(other.Curso_Academico))
			return false;
		if (idAsig == null) {
			if (other.idAsig != null)
				return false;
		} else if (!idAsig.equals(other.idAsig))
			return false;
		if (idG == null) {
			if (other.idG != null)
				return false;
		} else if (!idG.equals(other.idG))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{" + (Curso_Academico != null ? "Curso_Academico=" + Curso_Academico + ", " : "")
				+ (idG != null ? "idG=" + idG + ", " : "") + (idAsig != null ? "idAsig=" + idAsig : "") + "}";
	}
	
}
