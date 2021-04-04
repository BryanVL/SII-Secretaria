package Secretaria;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GU_CursoLetra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(length=1, nullable=false)
	private Integer Curso;
	@Column(length=1, nullable=false)
	private String Letra;
	
	
	public GU_CursoLetra() {
		super();
	}
	
	public Integer getCurso() {
		return Curso;
	}
	public void setCurso(Integer curso) {
		Curso = curso;
	}
	public String getLetra() {
		return Letra;
	}
	public void setLetra(String letra) {
		Letra = letra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Curso == null) ? 0 : Curso.hashCode());
		result = prime * result + ((Letra == null) ? 0 : Letra.hashCode());
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
		GU_CursoLetra other = (GU_CursoLetra) obj;
		if (Curso == null) {
			if (other.Curso != null)
				return false;
		} else if (!Curso.equals(other.Curso))
			return false;
		if (Letra == null) {
			if (other.Letra != null)
				return false;
		} else if (!Letra.equals(other.Letra))
			return false;
		return true;
	}
	
	
}
