package backingBeans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.UsuarioException;
import interfacesEJB.InterfazUsuario;
import jpa.Usuario;

@Named(value = "registro")
@RequestScoped
public class Registro {
	
	@EJB
	private InterfazUsuario usuario;
	
	private String nombre;
	private String pass;
	private String pass2;
	private String rol;
	private String dni;
	
	private Usuario u;
	
	public Registro() {
		u = new Usuario();
	}
	
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
	
	public String getPass2() {
		return pass2;
	}
	
	public void setPass2(String nombre) {
		this.pass2 = pass2;
	}
	
	public String getRol() {
		return rol;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setdni(String dni) {
		this.dni = dni;
	}
	
	public String registrarUsuario() {
		String respuesta = null;
		try {
			
			if(pass == pass2) {
				usuario.crearUsuario(dni, nombre, pass, rol);
				respuesta = "index.xhtml";
			} else {
				throw new UsuarioException("Las contraseñas deben coincidir.");
			}
			
		} catch(UsuarioException e) {
			e.printStackTrace();;
		} catch(AlumnoException e) {
			e.printStackTrace();
		}
		
		
		return respuesta;
	}
	
}
