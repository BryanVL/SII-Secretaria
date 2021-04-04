package Secretaria;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.*;

@Entity
public class Centro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;
	@Column(unique=true, length=80, nullable=false)
	private String Nombre;
	@Column(length=300, nullable=false)
	private String Direccion;
	@Column(length=9)
	private Integer TLF_Conserjeria;
	
	@ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
	private List<Titulacion> titulaciones;
	
	
	public Centro() {
		super();
	}
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public Integer getTLF_Conserjeria() {
		return TLF_Conserjeria;
	}
	public void setTLF_Conserjeria(Integer tLF_Conserjeria) {
		TLF_Conserjeria = tLF_Conserjeria;
	}
	public List<Titulacion> getTitulaciones() {
		return titulaciones;
	}
	public void setTitulaciones(List<Titulacion> titulaciones) {
		this.titulaciones = titulaciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
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
		Centro other = (Centro) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String res = "Centro [" + (ID != null ? "ID=" + ID : "") + (Nombre != null ? ", Nombre=" + Nombre : "")
				+ (Direccion != null ? ", Direccion=" + Direccion : "")
				+ (TLF_Conserjeria != null ? ", TLF_Conserjeria=" + TLF_Conserjeria : "");
		StringJoiner sj = new StringJoiner(", ", "(",")");
		if(titulaciones != null) {
			res += ", Titulaciones=";
			for(Titulacion t : titulaciones) {
				sj.add(t.getCodigo() != null ? t.getCodigo().toString() : "");
			}
			res += sj.toString();
		}
		return res + "]";
	}
	
	
}
