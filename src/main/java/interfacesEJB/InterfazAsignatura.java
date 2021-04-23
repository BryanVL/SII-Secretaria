package interfacesEJB;

import excepcionesEJB.AsignaturaException;
import jpa.Asignatura;

public interface InterfazAsignatura {

	/**Este método es para visualizar el alumno
	 * @param a alumno a visualizar*/
	public Asignatura VisualizarAsignatura(Asignatura a) throws AsignaturaException;
	
}
