package Secretaria;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Clase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*@EmbeddedId
	private ClasePK id;
	*/
	@Id
	private long id;
	
	@Temporal(TemporalType.TIME)
	private Date Hora_fin;
	
	@ManyToOne
	private Asignatura asignatura;
	
	@Id @ManyToOne
	private Grupo grupo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getHora_fin() {
		return Hora_fin;
	}
	public void setHora_fin(Date hora_fin) {
		Hora_fin = hora_fin;
	}
	
	
}
