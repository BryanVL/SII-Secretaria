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
	@JoinColumn(nullable=false)
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
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
		Idiomas other = (Idiomas) obj;
		if (Nombre == null) {
			if (other.Nombre != null)
				return false;
		} else if (!Nombre.equals(other.Nombre))
			return false;
		return true;
	}
   
}
