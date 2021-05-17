package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import excepcionesEJB.AsignaturaException;
import excepcionesEJB.GrupoException;
import excepcionesEJB.MatriculaException;
import interfacesEJB.InterfazAsignaturas_Matricula;
import jpa.Alumno;
import jpa.Asignatura;
import jpa.Asignaturas_Matricula;
import jpa.Grupo;
import jpa.Matricula;

@Stateless
@LocalBean
public class Asignaturas_MatriculaImpl implements InterfazAsignaturas_Matricula{
	@PersistenceContext(name = "Secretaria")
	EntityManager em;
	
	@Override
	public List<Alumno> VisualizarAlumnosPorGrupo(Grupo g) throws GrupoException{
		
		Grupo grupoExistente = em.find(Grupo.class,g.getID());
		
		if(grupoExistente == null) {
			throw new GrupoException("El grupo indicado no existe.");
		}
		
		TypedQuery query = em.createQuery("SELECT a FROM Asignaturas_Matricula a WHERE a.grupo.ID=:id",Asignaturas_Matricula.class);
		query.setParameter("id", g.getID());
		List<Asignaturas_Matricula> listaAsig = query.getResultList();
		
		List<Alumno> listaAlum = new ArrayList();
		
		for(Asignaturas_Matricula a : listaAsig) {
			listaAlum.add(a.getMatricula().getExpediente().getAlumno());
		}
		
		return listaAlum;
	}

	@Override
	public List<Asignaturas_Matricula> AplicarFiltros(Grupo g) throws GrupoException{
		
		Grupo grupoExistente = em.find(Grupo.class,g.getID());
		
		if(grupoExistente == null) {
			throw new GrupoException("El grupo indicado no existe.");
		}
		
		TypedQuery query = em.createQuery("Select a from Asignaturas_Matricula where a.grupo.ID := id",Asignaturas_Matricula.class);
		query.setParameter("id", g.getID());
		List<Asignaturas_Matricula> listaAsig = query.getResultList();
		
		return listaAsig;
	}

	@Override
	public List<Asignaturas_Matricula> AplicarFiltros(Asignatura a) throws AsignaturaException {
		
		Asignatura asignaturaExistente = em.find(Asignatura.class,a.getReferencia());

		if(asignaturaExistente == null) {
		throw new AsignaturaException("La asignatura indicada no existe.");
		}

		TypedQuery query = em.createQuery("Select a from Asignaturas_Matricula a where a.asignatura.referencia =: asignatura",Asignaturas_Matricula.class);
		query.setParameter("asignatura", a.getReferencia());
		List<Asignaturas_Matricula> listaAsig = query.getResultList();

		return listaAsig;
	}

	@Override
	public List<Asignaturas_Matricula> AplicarFiltros(Matricula m) throws MatriculaException {
		
		Matricula matriculaExistente = em.find(Matricula.class,m.getId());

		if(matriculaExistente == null) {
		throw new MatriculaException("La matricula indicada no existe.");
		}

		TypedQuery query = em.createQuery("Select a from Asignaturas_Matricula a where a.matricula.id =: id",Asignaturas_Matricula.class);
		query.setParameter("id", m.getId());
		List<Asignaturas_Matricula> listaAsig = query.getResultList();

		return listaAsig;
		
	}
	
	

}
