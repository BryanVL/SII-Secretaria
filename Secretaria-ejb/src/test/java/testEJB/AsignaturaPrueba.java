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
import interfacesEJB.InterfazOptativa;
import interfacesEJB.InterfazTitulacion;
import jpa.Asignatura;
import jpa.Titulacion;

public class AsignaturaPrueba {

	private static final Logger LOG = Logger.getLogger(OptativaPrueba.class.getCanonicalName());

	private static final String ASIGNATURA_EJB = "java:global/classes/AsignaturaImpl!ejb.AsignaturaImpl";	
	private static final String TITULACION_EJB = "java:global/classes/TitulacionImpl!ejb.TitulacionImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazTitulacion interfazTitulacion;
	private InterfazAsignatura interfazAsignatura;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazTitulacion = (InterfazTitulacion) SuiteTest.ctx.lookup(TITULACION_EJB);
		interfazAsignatura = (InterfazAsignatura) SuiteTest.ctx.lookup(ASIGNATURA_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testImportarAsignatura() {

//		String dir = "src/test/resources/GII.csv";
		String dir = "src/test/resources/Oferta asignaturas.xlsx";
		String dir2 = "src/test/resources/Titulacion.csv";
		
		try {
			
			interfazTitulacion.Importar(dir2);
			interfazAsignatura.Importar(dir);
			
			Asignatura asig = new Asignatura();
			asig.setReferencia(50658);
			
			Asignatura asignatura = interfazAsignatura.VisualizarAsignatura(50658);
			
			if(asignatura != null) {
				assertEquals(asig,asignatura);
			} else {
				fail("No coinciden las referencias");
			}
		} catch (ImportarException e) {
			fail("No debería lanzarse excepción");
		} catch (AsignaturaException e) {
			fail("No debería lanzarse excepción");
		}
		
		
	}
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarAsignatura() {
		try {
		
			Asignatura asig = new Asignatura();
			asig.setReferencia(12345);
			
			Asignatura asignatura = interfazAsignatura.VisualizarAsignatura(12345);
		
			if(asignatura != null) {
				assertEquals(asig,asignatura);
			} else {
				fail("No coinciden las referencias");
			}
		} catch (AsignaturaException e) {
			fail("No debería lanzarse excepción");
		}
		
	}
	
}
