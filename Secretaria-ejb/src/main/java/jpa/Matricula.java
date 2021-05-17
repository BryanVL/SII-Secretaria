package jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.*;

@Entity
public class Matricula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Matricula_PK id;
	
	@Column(length=10, nullable=false)
	private String Estado;
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date Fecha_de_matricula;
	@Column(length=10)
	private Integer Num_Archivo;
	@Column(length=6)
	private String Turno_Preferente;
	@Column(length=2)
	private String Nuevo_Ingreso;
	private String Listado_Asignaturas;
	
	@OneToMany(mappedBy="matricula")
	private List<Asignaturas_Matricula> AsigMat;
	
	@MapsId("idExp")
	@ManyToOne
	private Expediente expediente;
	
	public Matricula() {
		super();
	}


	public Matricula_PK getId() {
		return id;
	}


	public void setId(Matricula_PK id) {
		this.id = id;
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


	public Integer getNum_Archivo() {
		return Num_Archivo;
	}


	public void setNum_Archivo(Integer num_Archivo) {
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
	
	public String getListado_Asignaturas() {
		return Listado_Asignaturas;
	}


	public void setListado_Asignaturas(String listado_Asignaturas) {
		Listado_Asignaturas = listado_Asignaturas;
	}

	public List<Asignaturas_Matricula> getAsigMat() {
		return AsigMat;
	}


	public void setAsigMat(List<Asignaturas_Matricula> asigMat) {
		AsigMat = asigMat;
	}


	public Expediente getExpediente() {
		return expediente;
	}


	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Matricula other = (Matricula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ","Matricula [","]");
		StringJoiner sj2;
		if(id != null) 						{sj.add("id=" + id.toString());}
		if(Estado != null) 					{sj.add("Estado=" + Estado);}
		if(Fecha_de_matricula != null) 		{sj.add("Fecha_de_matricula=" + Fecha_de_matricula.toString());}
		if(Num_Archivo != null) 			{sj.add("Num_Archivo=" + Num_Archivo);}
		if(Turno_Preferente != null) 		{sj.add("Turno_Preferente=" + Turno_Preferente);}
		if(Nuevo_Ingreso != null) 			{sj.add("Nuevo_Ingreso=" + Nuevo_Ingreso);}
		if(Listado_Asignaturas != null) 	{sj.add("Listado_Asignaturas=" + Listado_Asignaturas);}
		if(AsigMat != null) {
			sj2 = new StringJoiner(", ","Asignaturas_Matricula=(",")");
			for(Asignaturas_Matricula e : AsigMat) { if(e.getId() != null) { sj2.add(e.getId().toString());}}
			sj.add(sj2.toString());
		}
		if(expediente != null) 				{sj.add("expediente=" + expediente.getNum_expediente());}
		return sj.toString();
	}

}