package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import excepcionesEJB.ExpedienteException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazExpediente;
import interfacesEJB.InterfazImportar;
import jpa.Expediente;
import jpa.Titulacion;

public class ExpedientePrueba {

	private static final Logger LOG = Logger.getLogger(ExpedientePrueba.class.getCanonicalName());

	private static final String EXPEDIENTE_EJB = "java:global/classes/ExpedienteImpl!ejb.ExpedienteImpl";
	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazExpediente interfazExpediente;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(EXPEDIENTE_EJB);
		interfazExpediente = (InterfazExpediente) SuiteTest.ctx.lookup(EXPEDIENTE_EJB);
		
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	public void testImportarExpediente() {
		String dir = "src/test/resources/alumnos.csv";
		
		try {
			interfazImportar.Importar(dir);
			
			Expediente e = new Expediente();
			e.setNum_expediente(104200001);
			Expediente expediente = interfazExpediente.VisualizarExpediente(104200001);
			
			if(e!=null) {
				assertEquals(e,expediente);
			}else {
				fail("No coinciden las referencias");
			}
		} catch (ImportarException e) {
			fail("No debería lanzarse excepción");
		} catch (ExpedienteException e) {
			// TODO Auto-generated catch block
			fail("No debería lanzarse excepción");
		}
	}
	
	@Test
	public void testVisualizarExpediente() {
		
		//Probamos si el expediente que ya tenemos en la base de datos es el mismo que obtenemos al llamar al método.
				Expediente ex = new Expediente();
				ex.setNum_expediente(123);
				
				try {
					assertEquals(ex,interfazExpediente.VisualizarExpediente(123));
				} catch (ExpedienteException e) {
					fail("No debería lanzarse excepción");
				}
				
	}
}
