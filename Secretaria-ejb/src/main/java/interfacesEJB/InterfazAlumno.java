/**
 * 
 */
package interfacesEJB;

import java.util.List;

import javax.ejb.Local;
import excepcionesEJB.AlumnoException;
import jpa.Alumno;


/**
 * @author alumno
 *
 */
@Local
public interface InterfazAlumno {

	/**Este método es para visualizar el alumno
	 * @param a alumno a visualizar*/
	public Alumno VisualizarAlumno(String dni) throws AlumnoException;
	
	public List<Alumno> mostrarDatosAdmin() throws AlumnoException;
	
	public void borrarAlumnos() throws AlumnoException;
	
}
