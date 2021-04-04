package Secretaria;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class Matricula_PK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(length=10)
	private String Curso_academico;
	@Column(length=9)
	private Integer idExp;
	
	//Quitar este constructor
	public Matricula_PK(String curso, Integer id) {
		Curso_academico = curso;
		idExp = id;
	}
	
	public Matricula_PK() {
		super();
	}
	
	public String getCurso_academico() {
		return Curso_academico;
	}
	public void setCurso_academico(String curso_academico) {
		Curso_academico = curso_academico;
	}
	public Integer getIdExp() {
		return idExp;
	}
	public void setIdExp(Integer idExp) {
		this.idExp = idExp;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Curso_academico == null) ? 0 : Curso_academico.hashCode());
		result = prime * result + ((idExp == null) ? 0 : idExp.hashCode());
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
		Matricula_PK other = (Matricula_PK) obj;
		if (Curso_academico == null) {
			if (other.Curso_academico != null)
				return false;
		} else if (!Curso_academico.equals(other.Curso_academico))
			return false;
		if (idExp == null) {
			if (other.idExp != null)
				return false;
		} else if (!idExp.equals(other.idExp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{" + (Curso_academico != null ? "Curso_academico=" + Curso_academico + ", " : "")
				+ (idExp != null ? "idExp=" + idExp : "") + "}";
	}
	
	
}
