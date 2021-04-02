package Secretaria;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Optativa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @OneToOne
	private Asignatura asignatura;
	
	@Column(length=2, nullable=false)
	private Integer Plazas;
	@Column(length=30)
	private String Mencion;
	
	
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
	
	

	
}
