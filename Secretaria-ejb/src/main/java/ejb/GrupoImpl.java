package ejb;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import excepcionesEJB.AsignaturaException;
import excepcionesEJB.GrupoException;
import excepcionesEJB.MatriculaException;
import interfacesEJB.InterfazGrupo;
import jpa.Asignatura;
import jpa.Asignaturas_Matricula;
import jpa.Asignaturas_Matricula_PK;
import jpa.Grupo;
import jpa.Matricula;
import jpa.Optativa;
import jpa.Titulacion;

@Stateless
@LocalBean
public class GrupoImpl implements InterfazGrupo{

	//private static final Logger LOG = Logger.getLogger(LotesEJB.class.getCanonicalName());
    @PersistenceContext(unitName = "Secretaria")
    private EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(Grupo.class.getCanonicalName());

    
	@Override
	public void crear(Integer titulacion, Integer curso, String letra) throws GrupoException {

		Grupo g = new Grupo();
		Titulacion tit = em.find(Titulacion.class, titulacion);
		if(tit != null) {
			g.setCurso(curso);
			g.setLetra(letra);
			g.setTurno_Mañana_Tarde("Mañana");
			g.setIngles("Si");
			g.setTitulacion(tit);
			Grupo grupo = buscarPorCursoLetra(titulacion, curso, letra);
			if(grupo == null) {
				em.persist(g);
			}
		}
	}

	@Override
	public Grupo leer(Grupo g) throws GrupoException {

		Grupo grupoExistente = em.find(Grupo.class, g.getID());
		if (grupoExistente == null) {
			throw new GrupoException("No existe ningun grupo asociado a ese ID");
		}
		return grupoExistente;
	}
	
	@Override
	public Grupo leer2(Long id) throws GrupoException {

		Grupo grupoExistente = em.find(Grupo.class, id);
		if (grupoExistente == null) {
			throw new GrupoException("No existe ningun grupo asociado a ese ID");
		}
		return grupoExistente;
	}

	@Override
	public void borrar(Grupo g) throws GrupoException {

		Grupo grupoExistente = em.find(Grupo.class, g.getID());
		if (grupoExistente == null) {
			throw new GrupoException("No existe ningun grupo asociado a ese ID");
		}
		em.remove(grupoExistente);
	}

	@Override
	public void actualizar(Grupo g) throws GrupoException {
		if(g.getID() != null) {
			Grupo grupoExistente = em.find(Grupo.class, g.getID());
			if (grupoExistente == null) {
				throw new GrupoException("No se ha encontrado el grupo");
			}
			em.merge(g);
		}
	}


	@Override
	public void comprobarPlazas(Grupo g) throws GrupoException {
		if(g.getPlazasDisponibles()==0) {
			throw new GrupoException("No hay mas hueco en el grupo");
		}
	}
	
	
	public Grupo buscarPorCursoLetra(Integer titulacion, Integer curso, String letra) throws GrupoException {
		
		List<Grupo> grupos = buscarPorCursoLetra(curso,letra);
        boolean encontrado = false;     
        int i = 0;
        Grupo g = null;
        while(i < grupos.size() && !encontrado) {
        	
     	   g = grupos.get(i); 
     	   
     	   if(g.getTitulacion().getCodigo().equals(titulacion)) {
     		   encontrado=true;
     	   }
     	   
     	   i++;
        }
        
        
		return g;
	}
	
	
	public List<Grupo> buscarPorCursoLetra(Integer curso, String letra) throws GrupoException{
		
		TypedQuery<Grupo> query = em.createQuery("Select g from Grupo g where g.Curso=:curso and g.Letra=:letra", Grupo.class);
		query.setParameter("curso", curso);
		query.setParameter("letra", letra);
        List<Grupo> grupos = query.getResultList();
	        
		return grupos;
	}
	
	@Override
	public List<Grupo> mostrarDatosAdmin() throws GrupoException{
		
		TypedQuery<Grupo> query = em.createQuery("SELECT g FROM Grupo g",Grupo.class);
		List<Grupo> grupos = query.getResultList();

		if(grupos == null || grupos.size() == 0) {
			throw new GrupoException("No se han encontrado grupos");
		}
		
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
			g.setPlazasDisponibles(g.getPlazasDisponibles()-1);//Disminuir en uno plazas
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
