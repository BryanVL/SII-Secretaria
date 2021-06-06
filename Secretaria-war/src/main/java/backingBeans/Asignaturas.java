package backingBeans;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import excepcionesEJB.AsignaturaException;
import interfacesEJB.InterfazAsignatura;
import jpa.Asignatura;

@Named(value = "asignatura")
@RequestScoped
public class Asignaturas{
	
	private static final Logger LOGGER = Logger.getLogger(Asignaturas.class.getCanonicalName());
	
	@EJB
	private InterfazAsignatura a;
	
	private Asignatura asignatura;
	private List<Asignatura> asignaturas;
	private boolean buscar;
	
	public Asignaturas() {
		asignatura = new Asignatura();
	}
	
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	
	public Asignatura getAsignatura() {
		return asignatura;
	}
	
	public List<Asignatura> getAsignaturas(){
		return asignaturas;
	}
	
	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
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
	
	public Asignatura buscarAsignatura(String referencia) {
		Asignatura asignatura = null;
		try {
			if(referencia.equals("")) {
				FacesMessage fm = new FacesMessage("No se ha introducido ninguna referencia");
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}else {
				asignatura = a.VisualizarAsignatura(Integer.parseInt(referencia));
			}
		}catch(AsignaturaException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return asignatura;
	}
	
	public List<Asignatura> leerDatosAdmin() {
		List<Asignatura> asignatura = null;
		try {
			
			asignatura = a.mostrarDatosAdmin();
		
		}catch(AsignaturaException e) {
			FacesMessage fm = new FacesMessage("No hay datos que mostrar");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return asignatura;
	}
	
	public String borrarAsignaturas() {
		String respuesta = null;
		try {
			a.borrarAsignaturas();
		} catch(AsignaturaException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
}