package interfacesEJB;

import javax.ejb.Local;

import excepcionesEJB.MatriculaException;
import jpa.Matricula;

@Local
public interface InterfazMatricula {

	
	/*
	 * Este método sirve para visualizar el contenido de una matrícula.
	 * @param m Matricula a visualizar.
	 */
	public Matricula VisualizarMatricula(Matricula m) throws MatriculaException;
	
}
