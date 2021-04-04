package Secretaria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Titulacion
 *
 */
@Entity

public class Titulacion implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length=4)
	private Integer Codigo;
	@Column(length=20, nullable=false)
	private String Nombre;
	@Column(precision=3, nullable=false)
	private Float Creditos;
	
	@OneToMany(mappedBy="titulacion")
	private List<Asignatura> asignaturas;
	
	@OneToMany(mappedBy="titulacion")
	private List<Expediente> expedientes;
	
	@OneToMany(mappedBy="titulacion")
	private List<Grupo> grupos;
	
	@ManyToMany(mappedBy="titulaciones")
	private List<Centro> centros;
	
	
	public Titulacion() {
		super();
	}


	public Integer getCodigo() {
		return Codigo;
	}


	public void setCodigo(Integer codigo) {
		Codigo = codigo;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public Float getCreditos() {
		return Creditos;
	}


	public void setCreditos(Float creditos) {
		Creditos = creditos;
	}


	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}


	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}


	public List<Expediente> getExpedientes() {
		return expedientes;
	}


	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
	}


	public List<Grupo> getGrupos() {
		return grupos;
	}


	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}


	public List<Centro> getCentros() {
		return centros;
	}


	public void setCentros(List<Centro> centros) {
		this.centros = centros;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Codigo == null) ? 0 : Codigo.hashCode());
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
		Titulacion other = (Titulacion) obj;
		if (Codigo == null) {
			if (other.Codigo != null)
				return false;
		} else if (!Codigo.equals(other.Codigo))
			return false;
		return true;
	}

	
   
}
