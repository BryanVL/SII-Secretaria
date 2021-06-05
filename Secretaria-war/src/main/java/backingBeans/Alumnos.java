package backingBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.UsuarioException;
import interfacesEJB.InterfazAlumno;
import interfacesEJB.InterfazUsuario;
import jpa.Alumno;
import jpa.Usuario;

@Named(value = "alumno")
@RequestScoped
public class Alumnos{
	
	private static final Logger LOGGER = Logger.getLogger(Alumnos.class.getCanonicalName());
	
	@EJB
	private InterfazAlumno a;
	
	private Alumno alumno;
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
	
	public Alumno buscarAlumno(String dni) {
		Alumno alumno = null;
		try {
			buscar = true;
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
}