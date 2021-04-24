package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.AsignaturaException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazAsignatura;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazOptativa;
import jpa.Asignatura;
import jpa.Titulacion;

public class AsignaturaPrueba {

	private static final Logger LOG = Logger.getLogger(OptativaPrueba.class.getCanonicalName());

	private static final String ASIGNATURA_EJB = "java:global/classes/AsignaturaImpl!ejb.AsignaturaImpl";	
	private static final String TITULACION_EJB = "java:global/classes/TitulacionImpl!ejb.TitulacionImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazImportar interfazImportar2;
	private InterfazAsignatura interfazAsignatura;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(ASIGNATURA_EJB);
		interfazImportar2 = (InterfazImportar) SuiteTest.ctx.lookup(TITULACION_EJB);
		interfazAsignatura = (InterfazAsignatura) SuiteTest.ctx.lookup(ASIGNATURA_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Ignore
	@Test
	@Requisitos({"RF7"})
	public void testImportarAsignatura() {

		String dir = "src/test/resources/GII.csv";
		String dir2 = "src/test/resources/Titulacion.csv";
		
		try {
			
			interfazImportar2.Importar(dir2);
			interfazImportar.Importar(dir);
			
			
			assertEquals(1, 1);
		} catch (ImportarException e) {
			fail("No debería lanzarse excepción");
		}
		
		
	}
	
	@Ignore
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarAsignatura() {
		
		//Probamos si la titulacion que ya tenemos en la base de datos es la misma que obtenemos al llamar al método.
		Asignatura asig = new Asignatura();
		asig.setReferencia(50658);
		
		try {
			assertEquals(asig,interfazAsignatura.VisualizarAsignatura(50658));
		} catch (AsignaturaException e) {
			fail("No debería lanzarse excepción");
		}
		
	}
	
}
