package Secretaria;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Idiomas
 *
 */
@Entity

public class Idiomas implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length=20)
	private String Nombre;
	@ManyToOne
	private Asignatura asignatura;
	
	
	public Idiomas() {
		super();
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public Asignatura getAsignatura() {
		return asignatura;
	}


	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
   
}
