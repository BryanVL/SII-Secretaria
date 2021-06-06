package backingBeans;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import ejb.TitulacionImpl;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazTitulacion;
import jpa.Titulacion;

@Named(value = "titulacion")
@RequestScoped
public class Titulaciones{
	
	private static final Logger LOGGER = Logger.getLogger(Titulaciones.class.getCanonicalName());
	
	@EJB
	private InterfazTitulacion a;
	
	private Titulacion titulacion;
	private String archivo;
	private Integer codigo;
	private List<Titulacion> titulaciones;
	private boolean buscar;
	
	public Titulaciones() {
		titulacion = new Titulacion();
	}
	
	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}
	
	public Titulacion getTitulacion() {
		return titulacion;
	}
	
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	public String getArchivo() {
		return archivo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public List<Titulacion> getTitulaciones(){
		return titulaciones;
	}
	
	public void setTitulaciones(List<Titulacion> titulaciones) {
		this.titulaciones = titulaciones;
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
	
	public Titulacion buscarTitulacion(String referencia) {
		Titulacion titulacion = null;
		try {
			if(referencia.equals("")) {
				FacesMessage fm = new FacesMessage("No se ha introducido ninguna referencia");
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}else {
				titulacion = a.VisualizarTitulacion(Integer.parseInt(referencia));
			}
		}catch(TitulacionException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return titulacion;
	}
	
	public List<Titulacion> leerDatosAdmin() {
		List<Titulacion> titulacion = null;
		try {
			
			titulacion = a.mostrarDatosAdmin();
		
		}catch(TitulacionException e) {
			FacesMessage fm = new FacesMessage("No hay datos que mostrar");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return titulacion;
	}
	
	public String borrarTitulaciones() {
		String respuesta = null;
		try {
			a.borrarTitulaciones();
		} catch(TitulacionException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
	
	public String importarTitulaciones(String dir){
		String respuesta = null;
		try {
			LOGGER.info("Importame esto crack   " + dir);
			a.Importar(dir);
			respuesta = "verTitulaciones.xhtml";
		} catch(ImportarException e){
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
}