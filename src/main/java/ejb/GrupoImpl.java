package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import excepcionesEJB.GrupoException;
import interfacesEJB.InterfazGrupo;
import jpa.Grupo;

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
		// TODO Auto-generated method stub
		if(g.getPlazas()==0) {
			throw new GrupoException();
		}
	}
	
	public Grupo buscarPorCursoLetra(Integer titulacion, Integer curso, String letra) throws GrupoException {
		
		TypedQuery query = em.createQuery("Select g from Grupo g", Grupo.class);	              
        List<Grupo> grupos = query.getResultList();
		
		 boolean encontrado = false;     
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
	        }
	        
		return g;
	}
	
	
	public List<Grupo> buscarPorCursoLetra(Integer curso, String letra) {
		
		TypedQuery query = em.createQuery("Select g from Grupo g", Grupo.class);	              
        List<Grupo> grupos = query.getResultList();
		
    
        int i = 0;
        List<Grupo> g = new ArrayList<Grupo>();
        Grupo gr = new Grupo();
        while(i<grupos.size()) {
     	   gr = grupos.get(i); 
     	   if(gr.getCurso().equals(curso) && gr.getLetra().equalsIgnoreCase(letra)) {
     		   g.add(gr);
     	   }
     	   i++;
        }
	        
		return g;
	}
    
}
