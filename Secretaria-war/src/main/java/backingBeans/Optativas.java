package backingBeans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import excepcionesEJB.ImportarException;
import excepcionesEJB.OptativaException;
import interfacesEJB.InterfazOptativa;
import io.undertow.servlet.spec.PartImpl;
import jpa.Asignatura;
import jpa.Optativa;

@Named(value = "optativa")
@RequestScoped
public class Optativas{
	
	private static final Logger LOGGER = Logger.getLogger(Optativas.class.getCanonicalName());
	
	@EJB
	private InterfazOptativa a;

	@Inject
	private InfoSesion sesion;
	
	
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
		if(sesion.comprobarSesion()) {
			buscar = true;
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
	
	public Optativa buscarOptativa(String referencia) {
		Optativa optativa = null;
		if(sesion.comprobarSesion()) {
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
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return optativa;
	}
	
	public List<Optativa> leerDatosAdmin() {
		List<Optativa> optativas = new ArrayList<Optativa>();
		if(sesion.comprobarSesion()) {
			try {
				List<Asignatura> asignaturas = a.mostrarDatosAdmin();
				for(Asignatura a: asignaturas) {
					if(a.getOptativa() != null) {
						optativas.add(a.getOptativa());
					}
				}
			
			}catch(OptativaException e) {
				FacesMessage fm = new FacesMessage("No hay datos que mostrar");
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return optativas;
	}
	
	public String borrarOptativas() {
		String respuesta = null;
		if(sesion.comprobarSesion()) {
			try {
				a.borrarOptativas();
			} catch(OptativaException e) {
				FacesMessage fm = new FacesMessage(e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
	
	public String importarOptativas(){
		String respuesta = null;
		if(sesion.comprobarSesion()) {
			try {
				if(archivo.getSubmittedFileName().endsWith(".xlsx")) {
					String sfile = "/tmp/Optativas.xlsx";
					File temporal = new File(sfile);
					temporal.delete();
					archivo.write(sfile);
					a.Importar(sfile);
					temporal.delete();
					respuesta = "ImportarAdmin.xhtml";
				} else if(archivo.getSubmittedFileName().endsWith(".csv")) {
					String sfile = "/tmp/Optativas.csv";
					File temporal = new File(sfile);
					temporal.delete();
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
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
}