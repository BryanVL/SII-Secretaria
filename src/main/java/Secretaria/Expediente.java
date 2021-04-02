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
	
	@Id @Column(precision=9)
	private Double Num_expediente;
	private Boolean Activo;
	@Column(precision=4, scale=2)
	private Double Nota_media_provisional;
	@Column(precision=3)
	private Double Creditos_superados;
	
	@ManyToOne
	private Titulacion titulacion;
	
	@ManyToOne
	private Alumno alumno;
	
	@OneToMany(mappedBy="expediente")
	private List<Encuesta> encuestas;
	
	@OneToMany(mappedBy="expediente")
	private List<Matricula> matriculas;
	
	
	public Double getNum_expediente() {
		return Num_expediente;
	}
	public void setNum_expediente(Double num_expediente) {
		Num_expediente = num_expediente;
	}
	public Boolean getActivo() {
		return Activo;
	}
	public void setActivo(Boolean activo) {
		Activo = activo;
	}
	public Double getNota_media_provisional() {
		return Nota_media_provisional;
	}
	public void setNota_media_provisional(Double nota_media_provisional) {
		Nota_media_provisional = nota_media_provisional;
	}
	public Double getCreditos_superados() {
		return Creditos_superados;
	}
	public void setCreditos_superados(Double creditos_superados) {
		Creditos_superados = creditos_superados;
	}

}
