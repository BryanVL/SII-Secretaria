package interfacesEJB;

import java.util.List;

import javax.ejb.Local;

import excepcionesEJB.AsignaturaException;
import excepcionesEJB.GrupoException;
import excepcionesEJB.MatriculaException;
import jpa.Alumno;
import jpa.Asignatura;
import jpa.Asignaturas_Matricula;
import jpa.Grupo;
import jpa.Matricula;

@Local
public interface InterfazAsignaturas_Matricula {
	
	/*
	 * Este método es para visualizar la lista de alumnos que pertenecen a un grupo.
	 * @param g Grupo del que se observa la lista de alumnos.
	 */
	public List<Alumno> VisualizarAlumnosPorGrupo(Grupo g) throws GrupoException;
	
	public List<Asignaturas_Matricula> AplicarFiltros(Grupo g) throws GrupoException;
	
	public List<Asignaturas_Matricula> AplicarFiltros(Asignatura a) throws AsignaturaException;

	public List<Asignaturas_Matricula> AplicarFiltros(Matricula m) throws MatriculaException;

}
