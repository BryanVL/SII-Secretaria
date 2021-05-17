package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.ExpedienteException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazAlumno;
import interfacesEJB.InterfazExpediente;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazTitulacion;
import jpa.Expediente;
import jpa.Titulacion;

public class ExpedientePrueba {

	private static final Logger LOG = Logger.getLogger(ExpedientePrueba.class.getCanonicalName());

	private static final String ALUMNO_EJB = "java:global/classes/AlumnoImpl!ejb.AlumnoImpl";
	private static final String TITULACION_EJB = "java:global/classes/TitulacionImpl!ejb.TitulacionImpl";
	private static final String EXPEDIENTE_EJB = "java:global/classes/ExpedienteImpl!ejb.ExpedienteImpl";
	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazExpediente interfazExpediente;
	private InterfazImportar interfazImportarTit;
	private InterfazImportar interfazImportarAl;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(EXPEDIENTE_EJB);
		interfazExpediente = (InterfazExpediente) SuiteTest.ctx.lookup(EXPEDIENTE_EJB);
		interfazImportarTit = (InterfazImportar) SuiteTest.ctx.lookup(TITULACION_EJB);
		interfazImportarAl  = (InterfazImportar) SuiteTest.ctx.lookup(ALUMNO_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testImportarExpediente() {
		String dir = "src/test/resources/alumnos.csv";
		
		try {
			//ES NECESARIO IMPORTAR PREVIAMENTE ALUMNOS Y TITULACION PARA QUE NO OCURRAN ERRORES:
			interfazImportarAl.Importar(dir);
			interfazImportarTit.Importar("src/test/resources/Titulacion.csv");
			
			//UNA VEZ IMPORTADOS LOS ANTERIORES DATOS, SE IMPORTAN LOS EXPEDIENTES Y SE COMPRUEBA QUE TODO ES CORRECTO:
			interfazImportar.Importar(dir);
			
			Expediente e = new Expediente();
			e.setNum_expediente(104200001);
			Expediente expediente = interfazExpediente.VisualizarExpediente(104200001);
			
			if(expediente!=null) {
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
	@Requisitos({"R11"})
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
