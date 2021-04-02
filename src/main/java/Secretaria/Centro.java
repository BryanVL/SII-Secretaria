package Secretaria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Centro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	@Column(unique=true, length=80, nullable=false)
	private String Nombre;
	@Column(length=300, nullable=false)
	private String Direccion;
	@Column(precision=9)
	private Double TLF_Conserjeria;
	
	@ManyToMany
	private List<Titulacion> titulaciones;
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
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
	public Double getTLF_Conserjeria() {
		return TLF_Conserjeria;
	}
	public void setTLF_Conserjeria(Double tLF_Conserjeria) {
		TLF_Conserjeria = tLF_Conserjeria;
	}
	
}
