package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.AsignaturaException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.OptativaException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazAsignatura;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazOptativa;
import jpa.Asignatura;
import jpa.Optativa;
import jpa.Titulacion;


public class OptativaPrueba {

	private static final Logger LOG = Logger.getLogger(OptativaPrueba.class.getCanonicalName());

	private static final String OPTATIVA_EJB = "java:global/classes/OptativaImpl!ejb.OptativaImpl";
	private static final String ASIGNATURA_EJB = "java:global/classes/OptativaImpl!ejb.AsignaturaImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazOptativa interfazOptativa;
	private InterfazAsignatura interfazAsignatura;
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(OPTATIVA_EJB);
		interfazOptativa = (InterfazOptativa) SuiteTest.ctx.lookup(OPTATIVA_EJB);
		interfazAsignatura = (InterfazAsignatura) SuiteTest.ctx.lookup(ASIGNATURA_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testImportarOptativa() {

		String dir = "src/test/resources/Optativas_Informatica.csv";
		
		try {
			interfazImportar.Importar(dir);
			
			
			assertEquals(1, 1);
		} catch (ImportarException e) {
			fail("No debería lanzarse excepción");
		}
		
	}
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarOptativa() {
		
		try {
			//Probamos si la titulacion que ya tenemos en la base de datos es la misma que obtenemos al llamar al método.
			Optativa opt = new Optativa();
			
			Asignatura asig;
			asig = interfazAsignatura.VisualizarAsignatura(53158);
				
			opt.setAsignatura(asig);
			
			assertEquals(asig,interfazOptativa.VisualizarOptativa(53158));
		} catch (OptativaException e) {
			fail("No debería lanzarse excepción");
		} catch (AsignaturaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
}
