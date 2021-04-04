package Secretaria;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.*;

@Entity
public class Grupos_por_Asignatura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Grupos_por_Asignatura_PK id;
	
	@Column(length=10)
	private String Oferta;
	
	@ManyToMany
	private List<Encuesta> encuestas;
	
	@MapsId("idG")
	@ManyToOne
	private Grupo grupo;
	@MapsId("idAsig")
	@ManyToOne
	private Asignatura asignatura;
	
	
	public Grupos_por_Asignatura() {
		super();
	}


	public Grupos_por_Asignatura_PK getId() {
		return id;
	}


	public void setId(Grupos_por_Asignatura_PK id) {
		this.id = id;
	}


	public String getOferta() {
		return Oferta;
	}


	public void setOferta(String oferta) {
		Oferta = oferta;
	}


	public List<Encuesta> getEncuestas() {
		return encuestas;
	}


	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}


	public Grupo getGrupo() {
		return grupo;
	}


	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}


	public Asignatura getAsignatura() {
		return asignatura;
	}


	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
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
		Grupos_por_Asignatura other = (Grupos_por_Asignatura) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		String res = "Grupos_por_Asignatura [" + (id != null ? "id=" + id.toString() : "")
				+ (Oferta != null ? ", Oferta=" + Oferta : "");
		StringJoiner sj = new StringJoiner(", ", "(",")");
		if(encuestas != null) {
			res += ", Encuestas=";
			for(Encuesta e : encuestas) {
				sj.add(e.getFecha_envio() != null ? e.getFecha_envio().toString() : "");
			}
			res += sj.toString();
		}
			res	+= (grupo != null ? ", grupo=" + grupo.getID() : "")
				+ (asignatura != null ? ", asignatura=" + asignatura.getReferencia() : "") + "]";
		return res;
	}
	
	
	
	
	
	
}
