package backingBeans;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.NoResultException;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.UsuarioException;
import interfacesEJB.InterfazUsuario;
import jpa.Usuario;

@Named(value = "registro")
@RequestScoped
public class Registro {
	
	private static final Logger LOGGER = Logger.getLogger(Registro.class.getCanonicalName());
	
	@EJB
	private InterfazUsuario u;
	
	private String pass2;
	private String dni;
	
	private Usuario usuario;
	
	public Registro() {
		usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getPass2() {
		return pass2;
	}
	
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setdni(String dni) {
		this.dni = dni;
	}
	
	//Metodo utilizado para crear el usuario admin con rol "Admin" y contraseña admin:
	
	public String crearSecretaria() {
		String respuesta = null;
		
		u.crearSecretaria(usuario.getUsuario(), usuario.getPassword());
		respuesta = "index.xhtml";
		return respuesta;
	}
	
	public String registrarUsuario() {
		String respuesta = null;
		try {
			
			if(usuario.getPassword().equals(pass2)) {
				LOGGER.info(usuario.toString() + ", " + dni);
				u.crearUsuario(dni, usuario.getUsuario(), usuario.getPassword(), "Alumno");
				respuesta = "MainPage.xhtml";
			} else {
				FacesMessage fm = new FacesMessage("Las contraseñas deben coincidir");
                FacesContext.getCurrentInstance().addMessage(null, fm);
            }
			
			LOGGER.info(respuesta);
		} catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		} catch(AlumnoException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		
		return respuesta;
	}
	
}
