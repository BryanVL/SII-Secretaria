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

import excepcionesEJB.ImportarException;
import excepcionesEJB.MatriculaException;
import interfacesEJB.InterfazMatricula;
import io.undertow.servlet.spec.PartImpl;
import jpa.Matricula;

@Named(value = "matricula")
@RequestScoped
public class Matriculas{
	
	private static final Logger LOGGER = Logger.getLogger(Matriculas.class.getCanonicalName());
	
	@EJB
	private InterfazMatricula m;

	@Inject
	private InfoSesion sesion;
	
	
	private Matricula matricula;
	private PartImpl archivo;
	private List<Matricula> matriculas;
	private String curso_academico;
	private Integer idexp;
	private boolean buscar;
	
	public Matriculas() {
		matricula = new Matricula();
	}
	
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
	public Matricula getMatricula() {
		return matricula;
	}
	
	public void setArchivo(PartImpl archivo) {
		this.archivo = archivo;
	}
	
	public PartImpl getArchivo() {
		return archivo;
	}
	
	public List<Matricula> getMatriculas(){
		return matriculas;
	}
	
	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	public String getCurso_academico() {
		return curso_academico;
	}
	
	public void setCurso_academico(String curso) {
		this.curso_academico = curso;
	}
	
	public Integer getIdExp() {
		return idexp;
	}
	
	public void setIdExp(Integer idexp) {
		this.idexp = idexp;
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
	
	public Matricula buscarMatricula(String curso, Integer idexp) {
		Matricula matricula = null;
		if(sesion.comprobarSesion()) {
			try {
				LOGGER.info("Buscando Matricula: " + curso + ", " + idexp);
				matricula = m.VisualizarMatricula(curso,idexp);
			}catch(MatriculaException e) {
				FacesMessage fm = new FacesMessage(e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return matricula;
	}
	
	public List<Matricula> leerDatosAdmin() {
		List<Matricula> matricula = null;
		if(sesion.comprobarSesion()) {
			try {
				
				matricula = m.mostrarDatosAdmin();
			
			}catch(MatriculaException e) {
				FacesMessage fm = new FacesMessage("No hay matriculas que mostrar");
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return matricula;
	}
	
	public String borrarMatriculas() {
		String respuesta = null;
		if(sesion.comprobarSesion()) {
			try {
				m.borrarMatriculas();
			} catch(MatriculaException e) {
				FacesMessage fm = new FacesMessage(e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}

	public String importarMatriculas(){
		String respuesta = null;
		if(sesion.comprobarSesion()) {
			try {
				if(archivo.getSubmittedFileName().endsWith(".xlsx")) {
					String sfile = "/tmp/Matriculas.xlsx";
					File temporal = new File(sfile);
					archivo.write(sfile);
					m.Importar(sfile);
					temporal.delete();
					respuesta = "ImportarAdmin.xhtml";
				} else if(archivo.getSubmittedFileName().endsWith(".csv")) {
					String sfile = "/tmp/Matriculas.csv";
					File temporal = new File(sfile);
					archivo.write(sfile);
					m.Importar(sfile);
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