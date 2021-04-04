package Secretaria;

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
		String res = "Matricula [" + (id != null ? "id=" + id.toString() : "") + (Estado != null ? ", Estado=" + Estado : "")
				+ (Fecha_de_matricula != null ? ", Fecha_de_matricula=" + Fecha_de_matricula : "")
				+ (Num_Archivo != null ? ", Num_Archivo=" + Num_Archivo : "")
				+ (Turno_Preferente != null ? ", Turno_Preferente=" + Turno_Preferente : "")
				+ (Nuevo_Ingreso != null ? ", Nuevo_Ingreso=" + Nuevo_Ingreso : "");
		StringJoiner sj = new StringJoiner(", ", "(",")");
		if(AsigMat != null) {
			res += ", Asignaturas_Matricula=";
			for(Asignaturas_Matricula a : AsigMat) {
				sj.add(a.getId() != null ? a.getId().toString() : "");
			}
			res += sj.toString();
		}
			res	+= (expediente != null ? ", expediente=" + expediente.getNum_expediente() : "") + "]";
		return res;
	}
}
