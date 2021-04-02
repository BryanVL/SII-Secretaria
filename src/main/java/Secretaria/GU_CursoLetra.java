package Secretaria;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GU_CursoLetra {

	/**
	 * 
	 */
	
	@Column(length=1)
	private Integer Curso;
	@Column(length=1)
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
	
	
}
