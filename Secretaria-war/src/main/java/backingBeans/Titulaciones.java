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
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazTitulacion;
import io.undertow.servlet.spec.PartImpl;
import jpa.Titulacion;

@Named(value = "titulacion")
@RequestScoped
public class Titulaciones{
	
	private static final Logger LOGGER = Logger.getLogger(Titulaciones.class.getCanonicalName());
	
	@EJB
	private InterfazTitulacion a;

	@Inject
	private InfoSesion sesion;
	
	
	private Titulacion titulacion;
	private PartImpl archivo;
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
	
	public void setArchivo(PartImpl archivo) {
		this.archivo = archivo;
	}
	
	public PartImpl getArchivo() {
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
		if(sesion.comprobarSesion()) {
			buscar = true;
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
	
	public Titulacion buscarTitulacion(String referencia) {
		Titulacion titulacion = null;
		if(sesion.comprobarSesion()) {
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
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return titulacion;
	}
	
	public List<Titulacion> leerDatosAdmin() {
		List<Titulacion> titulacion = null;
		if(sesion.comprobarSesion()) {
			try {
				
				titulacion = a.mostrarDatosAdmin();
			
			}catch(TitulacionException e) {
				FacesMessage fm = new FacesMessage("No hay datos que mostrar");
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return titulacion;
	}
	
	public String borrarTitulaciones() {
		String respuesta = null;
		if(sesion.comprobarSesion()) {
			try {
				a.borrarTitulaciones();
			} catch(TitulacionException e) {
				FacesMessage fm = new FacesMessage(e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
	
	public String importarTitulaciones(){
		String respuesta = null;
		if(sesion.comprobarSesion()) {
			try {
				if(archivo.getSubmittedFileName().endsWith(".xlsx")) {
					String sfile = "/tmp/Titulacion.xlsx";
					File temporal = new File(sfile);
					archivo.write(sfile);
					a.Importar(sfile);
					temporal.delete();
					respuesta = "ImportarAdmin.xhtml";
				} else if(archivo.getSubmittedFileName().endsWith(".csv")) {
					String sfile = "/tmp/Titulacion.csv";
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
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
}