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

import excepcionesEJB.AsignaturaException;
import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazAsignatura;
import io.undertow.servlet.spec.PartImpl;
import jpa.Asignatura;

@Named(value = "asignatura")
@RequestScoped
public class Asignaturas{
	
	private static final Logger LOGGER = Logger.getLogger(Asignaturas.class.getCanonicalName());
	
	@EJB
	private InterfazAsignatura a;
	
	private Asignatura asignatura;
	private PartImpl archivo;
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
	
	public void setArchivo(PartImpl archivo) {
		this.archivo = archivo;
	}
	
	public PartImpl getArchivo() {
		return archivo;
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
	
	public String importarAsignaturas(){
		String respuesta = null;
		try {
			if(archivo.getSubmittedFileName().endsWith(".xlsx")) {
				String sfile = "/tmp/Asignaturas.xlsx";
				File temporal = new File(sfile);
				archivo.write(sfile);
				a.Importar(sfile);
				temporal.delete();
				respuesta = "ImportarAdmin.xhtml";
			} else if(archivo.getSubmittedFileName().endsWith(".csv")) {
				String sfile = "/tmp/Asignaturas.csv";
				File temporal = new File(sfile);
				archivo.write(sfile);
				a.Importar(sfile);
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