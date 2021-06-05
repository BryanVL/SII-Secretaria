package backingBeans;

import java.util.ArrayList;
import java.util.List;
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
	private List<Usuario> usuarios;
	
	public User() {
		usuario = new Usuario();
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public String iniciarSesion() {
		String respuesta = null;
		
		try {
			
			Usuario usu = u.validarAcceso(usuario.getUsuario(), usuario.getPassword());
			if(usu.getRol().equals("Admin")) {
				respuesta = "MainPageAdmin.xhtml";
			} else {
				respuesta = "MainPage.xhtml";
			}

		}catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return respuesta;
	}
	
	public Usuario leerDatos(String nombre) {
		Usuario usuario = null;
		try {
			
			usuario = u.mostrarDatos(nombre);
		
		}catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return usuario;
	}
	
	public List<Usuario> leerDatosAdmin() {
		List<Usuario> usuario = null;
		try {
			
			usuario = u.mostrarDatosAdmin();
		
		}catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return usuario;
	}
	
	public String borrarUsuarios() {
		String respuesta = null;
		try {
			LOGGER.info("Entra al metodo");
			u.borrarUsuarios();
			LOGGER.info("Sale del metodo");
			respuesta = "index.xhtml";
		} catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
}