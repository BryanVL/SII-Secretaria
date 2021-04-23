package interfacesEJB;

import java.util.List;

import javax.ejb.Local;

import jpa.Alumno;
import jpa.Grupo;

@Local
public interface InterfazAsignaturas_Matricula {
	
	/*
	 * Este método es para visualizar la lista de alumnos que pertenecen a un grupo.
	 * @param g Grupo del que se observa la lista de alumnos.
	 */
	public List<Alumno> VisualizarAlumnosPorGrupo(Grupo g);
	
}
