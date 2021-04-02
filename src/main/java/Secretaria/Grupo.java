package Secretaria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Grupo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;
	@Embedded @Column(unique=true, nullable=false)
	private GU_CursoLetra cl;
	@Column(length=6, nullable=false)
	private String Turno_Mañana_Tarde;
	@Column(length=2, nullable=false)
	private String Ingles;
	private Boolean Visible;
	private Boolean Asignar;
	@Column(precision=3)
	private Double Plazas;
	
	@ManyToOne
	private Titulacion titulacion;
	
	@OneToMany(mappedBy="ID1")
	private List<Grupo> grupos;
	@ManyToOne
	private Grupo ID1;
	
	@OneToMany(mappedBy="grupo")
	private List<Asignaturas_Matricula> AsMat;
	
	@OneToMany(mappedBy="grupo")
	private List<Clase> clases;
	
	@OneToMany(mappedBy="grupo")
	private List<Grupos_por_Asignatura> GrAsig;
	
	
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public GU_CursoLetra getCl() {
		return cl;
	}
	public void setCl(GU_CursoLetra cl) {
		this.cl = cl;
	}
	public String getTurno_Mañana_Tarde() {
		return Turno_Mañana_Tarde;
	}
	public void setTurno_Mañana_Tarde(String turno_Mañana_Tarde) {
		Turno_Mañana_Tarde = turno_Mañana_Tarde;
	}
	public String getIngles() {
		return Ingles;
	}
	public void setIngles(String ingles) {
		Ingles = ingles;
	}
	public Boolean getVisible() {
		return Visible;
	}
	public void setVisible(Boolean visible) {
		Visible = visible;
	}
	public Boolean getAsignar() {
		return Asignar;
	}
	public void setAsignar(Boolean asignar) {
		Asignar = asignar;
	}
	public Double getPlazas() {
		return Plazas;
	}
	public void setPlazas(Double plazas) {
		Plazas = plazas;
	}
	
	
	

}
