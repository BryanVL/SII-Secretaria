package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.Alumno;

@Stateless
@LocalBean
public class Asignaturas_MatriculaImpl {
	@PersistenceContext(name = "Secretaria")
	EntityManager em;
	
	public List<Alumno> VisualizarAlumnosPorGrupo(Integer cur, String let){
		List<Alumno> lista = em.createQuery("SELECT idExp FROM asignaturas_Matricula where curso == cur && letra == let").getResultList();
		
		
		
		return lista;
	}
	
}
