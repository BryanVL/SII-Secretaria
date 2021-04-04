package Secretaria;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Clase_PK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date Dia;
	@Temporal(TemporalType.TIME)
	private Date Hora_inicio;
	private Long idG;
	
	public Clase_PK() {
		super();
	}
	
	public Date getDia() {
		return Dia;
	}
	public void setDia(Date dia) {
		Dia = dia;
	}
	public Date getHora_inicio() {
		return Hora_inicio;
	}
	public void setHora_inicio(Date hora_inicio) {
		Hora_inicio = hora_inicio;
	}
	public Long getIdG() {
		return idG;
	}
	public void setIdG(Long idG) {
		this.idG = idG;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Dia == null) ? 0 : Dia.hashCode());
		result = prime * result + ((Hora_inicio == null) ? 0 : Hora_inicio.hashCode());
		result = prime * result + ((idG == null) ? 0 : idG.hashCode());
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
		Clase_PK other = (Clase_PK) obj;
		if (Dia == null) {
			if (other.Dia != null)
				return false;
		} else if (!Dia.equals(other.Dia))
			return false;
		if (Hora_inicio == null) {
			if (other.Hora_inicio != null)
				return false;
		} else if (!Hora_inicio.equals(other.Hora_inicio))
			return false;
		if (idG == null) {
			if (other.idG != null)
				return false;
		} else if (!idG.equals(other.idG))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{" + (Dia != null ? "Dia=" + Dia + ", " : "")
				+ (Hora_inicio != null ? "Hora_inicio=" + Hora_inicio + ", " : "") + (idG != null ? "idG=" + idG : "")
				+ "}";
	}
	
	
	
}
