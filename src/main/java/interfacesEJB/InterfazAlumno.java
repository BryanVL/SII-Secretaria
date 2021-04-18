package interfacesEJB;

import excepcionesEJB.AlumnoException;
import jpa.Alumno;

public interface InterfazAlumno {
	public void Importar_Alumno(Alumno a) throws AlumnoException;
}
