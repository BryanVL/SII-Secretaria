package backingBeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import jpa.Usuario;

@Named(value = "infoSesion")
@SessionScoped
public class InfoSesion implements Serializable {
	
	
	private Usuario usuario;
	
	public InfoSesion() {
    }

    public synchronized void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public synchronized Usuario getUsuario() {
        return usuario;
    }
    
}