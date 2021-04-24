package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazExpediente;
import interfacesEJB.InterfazImportar;

public class ExpedientePrueba {

	private static final Logger LOG = Logger.getLogger(ExpedientePrueba.class.getCanonicalName());

	private static final String EXPEDIENTE_EJB = "java:global/classes/ExpedienteImpl!interfacesEJB.InterfazImportar";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazExpediente interfazExpediente;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(EXPEDIENTE_EJB);
		
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	public void testImportarExpediente() {
		String dir = "src/test/resources/Titulacion.xlsx";
		try {
			interfazImportar.Importar(dir);
			assertEquals(1,1);
			
			
		} catch (ImportarException e) {
			// TODO Auto-generated catch block
			fail("No debería lanzar excepción");
		}
		
	}
	
	
	@Test
	public void testVisualizarExpediente() {
		assertEquals(1,1);
	}
	
	
	
	
	
}
