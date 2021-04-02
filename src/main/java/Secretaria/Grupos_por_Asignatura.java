package Secretaria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Grupos_por_Asignatura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @Column(length=9)
	private String Curso_Academico;
	@Column(length=10)
	private String Oferta;
	
	@ManyToMany
	private List<Encuesta> encuestas;
	
	@Id @ManyToOne
	private Grupo grupo;
	@Id @ManyToOne
	private Asignatura asignatura;
	
	
	public Grupos_por_Asignatura() {
		super();
	}
	
	public String getCurso_Academico() {
		return Curso_Academico;
	}
	public void setCurso_Academico(String curso_Academico) {
		Curso_Academico = curso_Academico;
	}
	public String getOferta() {
		return Oferta;
	}
	public void setOferta(String oferta) {
		Oferta = oferta;
	}
	public List<Encuesta> getEncuestas() {
		return encuestas;
	}
	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Asignatura getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	
	
	
	
}
