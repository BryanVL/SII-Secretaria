package backingBeans;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.bouncycastle.jcajce.provider.asymmetric.GM;

import excepcionesEJB.GrupoException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazGrupo;
import interfacesEJB.InterfazTitulacion;
import jpa.Grupo;
import jpa.Titulacion;

@Named(value = "grupo")
@RequestScoped
public class Grupos{
	
	private static final Logger LOGGER = Logger.getLogger(Grupos.class.getCanonicalName());
	
	@EJB
	private InterfazGrupo a;

	@EJB
	private InterfazTitulacion t;
	
	@Inject
	private InfoSesion sesion;
	
	private Grupo gM;
	
	
	private Grupo grupo;
	private List<Grupo> grupos;
	private boolean noseusa;
	private boolean buscar;
	private Integer codigotit;
	private Integer curso;
	private String letra;
	
	public Grupos() {
		grupo = new Grupo();
		gM = new Grupo();
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setGM(Grupo grupo) {
		this.gM = grupo;
	}
	
	public Grupo getGM() {
		return gM;
	}
	
	public List<Grupo> getGrupos(){
		return grupos;
	}
	
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	public boolean getNoseusa(){
		return noseusa;
	}
	
	public void setNoseusa(boolean noseusa) {
		this.noseusa = noseusa;
	}
	
	public boolean getBuscar() {
		return buscar;
	}
	
	public void setBuscar(boolean buscar) {
		this.buscar = buscar;
	}
	
	public Integer getCodigotit() {
		return codigotit;
	}
	
	public void setCodigotit(Integer codigotit) {
		this.codigotit = codigotit;
	}
	
	public Integer getCurso() {
		return curso;
	}
	
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	
	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}
	
	
	
	public String buscarTrue() {
		String respuesta = null;
		if(sesion.comprobarSesion()) {
			if(codigotit == null) {
				noseusa = true;
			}
			buscar = true;
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
	
	public String crearGrupos() {
		String respuesta = null;
		
		if(sesion.comprobarSesion()) {
			try {
				Integer tit = 1041;
					a.crear(tit, 1, "A");
					a.crear(tit, 2, "A");
					a.crear(tit, 3, "A");
					a.crear(tit, 4, "A");
					a.crear(tit, 1, "B");
					a.crear(tit, 2, "B");
					a.crear(tit, 3, "B");
					a.crear(tit, 1, "C");
					a.crear(tit, 2, "C");
					a.crear(tit, 1, "D");
					respuesta = "ImportarAdmin.xhtml";
			} catch (GrupoException e) {
				FacesMessage fm = new FacesMessage(e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return respuesta;
	}
	
	public List<Grupo> buscarGrupo(Integer curso, String letra) {
		List<Grupo> grupos = null;
		if(sesion.comprobarSesion()) {
			try {
				grupos = a.buscarPorCursoLetra(curso, letra);
				if(grupos == null) {
					FacesMessage fm = new FacesMessage("No se ha encontrado ningun grupo");
		            FacesContext.getCurrentInstance().addMessage(null, fm);
				}
			}catch(GrupoException e) {
				FacesMessage fm = new FacesMessage(e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return grupos;
	}
	
	public Grupo buscarGrupo(Integer codigotit, Integer curso, String letra) {
		Grupo grupo = null;
		if(sesion.comprobarSesion()) {
			try {
				grupo = a.buscarPorCursoLetra(codigotit, curso, letra);
				if(grupo == null) {
					FacesMessage fm = new FacesMessage("No se ha encontrado el grupo");
		            FacesContext.getCurrentInstance().addMessage(null, fm);
				}
			}catch(GrupoException e) {
				FacesMessage fm = new FacesMessage(e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return grupo;
	}
	
	public List<Grupo> leerDatosAdmin() {
		List<Grupo> grupo = null;
		if(sesion.comprobarSesion()) {
			try {
				
				grupo = a.mostrarDatosAdmin();
			
			}catch(GrupoException e) {
				FacesMessage fm = new FacesMessage("No hay datos que mostrar");
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
	        FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return grupo;
	}
	
	public String borrarGrupo(Integer codigotit, Integer curso, String letra) {
		String respuesta = null;
		if(sesion.comprobarSesion()) {
			try {
				Grupo grupo = a.buscarPorCursoLetra(codigotit, curso, letra);
				a.borrar(grupo);
			} catch(GrupoException e) {
				FacesMessage fm = new FacesMessage(e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
	        FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return respuesta;
	}
	
	public String modificarGrupo(Integer codigotit, Integer curso, String letra) {
		String respuesta = null;
		respuesta = "mGrupo.xhtml";
		
		if(sesion.comprobarSesion()) {
			
			try {
				grupo = a.buscarPorCursoLetra(codigotit, curso, letra);
				
				
				if(grupo == null) {
					FacesMessage fm = new FacesMessage("No se ha encontrado el grupo");
		            FacesContext.getCurrentInstance().addMessage(null, fm);
				}
				gM = grupo;
				sesion.setIdgm(gM.getID());
				respuesta = "mGrupo.xhtml";
				
			}catch(GrupoException e) {
				FacesMessage fm = new FacesMessage(e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
            FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return respuesta;
	}
	
	public Grupo getGrupoModf() {
		return gM;
		
	}
	
	public String modificar() {
		String respuesta = null;
		respuesta = "verGrupos.xhtml";
		Grupo prueba;
		
		if(sesion.comprobarSesion()) {
			
			try {
				
				prueba = a.leer2( sesion.getIdgm() );
				if(gM.getCurso() != null || !gM.getCurso().equals("")) {
					prueba.setCurso(gM.getCurso());
				}
				if(gM.getLetra() != null || !gM.getLetra().equals("")) {
					prueba.setLetra(gM.getLetra());
				}
				if(gM.getTurno_Mañana_Tarde() != null || !gM.getTurno_Mañana_Tarde().equals("")) {
					prueba.setTurno_Mañana_Tarde(gM.getTurno_Mañana_Tarde());
				}
				if(gM.getIngles() != null || !gM.getIngles().equals("")) {
					prueba.setIngles(gM.getIngles());
				}
				
				a.actualizar(prueba);
				
			} catch(GrupoException e) {
				FacesMessage fm = new FacesMessage(e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("No se ha iniciado sesion");
	        FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
		return respuesta;
	}

}