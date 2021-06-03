package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.UsuarioException;
import interfacesEJB.InterfazAlumno;
import interfacesEJB.InterfazUsuario;
import jpa.Alumno;
import jpa.Asignaturas_Matricula;
import jpa.Usuario;

@Stateless
@LocalBean
public class UsuarioImpl implements InterfazUsuario{
	
	@PersistenceContext(unitName = "Secretaria")
    private EntityManager em;
	
	
	@Override
	public void validarAcceso(String nombre, String pass) throws UsuarioException {
    	Usuario usuario = em.find(Usuario.class, nombre);
    	
    	if(usuario==null) {
    		throw new UsuarioException();
    	}
    	
    	if(pass != usuario.getPassword()) {
    		throw new UsuarioException();
    	}
    	
	}


	@Override
	public void crearUsuario(String dni, String nombre, String pass, String rol) throws UsuarioException, AlumnoException{
		
		try {
		
			TypedQuery<Alumno> query = em.createQuery("SELECT a FROM Alumno a WHERE a.DNI= :dni",Alumno.class);
			query.setParameter("dni", dni);
			Alumno alumno = query.getSingleResult();
			
			if(alumno == null) {
				throw new AlumnoException("No existe un alumno asociado a ese dni.");
			}
			
			if(alumno.getUser() == null) {
				
				Usuario usuario = new Usuario();
				usuario.setUsuario(nombre);
				usuario.setPassword(pass);
				usuario.setRol(rol);
				
				em.persist(usuario);
			} else {
				throw new UsuarioException("Ya existe un usuario asociado a este dni");
			}
		
		
		} catch(AlumnoException e) {
			e.printStackTrace();
		} catch(UsuarioException e) {
			e.printStackTrace();
		}
	}
}
