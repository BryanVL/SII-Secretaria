package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import jpa.Alumno;
import jpa.Asignaturas_Matricula;
import jpa.Grupo;

@Stateless
@LocalBean
public class Asignaturas_MatriculaImpl {
	@PersistenceContext(name = "Secretaria")
	EntityManager em;
	
	public List<Alumno> VisualizarAlumnosPorGrupo(Grupo g){
		TypedQuery query = em.createQuery("SELECT a FROM Asignaturas_Matricula a WHERE g.getID() = a.getGrupo().getID()",Alumno.class);
		List<Asignaturas_Matricula> listaAsig = query.getResultList();
		
		List<Alumno> listaAlum = new ArrayList();
		
		for(Asignaturas_Matricula a : listaAsig) {
			listaAlum.add(a.getMatricula().getExpediente().getAlumno());
		}
		
		return listaAlum;
	}
	
}
