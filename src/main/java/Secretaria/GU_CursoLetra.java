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
	
	@Column(precision=1)
	private Double Curso;
	@Column(length=1)
	private String Letra;
	
	
}
