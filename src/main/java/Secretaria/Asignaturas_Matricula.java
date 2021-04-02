package Secretaria;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Asignaturas_Matricula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	private Grupo grupo;
	
	@Id @ManyToOne
	private Asignatura asignatura;
	
	@ManyToOne
	private Matricula matricula;
	
}
