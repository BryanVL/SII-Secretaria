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

	@Id @Column(length=5)
	private Integer Referencia;
	@Column(length=3, nullable=false)
	private Integer Codigo;
	@Column(precision=2, scale=1, nullable=false)
	private Float Creditos_total;
	@Column(precision=2,scale=1)
	private Float Creditos_teoria;
	@Column(length=2, nullable=false)
	private String Ofertada;
	@Column(length=30, nullable=false)
	private String Nombre;
	@Column(length=1)
	private Integer Curso;
	@Column(length=10)
	private String Caracter;
	@Column(length=1)
	private Integer Duracion;
	@Column(length=10, nullable=false)
	private String Unidad_temporal;
	
	
	@OneToMany(mappedBy="asignatura")
	private List<Idiomas> idiomas;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Titulacion titulacion;
	
	@OneToMany(mappedBy="asignatura")
	private List<Clase> clases;
	
	@OneToMany(mappedBy="asignatura")
	private List<Grupos_por_Asignatura> GrAsig;
	
	@OneToMany(mappedBy="asignatura")
	private List<Asignaturas_Matricula> AsigMat;
	
	@OneToOne(mappedBy="asignatura")
	private Optativa optativa;

	
	public Asignatura() {
		super();
	}


	public Integer getReferencia() {
		return Referencia;
	}


	public void setReferencia(Integer referencia) {
		Referencia = referencia;
	}


	public Integer getCodigo() {
		return Codigo;
	}


	public void setCodigo(Integer codigo) {
		Codigo = codigo;
	}


	public Float getCreditos_total() {
		return Creditos_total;
	}


	public void setCreditos_total(Float creditos_total) {
		Creditos_total = creditos_total;
	}


	public Float getCreditos_teoria() {
		return Creditos_teoria;
	}


	public void setCreditos_teoria(Float creditos_teoria) {
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


	public Integer getCurso() {
		return Curso;
	}


	public void setCurso(Integer curso) {
		Curso = curso;
	}


	public String getCaracter() {
		return Caracter;
	}


	public void setCaracter(String caracter) {
		Caracter = caracter;
	}


	public Integer getDuracion() {
		return Duracion;
	}


	public void setDuracion(Integer duracion) {
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


	public Titulacion getTitulacion() {
		return titulacion;
	}


	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}


	public List<Clase> getClases() {
		return clases;
	}


	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}


	public List<Grupos_por_Asignatura> getGrAsig() {
		return GrAsig;
	}


	public void setGrAsig(List<Grupos_por_Asignatura> grAsig) {
		GrAsig = grAsig;
	}


	public List<Asignaturas_Matricula> getAsigMat() {
		return AsigMat;
	}


	public void setAsigMat(List<Asignaturas_Matricula> asigMat) {
		AsigMat = asigMat;
	}


	public Optativa getOptativa() {
		return optativa;
	}


	public void setOptativa(Optativa optativa) {
		this.optativa = optativa;
	}

	
   
}


