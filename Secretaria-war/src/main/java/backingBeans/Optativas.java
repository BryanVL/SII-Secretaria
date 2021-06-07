package backingBeans;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import excepcionesEJB.OptativaException;
import interfacesEJB.InterfazOptativa;
import io.undertow.servlet.spec.PartImpl;
import jpa.Optativa;

@Named(value = "optativa")
@RequestScoped
public class Optativas{
	
	private static final Logger LOGGER = Logger.getLogger(Optativas.class.getCanonicalName());
	
	@EJB
	private InterfazOptativa a;
	
	private Optativa optativa;
	private PartImpl archivo;
	private Integer referencia;
	private List<Optativa> optativas;
	private boolean buscar;
	
	public Optativas() {
		optativa = new Optativa();
	}
	
	public void setOptativa(Optativa optativa) {
		this.optativa = optativa;
	}
	
	public Optativa getOptativa() {
		return optativa;
	}
	
	public void setArchivo(PartImpl archivo) {
		this.archivo = archivo;
	}
	
	public PartImpl getArchivo() {
		return archivo;
	}
	
	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}
	
	public Integer getReferencia() {
		return referencia;
	}
	
	public List<Optativa> getOptativas(){
		return optativas;
	}
	
	public void setOptativas(List<Optativa> optativas) {
		this.optativas = optativas;
	}
	
	public boolean getBuscar() {
		return buscar;
	}
	
	public void setBuscar(boolean buscar) {
		this.buscar = buscar;
	}
	
	public String buscarTrue() {
		String respuesta = null;
		buscar = true;
		return respuesta;
	}
	
	public Optativa buscarOptativa(String referencia) {
		Optativa optativa = null;
		try {
			if(referencia.equals("")) {
				FacesMessage fm = new FacesMessage("No se ha introducido ninguna referencia");
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}else {
				optativa = a.VisualizarOptativa(Integer.parseInt(referencia));
			}
		}catch(OptativaException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return optativa;
	}
	
	public List<Optativa> leerDatosAdmin() {
		List<Optativa> optativa = null;
		try {
			
			optativa = a.mostrarDatosAdmin();
		
		}catch(OptativaException e) {
			FacesMessage fm = new FacesMessage("No hay datos que mostrar");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return optativa;
	}
	
	public String borrarOptativas() {
		String respuesta = null;
		try {
			a.borrarOptativas();
		} catch(OptativaException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
}