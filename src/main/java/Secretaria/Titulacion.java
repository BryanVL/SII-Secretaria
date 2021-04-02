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
	@Column(precision=4)
	private Double Codigo;
	@Column(length=20, nullable=false)
	private String Nombre;
	@Column(precision=3, nullable=false)
	private Double Creditos;
	
	@OneToMany(mappedBy="titulacion")
	private List<Asignatura> asignaturas;
	/*
	@OneToMany(mappedBy="titulacion")
	private List<Expediente> expedientes;
	
	@OneToMany(mappedBy="titulacion")
	private List<Grupo> grupos;
	
	@ManyToMany(mappedBy="titulaciones")
	private List<Centro> centros;
	*/
	
	public Titulacion() {
		super();
	}

	public Double getCodigo() {
		return Codigo;
	}

	public void setCodigo(Double codigo) {
		Codigo = codigo;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Double getCreditos() {
		return Creditos;
	}

	public void setCreditos(Double creditos) {
		Creditos = creditos;
	}

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
   
}
