package Secretaria;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Alumno
 *
 */
@Entity

public class Alumno implements Serializable {
//prueba
	@Id
	private Long dni;
	private static final long serialVersionUID = 1L;

	public Alumno() {
		super();
	}   
	public Long getDni() {
		return this.dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}
   
}
