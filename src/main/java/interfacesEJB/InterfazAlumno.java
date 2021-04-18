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

	/**Este método 
	 * @param dir dirección donde se encuentra el excel*/
	public void validarAcceso(Alumno a) throws AlumnoException;
	
	
	
}
