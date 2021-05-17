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
import jpa.Matricula_PK;

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
		
		TypedQuery<Asignaturas_Matricula> query = em.createQuery("SELECT a FROM Asignaturas_Matricula a WHERE a.grupo.ID= :id",Asignaturas_Matricula.class);
		query.setParameter("id", g.getID());
		List<Asignaturas_Matricula> listaAsig = query.getResultList();
		
		List<Alumno> listaAlum = new ArrayList<Alumno>();
		
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
		
		TypedQuery<Asignaturas_Matricula> query = em.createQuery("Select a from Asignaturas_Matricula a where a.grupo.ID = :id",Asignaturas_Matricula.class);
		query.setParameter("id", g.getID());
		List<Asignaturas_Matricula> listaAsig = query.getResultList();
		
		return listaAsig;
	}

	@Override
	public List<Asignaturas_Matricula> AplicarFiltros(Integer referencia) throws AsignaturaException {
		
		Asignatura asignaturaExistente = em.find(Asignatura.class,referencia);

		if(asignaturaExistente == null) {
		throw new AsignaturaException("La asignatura indicada no existe.");
		}

		TypedQuery<Asignaturas_Matricula> query = em.createQuery("Select a from Asignaturas_Matricula a where a.asignatura.Referencia = :asignatura",Asignaturas_Matricula.class);
		query.setParameter("asignatura", referencia);
		List<Asignaturas_Matricula> listaAsig = query.getResultList();

		return listaAsig;
	}

	@Override
	public List<Asignaturas_Matricula> AplicarFiltros(String curso,Integer idExp) throws MatriculaException {
		
		Matricula_PK mpk = new Matricula_PK();
		mpk.setCurso_academico(curso);
		mpk.setIdExp(idExp);
		Matricula matriculaExistente = em.find(Matricula.class,mpk);

		if(matriculaExistente == null) {
			throw new MatriculaException("La matricula indicada no existe.");
		}

		TypedQuery<Asignaturas_Matricula> query = em.createQuery("Select a from Asignaturas_Matricula a where a.matricula.id = :id",Asignaturas_Matricula.class);
		query.setParameter("id", mpk);
		List<Asignaturas_Matricula> listaAsig = query.getResultList();

		return listaAsig;
		
	}
	
	

}
