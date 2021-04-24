package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.AsignaturaException;
import excepcionesEJB.GrupoException;
import excepcionesEJB.MatriculaException;
import interfacesEJB.InterfazGrupo;
import jpa.Alumno;
import jpa.Asignatura;
import jpa.Asignaturas_Matricula;
import jpa.Asignaturas_Matricula_PK;
import jpa.Grupo;
import jpa.Matricula;

@Stateless
@LocalBean
public class GrupoImpl implements InterfazGrupo{

	//private static final Logger LOG = Logger.getLogger(LotesEJB.class.getCanonicalName());
    @PersistenceContext(unitName = "Secretaria")
    private EntityManager em;

	@Override
	public void Crear(Grupo g) throws GrupoException {
		// TODO Auto-generated method stub
		Grupo grupoExistente = em.find(Grupo.class, g.getID());
		if (grupoExistente != null) {
			throw new GrupoException();
		}
		em.persist(g);
	}

	@Override
	public Grupo Leer(Grupo g) throws GrupoException {
		// TODO Auto-generated method stub
		Grupo grupoExistente = em.find(Grupo.class, g.getID());
		if (grupoExistente == null) {
			throw new GrupoException();
		}
		return grupoExistente;
	}

	@Override
	public void Borrar(Grupo g) throws GrupoException {
		// TODO Auto-generated method stub
		Grupo grupoExistente = em.find(Grupo.class, g.getID());
		if (grupoExistente == null) {
			throw new GrupoException();
		}
		em.remove(em.merge(g));
	}

	@Override
	public void Actualizar(Grupo g) throws GrupoException {
		// TODO Auto-generated method stub
		Grupo grupoExistente = em.find(Grupo.class, g.getID());
		if (grupoExistente == null) {
			throw new GrupoException();
		}
		em.merge(g);
	}


	@Override
	public void ComprobarPlazas(Grupo g) throws GrupoException {
		if(g.getPlazasDisponibles()==0) {
			throw new GrupoException();
		}
	}
	
	public Grupo buscarPorCursoLetra(Integer titulacion, Integer curso, String letra) throws GrupoException {
		
		TypedQuery query = em.createQuery("Select g from Grupo g where g.getCurso()=curso and g.getLetra()=letra", Grupo.class);	              
        List<Grupo> grupos = query.getResultList();
		
        
        boolean encontrado = false;     
        int i = 0;
        Grupo g = new Grupo();
        while(i<grupos.size() && !encontrado) {
     	   g = grupos.get(i); 
     	   if(g.getTitulacion().getCodigo().equals(titulacion)) {
     		   encontrado=true;
     	   }
     	   i++;
        }
        
        if(!encontrado) {
        	throw new GrupoException();
        }
        
		/* boolean encontrado = false;     
	        int i = 0;
	        Grupo g = new Grupo();
	        while(i<grupos.size() && !encontrado) {
	     	   g = grupos.get(i); 
	     	   if(g.getCurso().equals(curso) && g.getLetra().equalsIgnoreCase(letra) && g.getTitulacion().getCodigo().equals(titulacion)) {
	     		   encontrado=true;
	     	   }
	     	   i++;
	        }
	        
	        if(!encontrado) {
	        	throw new GrupoException();
	        }*/
	        
		return g;
	}
	
	
	public List<Grupo> buscarPorCursoLetra(Integer curso, String letra) {
		
		TypedQuery query = em.createQuery("Select g from Grupo g where g.getCurso()=curso and g.getLetra()=letra", Grupo.class);	              
        List<Grupo> grupos = query.getResultList();
		
    
        /*int i = 0;
        List<Grupo> g = new ArrayList<Grupo>();
        Grupo gr = new Grupo();
        while(i<grupos.size()) {
     	   gr = grupos.get(i); 
     	   if(gr.getCurso().equals(curso) && gr.getLetra().equalsIgnoreCase(letra)) {
     		   g.add(gr);
     	   }
     	   i++;
        }*/
	        
		return grupos;
	}
	
	
	public void asignarGrupo(Matricula matricula, Grupo grupo, Asignatura asignatura) throws MatriculaException, GrupoException, AsignaturaException {
		Matricula m = em.find(Matricula.class, matricula.getId());
		if(m==null) {
			throw new MatriculaException("La matricula no ha sido encontrada");
		}
		
		Grupo g = em.find(Grupo.class, grupo.getID());
		if(g==null) {
			throw new GrupoException("El grupo no ha sido encontrado");
		}
			
		Asignatura asig = em.find(Asignatura.class, asignatura.getReferencia());
		if(asig==null) {
			throw new AsignaturaException("La asignatura no ha sido encontrada");
		}
		
		
		if(g.getPlazasDisponibles()>0) {
			//g.setPlazasDisponibles(g.getPlazasDisponibles()-1);//Disminuir en uno plazas
			Asignaturas_Matricula am = new Asignaturas_Matricula();
			am.setMatricula(m);
			am.setGrupo(g);
			am.setAsignatura(asig);
			am.setAsignacionAutomatica(true);
			Asignaturas_Matricula_PK id = new Asignaturas_Matricula_PK();
			id.setIdAsig(asig.getReferencia());
			id.setIdM(m.getId());
			am.setId(id);
			em.persist(am);	
		}else {
			throw new GrupoException("No quedan plazas disponibles en este grupo");
		}
		
	}
	
    
}
