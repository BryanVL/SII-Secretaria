package backingBeans;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import jpa.Usuario;

@Named(value = "infoSesion")
@SessionScoped
public class InfoSesion implements Serializable {
	
	private static final Logger LOGGER = Logger.getLogger(User.class.getCanonicalName());
	private Long idgm;
	
	private Usuario usuario;
	
	public InfoSesion() {
    }

    public synchronized void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public synchronized Usuario getUsuario() {
        return usuario;
    }
    
    public synchronized void setIdgm(Long id) {
        this.idgm = id;
    }

    public synchronized Long getIdgm() {
        return idgm;
    }
    
    public synchronized String cerrarSesion() {
    	if(usuario != null) {
    		usuario = null;
    	}
    	return "index.xhtml";
    }
    
    public synchronized boolean comprobarSesion() {
    	boolean respuesta = false;
    	if(usuario != null) {
    		respuesta = true;
    	}
    	return respuesta;
    }
}