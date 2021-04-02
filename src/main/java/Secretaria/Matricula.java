package Secretaria;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Matricula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @Column(length=10)
	private String Curso_academico;
	@Column(length=10, nullable=false)
	private String Estado;
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date Fecha_de_matricula;
	@Column(precision=10)
	private Double Num_Archivo;
	@Column(length=6)
	private String Turno_Preferente;
	@Column(length=2)
	private String Nuevo_Ingreso;
	
	@OneToMany(mappedBy="matricula")
	private List<Asignaturas_Matricula> AsigMat;
	
	@ManyToOne
	private Expediente expediente;
	
	
	public String getCurso_academico() {
		return Curso_academico;
	}
	public void setCurso_academico(String curso_academico) {
		Curso_academico = curso_academico;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public Date getFecha_de_matricula() {
		return Fecha_de_matricula;
	}
	public void setFecha_de_matricula(Date fecha_de_matricula) {
		Fecha_de_matricula = fecha_de_matricula;
	}
	public Double getNum_Archivo() {
		return Num_Archivo;
	}
	public void setNum_Archivo(Double num_Archivo) {
		Num_Archivo = num_Archivo;
	}
	public String getTurno_Preferente() {
		return Turno_Preferente;
	}
	public void setTurno_Preferente(String turno_Preferente) {
		Turno_Preferente = turno_Preferente;
	}
	public String getNuevo_Ingreso() {
		return Nuevo_Ingreso;
	}
	public void setNuevo_Ingreso(String nuevo_Ingreso) {
		Nuevo_Ingreso = nuevo_Ingreso;
	}
	
	
}
