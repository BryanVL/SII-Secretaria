package Secretaria;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class ClasePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date Dia;
	@Temporal(TemporalType.TIME)
	private Date Hora_inicio;
	
	
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
	
}
