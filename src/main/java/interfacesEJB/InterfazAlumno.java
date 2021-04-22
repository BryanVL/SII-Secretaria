/**
 * 
 */
package interfacesEJB;

import javax.ejb.Local;
import excepcionesEJB.AlumnoException;
import jpa.Alumno;


/**
 * @author alumno
 *
 */
@Local
public interface InterfazAlumno {

	/**Este método hace algo xD
	 * @param a alumno */
	public void validarAcceso(Alumno a) throws AlumnoException;
	
	/**Este método es para visualizar el alumno
	 * @param a alumno a visualizar*/
	public Alumno VisualizarAlumno(Alumno a) throws AlumnoException;
	
}
