package ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.UsuarioException;
import interfacesEJB.InterfazUsuario;
import jpa.Alumno;
import jpa.Grupo;
import jpa.Titulacion;
import jpa.Usuario;

@Stateless
@LocalBean
public class UsuarioImpl implements InterfazUsuario{
	
	@PersistenceContext(unitName = "Secretaria")
    private EntityManager em;
	
	
	@Override
	public Usuario validarAcceso(String nombre, String pass) throws UsuarioException {
    	Usuario usuario = em.find(Usuario.class, nombre);
    	
    	if(usuario==null) {
    		throw new UsuarioException("No se ha encontrado el usuario");
    	}
    	
    	if(!pass.equals(usuario.getPassword())) {
    		throw new UsuarioException("La constraseña no es correcta");
    	}
    	
    	return usuario;
    	
	}


	@Override
	public void crearUsuario(String dni, String nombre, String pass) throws UsuarioException, AlumnoException{
		
		TypedQuery<Alumno> query = em.createQuery("SELECT a FROM Alumno a WHERE a.DNI= :dni",Alumno.class);
		query.setParameter("dni", dni);
		List<Alumno> alumnos = query.getResultList();
		
		if(alumnos == null || alumnos.size() == 0) {
			throw new AlumnoException("No se encuentra el alumno asociado a ese dni");
		}
		Alumno alumno = alumnos.get(0);
		
		if(alumno.getUsuario() == null) {
			
			Usuario usuario = new Usuario();
			usuario.setUsuario(nombre);
			usuario.setPassword(pass);
			usuario.setRol("Alumno");
			usuario.setAlumno(alumno);
			em.persist(usuario);
			
		} else {
			throw new UsuarioException("Ya existe un usuario asociado a este dni");
		}
		
	}
	
	public void crearSecretaria(String nombre, String pass) throws UsuarioException{
		
		Usuario usuario = em.find(Usuario.class, nombre);
		if(usuario != null) {
			throw new UsuarioException("Ya existe ese usuario");
		} else {
			usuario = new Usuario();
			usuario.setUsuario(nombre);
			usuario.setPassword(pass);
			usuario.setRol("Admin");
			em.persist(usuario);
		}
		
	}
	
	@Override
	public Usuario mostrarDatos(String nombre) throws UsuarioException{
		
		Usuario usuario = em.find(Usuario.class, nombre);
	
		if(usuario == null) {
			throw new UsuarioException("No se ha encontrado el usuario");
		}
		return usuario;
	}
	
	@Override
	public List<Usuario> mostrarDatosAdmin() throws UsuarioException{
		
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u",Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		if(usuarios == null || usuarios.size() == 0) {
			throw new UsuarioException("No se ha encontrado el usuario");
		}
		
		return usuarios;
	}
	
	@Override
	public void borrarUsuario(String usuario) throws UsuarioException {
		if(!usuario.equals("admin")) {
			Usuario u = em.find(Usuario.class, usuario);
			Alumno al = u.getAlumno();
			al.setUsuario(null);
			em.merge(al);
			em.remove(u);
		}
	}
	
	@PostConstruct
	public void comprobarAdmin() {
		
		Usuario usuario = em.find(Usuario.class, "admin");
		
		if(usuario==null) {
			Usuario user = new Usuario();
			user.setUsuario("admin");
			user.setPassword("admin");
			user.setRol("Admin");
			em.persist(user);
		}
		
		
	}
	
	
}
