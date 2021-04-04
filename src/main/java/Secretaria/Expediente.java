package Secretaria;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

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
	
	@OneToMany(mappedBy="expediente", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Encuesta> encuestas;
	
	@OneToMany(mappedBy="expediente", cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Num_expediente == null) ? 0 : Num_expediente.hashCode());
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
		Expediente other = (Expediente) obj;
		if (Num_expediente == null) {
			if (other.Num_expediente != null)
				return false;
		} else if (!Num_expediente.equals(other.Num_expediente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String res = "Expediente [" + (Num_expediente != null ? "Num_expediente=" + Num_expediente : "")
				+ (Activo != null ? ", Activo=" + Activo : "")
				+ (Nota_media_provisional != null ? ", Nota_media_provisional=" + Nota_media_provisional : "")
				+ (Creditos_superados != null ? ", Creditos_superados=" + Creditos_superados : "")
				+ (titulacion != null ? ", Titulacion=" + titulacion.getCodigo() : "")
				+ (alumno != null ? ", Alumno=" + alumno.getID() : "");
		StringJoiner sj = new StringJoiner(", ", "(",")");
		if(encuestas != null) {
			res += ", Encuestas=";
			for(Encuesta e : encuestas) {
				sj.add(e.getFecha_envio() != null ? e.getFecha_envio().toString() : "");
			}
			res += sj.toString();
		}
		StringJoiner sj2 = new StringJoiner(", ", "(",")");
		if(matriculas != null) {
			res += ", Matriculas=";
			for(Matricula m : matriculas) {
				sj2.add(m.getId().toString() != null ? m.getId().toString() : "");
			}
			res += sj2.toString();
		}
	
		return res +  "]";
	}

}
