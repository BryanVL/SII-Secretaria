package Secretaria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Expediente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @Column(length=9)
	private Integer Num_expediente;
	private Boolean Activo;
	@Column(precision=4, scale=2)
	private Float Nota_media_provisional;
	@Column(precision=3, scale=1)
	private Float Creditos_superados;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Titulacion titulacion;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Alumno alumno;
	
	@OneToMany(mappedBy="expediente")
	private List<Encuesta> encuestas;
	
	@OneToMany(mappedBy="expediente")
	private List<Matricula> matriculas;

	
	public Expediente() {
		super();
	}
	
	public Integer getNum_expediente() {
		return Num_expediente;
	}

	public void setNum_expediente(Integer num_expediente) {
		Num_expediente = num_expediente;
	}

	public Boolean getActivo() {
		return Activo;
	}

	public void setActivo(Boolean activo) {
		Activo = activo;
	}

	public Float getNota_media_provisional() {
		return Nota_media_provisional;
	}

	public void setNota_media_provisional(Float nota_media_provisional) {
		Nota_media_provisional = nota_media_provisional;
	}

	public Float getCreditos_superados() {
		return Creditos_superados;
	}

	public void setCreditos_superados(Float creditos_superados) {
		Creditos_superados = creditos_superados;
	}

	public Titulacion getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<Encuesta> getEncuestas() {
		return encuestas;
	}

	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	

}
