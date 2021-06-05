package backingBeans;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.UsuarioException;
import interfacesEJB.InterfazUsuario;
import jpa.Usuario;

@Named(value = "user")
@RequestScoped
public class User{
	
	private static final Logger LOGGER = Logger.getLogger(User.class.getCanonicalName());
	
	@EJB
	private InterfazUsuario u;
	
	private Usuario usuario;
	
	public User() {
		usuario = new Usuario();
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String iniciarSesion() {
		String respuesta = null;
		
		try {
			LOGGER.info(usuario.toString());
			u.validarAcceso(usuario.getUsuario(), usuario.getPassword());
			respuesta = "MainPage.xhtml";
			LOGGER.info(respuesta);

		}catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return respuesta;
	}
	
	public String leerDatos() {
		String respuesta = null;
		try {
			
			u.mostrarDatos(usuario.getUsuario());
		
		}catch(UsuarioException e) {
			e.printStackTrace();
		}
		return respuesta;
	}
	
}