/**
 * 
 */
package interfacesEJB;

import javax.ejb.Local;

import excepcionesEJB.AlumnoException;


/**
 * @author alumno
 *
 */
@Local
public interface InterfazAlumno {

	/**Este método 
	 * @param dir dirección donde se encuentra el excel*/
	public void validarAcceso(String dir) throws AlumnoException;
	
}
