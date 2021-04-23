package ejb;

import java.util.ArrayList;
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
	
	public List<Alumno> VisualizarAlumnosPorGrupo(Integer curso, String letra){
		List<Alumno> lista = new ArrayList();
		
		return null;
	}
	
}
