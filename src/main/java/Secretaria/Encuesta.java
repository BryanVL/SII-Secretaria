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
	@JoinColumn(nullable=false)
	private Expediente expediente;
	
	@ManyToMany(mappedBy="encuestas")
	private List<Grupos_por_Asignatura> GrAsig;

	
	public Encuesta() {
		super();
	}
	
	public Date getFecha_envio() {
		return Fecha_envio;
	}

	public void setFecha_envio(Date fecha_envio) {
		Fecha_envio = fecha_envio;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public List<Grupos_por_Asignatura> getGrAsig() {
		return GrAsig;
	}

	public void setGrAsig(List<Grupos_por_Asignatura> grAsig) {
		GrAsig = grAsig;
	}
	
	
}
