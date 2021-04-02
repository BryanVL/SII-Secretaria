package Secretaria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Asignatura
 *
 */
@Entity

public class Asignatura implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id @Column(precision=5)
	private Double Referencia;
	@Column(precision=3, nullable=false)
	private Double Codigo;
	@Column(precision=2, nullable=false)
	private Double Creditos_total;
	@Column(precision=2)
	private Double Creditos_teoria;
	@Column(length=2, nullable=false)
	private String Ofertada;
	@Column(length=30, nullable=false)
	private String Nombre;
	@Column(precision=1)
	private Double Curso;
	@Column(length=10)
	private String Caracter;
	@Column(precision=1)
	private Double Duracion;
	@Column(length=10)
	private String Unidad_temporal;
	
	
	@OneToMany(mappedBy="asignatura")
	private List<Idiomas> idiomas;
	
	@ManyToOne
	private Titulacion titulacion;
	/*
	@OneToMany(mappedBy="asignatura")
	private List<Clase> clases;
	
	@OneToMany(mappedBy="asignatura")
	private List<Grupos_por_Asignatura> GrAsig;
	
	@OneToMany(mappedBy="asignatura")
	private List<Asignaturas_Matricula> AsigMat;
	*/

	public Asignatura() {
		super();
	}

	public Double getReferencia() {
		return Referencia;
	}

	public void setReferencia(Double referencia) {
		Referencia = referencia;
	}

	public Double getCodigo() {
		return Codigo;
	}

	public void setCodigo(Double codigo) {
		Codigo = codigo;
	}

	public Double getCreditos_total() {
		return Creditos_total;
	}

	public void setCreditos_total(Double creditos_total) {
		Creditos_total = creditos_total;
	}

	public Double getCreditos_teoria() {
		return Creditos_teoria;
	}

	public void setCreditos_teoria(Double creditos_teoria) {
		Creditos_teoria = creditos_teoria;
	}

	public String getOfertada() {
		return Ofertada;
	}

	public void setOfertada(String ofertada) {
		Ofertada = ofertada;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Double getCurso() {
		return Curso;
	}

	public void setCurso(Double curso) {
		Curso = curso;
	}

	public String getCaracter() {
		return Caracter;
	}

	public void setCaracter(String caracter) {
		Caracter = caracter;
	}

	public Double getDuracion() {
		return Duracion;
	}

	public void setDuracion(Double duracion) {
		Duracion = duracion;
	}

	public String getUnidad_temporal() {
		return Unidad_temporal;
	}

	public void setUnidad_temporal(String unidad_temporal) {
		Unidad_temporal = unidad_temporal;
	}

	public List<Idiomas> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<Idiomas> idiomas) {
		this.idiomas = idiomas;
	}
   
}


