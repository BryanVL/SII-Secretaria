package Secretaria;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Alumno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;
	@Column(unique=true, length=10, nullable=false)
	private String DNI;
	@Column(length=15, nullable=false)
	private String Nombre;
	@Column(length=20, nullable=false)
	private String Apellido1;
	@Column(length=20)
	private String Apellido2;
	@Column(length=40, nullable=false)
	private String Email_institucional;
	@Column(length=40)
	private String Email_personal;
	@Column(length=9)
	private Integer Telefono;
	@Column(length=9)
	private Integer Movil;
	@Column(length=50)
	private String Direccion;
	@Column(length=40)
	private String Localidad;
	@Column(length=25)
	private String Provincia;
	@Column(precision=5)
	private Double CP;
	@Column(length=40)
	private String Usuario;

	
	@OneToMany(mappedBy="alumno", cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
	private List<Expediente> expedientes;
	
	//Quitar este constructor
	public Alumno(Long id, String dni) {
		ID = id;
		DNI = dni;
	}
	
	public Alumno() {
		super();
	}
	
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido1() {
		return Apellido1;
	}

	public void setApellido1(String apellido1) {
		Apellido1 = apellido1;
	}

	public String getApellido2() {
		return Apellido2;
	}

	public void setApellido2(String apellido2) {
		Apellido2 = apellido2;
	}

	public String getEmail_institucional() {
		return Email_institucional;
	}

	public void setEmail_institucional(String email_institucional) {
		Email_institucional = email_institucional;
	}

	public String getEmail_personal() {
		return Email_personal;
	}

	public void setEmail_personal(String email_personal) {
		Email_personal = email_personal;
	}

	public Integer getTelefono() {
		return Telefono;
	}

	public void setTelefono(Integer telefono) {
		Telefono = telefono;
	}

	public Integer getMovil() {
		return Movil;
	}

	public void setMovil(Integer movil) {
		Movil = movil;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

	public String getProvincia() {
		return Provincia;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}

	public Double getCP() {
		return CP;
	}

	public void setCP(Double cP) {
		CP = cP;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public List<Expediente> getExpedientes() {
		return expedientes;
	}

	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
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
		Alumno other = (Alumno) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String res = "Alumno [" + (ID != null ? "ID=" + ID : "") + (DNI != null ? ", DNI=" + DNI : "")
				+ (Nombre != null ? ", Nombre=" + Nombre: "")
				+ (Apellido1 != null ? ", Apellido1=" + Apellido1: "")
				+ (Apellido2 != null ? ", Apellido2=" + Apellido2: "")
				+ (Email_institucional != null ? ", Email_institucional=" + Email_institucional : "")
				+ (Email_personal != null ? ", Email_personal=" + Email_personal : "")
				+ (Telefono != null ? ", Telefono=" + Telefono : "")
				+ (Movil != null ? ", Movil=" + Movil : "")
				+ (Direccion != null ? ", Direccion=" + Direccion : "")
				+ (Localidad != null ? ", Localidad=" + Localidad : "")
				+ (Provincia != null ? ", Provincia=" + Provincia : "") + (CP != null ? "CP=" + CP : "")
				+ (Usuario != null ? ", Usuario=" + Usuario : "");
		StringJoiner sj = new StringJoiner(", ", "(",")");
		if(expedientes != null) {
			res += ", Expedientes=";
			for(Expediente e : expedientes) {
				sj.add(e.getNum_expediente() != null ? e.getNum_expediente().toString() : "");
			}
			res += sj.toString();
		}
		
		return res + "]";
	}

}

