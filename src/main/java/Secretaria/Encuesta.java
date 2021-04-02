package Secretaria;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Encuesta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @Temporal(TemporalType.TIME)
	private Date Fecha_envio;
	
	@ManyToOne
	private Expediente expediente;
	
	@ManyToMany(mappedBy="encuestas")
	private List<Grupos_por_Asignatura> GrAsig;

	public Date getFecha_envio() {
		return Fecha_envio;
	}

	public void setFecha_envio(Date fecha_envio) {
		Fecha_envio = fecha_envio;
	}
	
	
}
