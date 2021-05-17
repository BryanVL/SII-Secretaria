package jpa;

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
		StringJoiner sj = new StringJoiner(", ","Grupos_por_Asignatura [","]");
		StringJoiner sj2;
		if(id != null) 				{sj.add("id=" + id.toString());}
		if(Oferta != null) 			{sj.add("Oferta=" + Oferta);}
		if(encuestas != null) {
			sj2 = new StringJoiner(", ","Encuestas=(",")");
			for(Encuesta e : encuestas) { if(e.getFecha_envio() != null) { sj2.add(e.getFecha_envio().toString());}}
			sj.add(sj2.toString());
		}
		if(grupo != null) 			{sj.add("grupo=" + grupo.getID());}
		if(asignatura != null) 		{sj.add("asignatura=" + asignatura.getReferencia());}
		return sj.toString();
	}
	
}
