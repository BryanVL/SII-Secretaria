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

import excepcionesEJB.AlumnoException;
import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazAlumno;
import io.undertow.servlet.spec.PartImpl;
import jpa.Alumno;

@Named(value = "alumno")
@RequestScoped
public class Alumnos{
	
	private static final Logger LOGGER = Logger.getLogger(Alumnos.class.getCanonicalName());
	
	@EJB
	private InterfazAlumno a;
	
	private Alumno alumno;
	private PartImpl archivo;
	private List<Alumno> alumnos;
	private boolean buscar;
	
	public Alumnos() {
		alumno = new Alumno();
	}
	
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	public Alumno getAlumno() {
		return alumno;
	}
	
	public void setArchivo(PartImpl archivo) {
		this.archivo = archivo;
	}
	
	public PartImpl getArchivo() {
		return archivo;
	}
	
	public List<Alumno> getAlumnos(){
		return alumnos;
	}
	
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
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
	
	public Alumno buscarAlumno(String dni) {
		Alumno alumno = null;
		try {
			LOGGER.info("Buscando Alumno: " + dni);
			alumno = a.VisualizarAlumno(dni);
		}catch(AlumnoException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return alumno;
	}
	
	public List<Alumno> leerDatosAdmin() {
		List<Alumno> alumno = null;
		try {
			
			alumno = a.mostrarDatosAdmin();
		
		}catch(AlumnoException e) {
			FacesMessage fm = new FacesMessage("No hay datos que mostrar");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return alumno;
	}
	
	public String borrarAlumnos() {
		String respuesta = null;
		try {
			a.borrarAlumnos();
		} catch(AlumnoException e) {
			FacesMessage fm = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
	
	public String importarAlumnos(){
		String respuesta = null;
		try {
			if(archivo.getSubmittedFileName().endsWith(".xlsx")) {
				String sfile = "/tmp/Alumnos.xlsx";
				File temporal = new File(sfile);
				archivo.write(sfile);
				a.Importar(sfile);
				temporal.delete();
				respuesta = "ImportarAdmin.xhtml";
			} else if(archivo.getSubmittedFileName().endsWith(".csv")) {
				String sfile = "/tmp/Alumnos.csv";
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