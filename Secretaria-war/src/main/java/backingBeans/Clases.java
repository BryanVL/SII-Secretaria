package backingBeans;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import excepcionesEJB.ClaseException;
import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazHorarios;
import io.undertow.servlet.spec.PartImpl;
import jpa.Clase;

@Named(value = "clase")
@RequestScoped
public class Clases{
	
	private static final Logger LOGGER = Logger.getLogger(Clases.class.getCanonicalName());
	
	@EJB
	private InterfazHorarios c;

	@Inject
	private InfoSesion sesion;
	
	
	private Clase clase;
	private PartImpl archivo;
	private List<Clase> clases;
	private boolean buscar;
	
	public Clases() {
		clase= new Clase();
	}
	
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	
	public Clase getClase() {
		return clase;
	}
	
	public void setArchivo(PartImpl archivo) {
		this.archivo = archivo;
	}
	
	public PartImpl getArchivo() {
		return archivo;
	}
	
	public List<Clase> getClases(){
		return clases;
	}
	
	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}
	
	public boolean getBuscar() {
		return buscar;
	}
	
	public void setBuscar(boolean buscar) {
		this.buscar = buscar;
	}
	
	public String buscarTrue() {
		String respuesta = null;
		if(sesion.comprobarSesion()) {
			buscar = true;
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
	
	public List<Clase> leerDatosAdmin() {
		List<Clase> clase = null;
		if(sesion.comprobarSesion()) {
			try {
				
				clase = c.mostrarDatosAdmin();
			
			}catch(ClaseException e) {
				FacesMessage fm = new FacesMessage("No hay datos que mostrar");
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return clase;
	}
	
	public String borrarClases() {
		String respuesta = null;
		if(sesion.comprobarSesion()) {
			try {
				c.borrarClases();
			} catch(ClaseException e) {
				FacesMessage fm = new FacesMessage(e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
	
	public String importarClases(){
		String respuesta = null;
		if(sesion.comprobarSesion()) {
			try {
				if(archivo.getSubmittedFileName().endsWith(".xlsx")) {
					String sfile = "/tmp/Clases.xlsx";
					File temporal = new File(sfile);
					archivo.write(sfile);
					c.Importar(sfile);
					temporal.delete();
					respuesta = "ImportarAdmin.xhtml";
				} else if(archivo.getSubmittedFileName().endsWith(".csv")) {
					String sfile = "/tmp/Clases.csv";
					File temporal = new File(sfile);
					archivo.write(sfile);
					c.Importar(sfile);
					temporal.delete();
					respuesta = "ImportarAdmin.xhtml";
				} else {
					FacesMessage fm = new FacesMessage("El archivo no es correcto");
		            FacesContext.getCurrentInstance().addMessage(null, fm);
				}
			} catch(ImportarException e){
				FacesMessage fm = new FacesMessage(e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			} catch (IOException e) {
				FacesMessage fm = new FacesMessage("Error en el archivo");
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
}