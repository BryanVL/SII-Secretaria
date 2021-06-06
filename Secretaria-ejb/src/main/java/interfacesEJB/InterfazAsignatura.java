package interfacesEJB;

import java.util.List;

import javax.ejb.Local;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.AsignaturaException;
import excepcionesEJB.ImportarException;
import jpa.Alumno;
import jpa.Asignatura;

@Local
public interface InterfazAsignatura {

	/**Este método es para visualizar el alumno
	 * @param a alumno a visualizar*/
	public Asignatura VisualizarAsignatura(Integer Referencia) throws AsignaturaException;
	
	public List<Asignatura> mostrarDatosAdmin() throws AsignaturaException;
	
	public void borrarAsignaturas() throws AsignaturaException;
	
	public void Importar(String dir) throws ImportarException;
}
