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
	private static final String ASIGNATURA_EJB = "java:global/classes/AsignaturaImpl!ejb.AsignaturaImpl";
	private static final String TITULACION_EJB = "java:global/classes/TitulacionImpl!ejb.TitulacionImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazImportar interfazImportar2;
	private InterfazImportar interfazImportar3;
	
	private InterfazOptativa interfazOptativa;
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(OPTATIVA_EJB);
		interfazImportar2 = (InterfazImportar) SuiteTest.ctx.lookup(ASIGNATURA_EJB);
		interfazImportar3 = (InterfazImportar) SuiteTest.ctx.lookup(TITULACION_EJB);
		
		interfazOptativa = (InterfazOptativa) SuiteTest.ctx.lookup(OPTATIVA_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testImportarOptativa() {
		//Elegir desde donde realizar la importacion:
//		String dir = "src/test/resources/Optativas_Informatica.csv";
//		String dir2 = "src/test/resources/GII.csv";
//		String dir3 = "src/test/resources/Titulacion.csv";
		String dir = "src/test/resources/Oferta asignaturas.xlsx";
		String dir2 = "src/test/resources/Oferta asignaturas.xlsx";
		String dir3 = "src/test/resources/Titulacion.xlsx";
		
		try {

			interfazImportar3.Importar(dir3);
			interfazImportar2.Importar(dir2);
			interfazImportar.Importar(dir);
			Optativa op = new Optativa();
			Asignatura a = new Asignatura();
			a.setReferencia(53158);
			op.setAsignatura(a);
			
			Optativa opta = interfazOptativa.VisualizarOptativa(53158);
			
			if(a!=null) {
				assertEquals(op,opta);
			}
			
		} catch (ImportarException e) {
			fail("No debería lanzarse excepción");
		} catch (OptativaException e) {
			fail("No debería lanzarse excepción");
		}
		
	}
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarOptativa() {
		
		try {
			Asignatura asignatura = new Asignatura();
			asignatura.setReferencia(12345);
			asignatura.setCodigo(900);
			asignatura.setCreditos_total((float)6);
			asignatura.setOfertada("Si");
			asignatura.setNombre("Pruebas con Junit");
			
			Optativa opt = new Optativa();
			opt.setAsignatura(asignatura);
			opt.setMencion("Informatica");
			opt.setPlazas(50);
			
			Optativa optativa = interfazOptativa.VisualizarOptativa(12345);
			
			if(optativa != null) {
				assertEquals(opt,optativa);
			} else {
				fail("No coinciden las referencias");
			}
		} catch (OptativaException e) {
			fail("No debería lanzarse excepción");
		}
		
	}
	
}
