package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazMatricula;
import interfacesEJB.InterfazTitulacion;

public class MatriculaPrueba {

	
	private static final Logger LOG = Logger.getLogger(MatriculaPrueba.class.getCanonicalName());
	private static final String GRUPO_EJB = "java:global/classes/MatriculaImpl!ejb.MatriculaImpl";	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private InterfazImportar interfazImportar;
	private InterfazMatricula interfazMatricula; 

		
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(GRUPO_EJB);
		interfazMatricula = (InterfazMatricula)  SuiteTest.ctx.lookup(GRUPO_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	public void testImportarMatricula() {
		
		
	}
	
	
	@Test
	public void testVisualizarMatricula() {
		assertEquals(1,1);
	}
}
