package Secretaria;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Asignaturas_Matricula_PK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Matricula_PK idM;
	@Column(length=5)
	private Integer idAsig;
	
	public Asignaturas_Matricula_PK() {
		super();
	}
	
	public Matricula_PK getIdM() {
		return idM;
	}
	public void setIdM(Matricula_PK idM) {
		this.idM = idM;
	}
	public Integer getIdAsig() {
		return idAsig;
	}
	public void setIdAsig(Integer idAsig) {
		this.idAsig = idAsig;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAsig == null) ? 0 : idAsig.hashCode());
		result = prime * result + ((idM == null) ? 0 : idM.hashCode());
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
		Asignaturas_Matricula_PK other = (Asignaturas_Matricula_PK) obj;
		if (idAsig == null) {
			if (other.idAsig != null)
				return false;
		} else if (!idAsig.equals(other.idAsig))
			return false;
		if (idM == null) {
			if (other.idM != null)
				return false;
		} else if (!idM.equals(other.idM))
			return false;
		return true;
	}
	
}
