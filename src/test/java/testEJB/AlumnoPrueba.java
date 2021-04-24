package testEJB;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import interfacesEJB.InterfazAlumno;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazTitulacion;

public class AlumnoPrueba {


	private static final Logger LOG = Logger.getLogger(AlumnoPrueba.class.getCanonicalName());

	private static final String ALUMNO_EJB = "java:global/classes/AlumnoImpl!ejb.AlumnoImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazAlumno interfazAlumno;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(ALUMNO_EJB);
		interfazAlumno  = (InterfazAlumno) SuiteTest.ctx.lookup(ALUMNO_EJB);
		
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	public void testValidarAcceso() {
		assertEquals(1,1);
	}
	
	@Test
	public void testVisualizarAlumno() {
		assertEquals(1,1);
	}
	
	
	
}
