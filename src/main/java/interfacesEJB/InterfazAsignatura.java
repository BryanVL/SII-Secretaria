package interfacesEJB;

import javax.ejb.Local;

import excepcionesEJB.AsignaturaException;
import jpa.Asignatura;

@Local
public interface InterfazAsignatura {

	/**Este método es para visualizar el alumno
	 * @param a alumno a visualizar*/
	public Asignatura VisualizarAsignatura(Asignatura a) throws AsignaturaException;
	
}
