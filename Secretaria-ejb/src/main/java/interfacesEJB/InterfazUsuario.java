package interfacesEJB;

import javax.ejb.Local;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.UsuarioException;
import jpa.Usuario;

@Local
public interface InterfazUsuario {

	/**Este método comprueba que el acceso se realiza de forma correcta
	 * @param a alumno a validar*/
	public void validarAcceso(String nombre, String pass) throws UsuarioException;
	
	public void crearUsuario(String dni, String nombre, String pass, String rol) throws UsuarioException,AlumnoException;
	
	public void mostrarDatos(String nombre) throws UsuarioException,AlumnoException;
}
