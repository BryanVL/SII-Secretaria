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
	@JoinColumn(nullable=false)
	private Grupo grupo;
	
	@Id @ManyToOne
	private Asignatura asignatura;
	
	@Id @ManyToOne
	private Matricula matricula;

	
	
	public Asignaturas_Matricula() {
		super();
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

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
}
