package testEJB;

import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.AlumnoException;
import excepcionesEJB.UsuarioException;
import interfacesEJB.InterfazAlumno;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazUsuario;
import jpa.Alumno;
import jpa.Usuario;

public class UsuarioPrueba {

	private static final Logger LOG = Logger.getLogger(AlumnoPrueba.class.getCanonicalName());

	private static final String ALUMNO_EJB = "java:global/classes/AlumnoImpl!ejb.AlumnoImpl";
	private static final String USUARIO_EJB = "java:global/classes/UsuarioImpl!ejb.UsuarioImpl";
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazAlumno interfazAlumno;
	private InterfazUsuario interfazUsuario;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazAlumno  = (InterfazAlumno) SuiteTest.ctx.lookup(ALUMNO_EJB);
		interfazUsuario = (InterfazUsuario) SuiteTest.ctx.lookup(USUARIO_EJB);
		
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF1"})
	public void testValidarAcceso() {
		try {
			
			Usuario usuario = new Usuario();
			usuario.setUsuario("Marco12");
			usuario.setPassword("12345");
			usuario.setRol("Alumno");
			interfazUsuario.validarAcceso("Marco12","12345");
			
		}catch(UsuarioException e) {
			fail("No deberia lanzarse esta excepcion.");
		}
	}
	
	@Test
	@Requisitos({"RF"})
	public void testCrearUsuario() {
		try {
			
			Alumno alumno = interfazAlumno.VisualizarAlumno("125681D");
			Usuario usuario = new Usuario();
			interfazUsuario.crearUsuario("125680C", "Nowel13", "pepepe13");
			
			
		} catch(AlumnoException e) {
			e.printStackTrace();
		} catch(UsuarioException e) {
			e.printStackTrace();
		}
	}
}
