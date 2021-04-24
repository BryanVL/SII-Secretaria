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

	/**Este método comprueba que el acceso se realiza de forma correcta
	 * @param a alumno a validar*/
	public void validarAcceso(Alumno a) throws AlumnoException;
	
	/**Este método es para visualizar el alumno
	 * @param a alumno a visualizar*/
	public Alumno VisualizarAlumno(Long id) throws AlumnoException;
	
}
