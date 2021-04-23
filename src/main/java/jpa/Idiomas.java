package jpa;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

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
	
	@ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
	private List<Asignatura> asignaturas;
	
	
	public Idiomas() {
		super();
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}


	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
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

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ","Idiomas [","]");
		if(Nombre != null) 			{sj.add("Nombre=" + Nombre);}
		return sj.toString();
	}


}
