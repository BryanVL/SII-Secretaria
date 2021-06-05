package interfacesEJB;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.NoResultException;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.UsuarioException;
import jpa.Usuario;

@Local
public interface InterfazUsuario {

	/**Este método comprueba que el acceso se realiza de forma correcta
	 * @param a alumno a validar*/
	public Usuario validarAcceso(String nombre, String pass) throws UsuarioException;
	
	public void crearUsuario(String dni, String nombre, String pass, String rol) throws UsuarioException,AlumnoException;
	
	public Usuario mostrarDatos(String nombre) throws UsuarioException;
	
	public List<Usuario> mostrarDatosAdmin() throws UsuarioException;
	
	public void crearSecretaria(String nombre, String pass) throws UsuarioException;
	
	public void borrarUsuarios() throws UsuarioException;
	
	public Usuario visualizarUsuario(String usuario) throws UsuarioException;
}
