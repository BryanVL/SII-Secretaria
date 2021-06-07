package backingBeans;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import excepcionesEJB.UsuarioException;
import interfacesEJB.InterfazUsuario;
import jpa.Usuario;

@Named(value = "user")
@RequestScoped
public class User{
	
	private static final Logger LOGGER = Logger.getLogger(User.class.getCanonicalName());
	
	@EJB
	private InterfazUsuario u;
	
	@Inject
	private InfoSesion sesion;
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	private boolean buscar;
	
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
	
	public boolean getBuscar() {
		return buscar;
	}
	
	public void setBuscar(boolean buscar) {
		this.buscar = buscar;
	}
	
	public String iniciarSesion() {
		String respuesta = null;
		
		try {
			
			Usuario usu = u.validarAcceso(usuario.getUsuario(), usuario.getPassword());
			sesion.setUsuario(usu);
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

	public String buscarTrue(){
		String respuesta = null;
		buscar = true;
		return respuesta;
	}
	
	public Usuario buscarUsuario(String usuario) {
		Usuario user = null;
		try {
			LOGGER.info("Buscando Usuario: " + usuario);
			user = u.mostrarDatos(usuario);
		}catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return user;
	}
	
	public List<Usuario> leerDatosAdmin() {
		List<Usuario> usuario = null;
		try {
			
			usuario = u.mostrarDatosAdmin();
		
		}catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage("No hay datos que mostrar");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return usuario;
	}
	
	public String borrarUsuarios() {
		String respuesta = null;
		try {
			u.borrarUsuarios();
		} catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
}