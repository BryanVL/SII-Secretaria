package jpa;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Titulacion
 *
 */
@Entity

public class Titulacion implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length=4)
	private Integer Codigo;
	@Column(length=20, nullable=false)
	private String Nombre;
	@Column(precision=3, nullable=false)
	private Float Creditos;
	
	@OneToMany(mappedBy="titulacion", cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
	private List<Asignatura> asignaturas;
	
	@OneToMany(mappedBy="titulacion", cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
	private List<Expediente> expedientes;
	
	@OneToMany(mappedBy="titulacion", cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
	private List<Grupo> grupos;
	
	@ManyToMany(mappedBy="titulaciones")
	@JoinColumn(nullable=false)
	private List<Centro> centros;
	
	public Titulacion() {
		super();
	}


	public Integer getCodigo() {
		return Codigo;
	}


	public void setCodigo(Integer codigo) {
		Codigo = codigo;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public Float getCreditos() {
		return Creditos;
	}


	public void setCreditos(Float creditos) {
		Creditos = creditos;
	}


	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}


	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}


	public List<Expediente> getExpedientes() {
		return expedientes;
	}


	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
	}


	public List<Grupo> getGrupos() {
		return grupos;
	}


	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}


	public List<Centro> getCentros() {
		return centros;
	}


	public void setCentros(List<Centro> centros) {
		this.centros = centros;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Codigo == null) ? 0 : Codigo.hashCode());
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
		Titulacion other = (Titulacion) obj;
		if (Codigo == null) {
			if (other.Codigo != null)
				return false;
		} else if (!Codigo.equals(other.Codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ","Titulacion [","]");
		StringJoiner sj2;
		if(Codigo != null) 				{sj.add("Codigo=" + Codigo);}
		if(Nombre != null) 				{sj.add("Nombre=" + Nombre);}
		if(Creditos != null) 			{sj.add("Creditos=" + Creditos);}
		if(asignaturas != null) {
			sj2 = new StringJoiner(", ","Asignaturas=(",")");
			for(Asignatura e : asignaturas) { if(e.getReferencia() != null) { sj2.add(e.getReferencia().toString());}}
			sj.add(sj2.toString());
		}
		if(expedientes != null) {
			sj2 = new StringJoiner(", ","Expedientes=(",")");
			for(Expediente e : expedientes) { if(e.getNum_expediente() != null) { sj2.add(e.getNum_expediente().toString());}}
			sj.add(sj2.toString());
		}
		if(grupos != null) {
			sj2 = new StringJoiner(", ","Grupos=(",")");
			for(Grupo e : grupos) { if(e.getID() != null) { sj2.add(e.getID().toString());}}
			sj.add(sj2.toString());
		}
		if(centros != null) {
			sj2 = new StringJoiner(", ","Centros=(",")");
			for(Centro e : centros) { if(e.getID() != null) { sj2.add(e.getID().toString());}}
			sj.add(sj2.toString());
		}
		return sj.toString();
	}
	
}
