package backingBeans;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import excepcionesEJB.ExpedienteException;
import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazExpediente;
import io.undertow.servlet.spec.PartImpl;
import jpa.Expediente;

@Named(value = "expediente")
@RequestScoped
public class Expedientes{
	
	private static final Logger LOGGER = Logger.getLogger(Expedientes.class.getCanonicalName());
	
	@EJB
	private InterfazExpediente e;
	
	private Expediente expediente;
	private PartImpl archivo;
	private List<Expediente> expedientes;
	private boolean buscar;
	
	public Expedientes() {
		expediente = new Expediente();
	}
	
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	
	public Expediente getExpediente() {
		return expediente;
	}
	
	public void setArchivo(PartImpl archivo) {
		this.archivo = archivo;
	}
	
	public PartImpl getArchivo() {
		return archivo;
	}
	
	public List<Expediente> getExpedientes(){
		return expedientes;
	}
	
	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
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
	
	public Expediente buscarExpediente(String referencia) {
		Expediente expediente = null;
		try {
			if(referencia.equals("")) {
				FacesMessage fm = new FacesMessage("No se ha introducido ninguna referencia");
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}else {
				expediente = e.VisualizarExpediente(Integer.parseInt(referencia));
			}
		}catch(ExpedienteException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return expediente;
	}
	
	public List<Expediente> leerDatosAdmin() {
		List<Expediente> expediente = null;
		try {
			
			expediente = e.mostrarDatosAdmin();
		
		}catch(ExpedienteException e) {
			FacesMessage fm = new FacesMessage("No hay datos que mostrar");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return expediente;
	}
	
	public String borrarExpedientes() {
		String respuesta = null;
		try {
			e.borrarExpedientes();
		} catch(ExpedienteException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
	
	public String importarExpedientes(){
		String respuesta = null;
		try {
			if(archivo.getSubmittedFileName().endsWith(".xlsx")) {
				String sfile = "/tmp/Expedientes.xlsx";
				File temporal = new File(sfile);
				archivo.write(sfile);
				e.Importar(sfile);
				temporal.delete();
				respuesta = "ImportarAdmin.xhtml";
			} else if(archivo.getSubmittedFileName().endsWith(".csv")) {
				String sfile = "/tmp/Expedientes.csv";
				File temporal = new File(sfile);
				archivo.write(sfile);
				e.Importar(sfile);
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
		return respuesta;
	}
}