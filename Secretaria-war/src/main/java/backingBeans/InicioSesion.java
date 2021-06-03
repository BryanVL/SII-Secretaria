package backingBeans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.UsuarioException;
import interfacesEJB.InterfazUsuario;
import jpa.Usuario;

@Named(value = "inicioSesion")
@RequestScoped
public class InicioSesion{
	
	private InterfazUsuario usuario;
	
	private String nombre;
	private String pass;

	private Usuario u;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String iniciarSesion() {
		String respuesta = null;
		
		try {
			
			usuario.validarAcceso(nombre, pass);
			respuesta = "MisDatos.xhtml";
			
		}catch(UsuarioException e) {
			e.printStackTrace();
		}
		
		
		return respuesta;
	}
	
	public String mostrarDatos() {
		String respuesta = null;
		
		
		
		
		return respuesta;
	}
	
}