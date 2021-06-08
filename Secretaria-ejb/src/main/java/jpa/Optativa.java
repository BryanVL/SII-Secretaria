package jpa;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.*;

@Entity
public class Optativa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @OneToOne
	private Asignatura asignatura;
	
	@Column(length=3, nullable=false)
	private Integer Plazas;
	@Column(length=30)
	private String Mencion;
	
	@ManyToMany
	private List<Titulacion> titulaciones;
	
	public Optativa() {
		super();
	}
	
	public Asignatura getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	public Integer getPlazas() {
		return Plazas;
	}
	public void setPlazas(Integer plazas) {
		Plazas = plazas;
	}
	public String getMencion() {
		return Mencion;
	}
	public void setMencion(String mencion) {
		Mencion = mencion;
	}
	public List<Titulacion> getTitulaciones() {
		return titulaciones;
	}

	public void setTitulaciones(List<Titulacion> titulaciones) {
		this.titulaciones = titulaciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
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
		Optativa other = (Optativa) obj;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ","Optativa [","]");
		if(asignatura != null) 			{sj.add("asignatura=" + asignatura.getReferencia());}
		if(Plazas != null) 				{sj.add("Plazas=" + Plazas);}
		if(Mencion != null) 			{sj.add("Mencion=" + Mencion);}
		return sj.toString();
	}

	
}
