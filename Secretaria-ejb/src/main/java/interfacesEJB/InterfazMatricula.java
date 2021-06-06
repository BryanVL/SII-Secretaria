package interfacesEJB;

import java.util.List;

import javax.ejb.Local;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.MatriculaException;
import jpa.Alumno;
import jpa.Matricula;

@Local
public interface InterfazMatricula {

	
	/*
	 * Este método sirve para visualizar el contenido de una matrícula.
	 * @param m Matricula a visualizar.
	 */
	public Matricula VisualizarMatricula(String curso, Integer idexp) throws MatriculaException;

	public List<Matricula> mostrarDatosAdmin() throws MatriculaException;
	
	public void borrarMatriculas() throws MatriculaException;
	
}
