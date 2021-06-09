package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.AlumnoException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazAlumno;
import interfacesEJB.InterfazTitulacion;
import jpa.Alumno;
import jpa.Titulacion;

public class AlumnoPrueba {


	private static final Logger LOG = Logger.getLogger(AlumnoPrueba.class.getCanonicalName());

	private static final String ALUMNO_EJB = "java:global/classes/AlumnoImpl!ejb.AlumnoImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazAlumno interfazAlumno;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazAlumno  = (InterfazAlumno) SuiteTest.ctx.lookup(ALUMNO_EJB);
		
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testImportarAlumno() {
		//Elegir la ruta desde la que probar la importacion:
		String dir = "src/test/resources/alumnos.csv";
		//String dir = "src/test/resources/alumnos.xlsx";
		try {
			interfazAlumno.Importar(dir);
			
			Alumno a = interfazAlumno.VisualizarAlumno("95115697E");
			
			if(a!=null) {
				assertEquals(a.getDNI(),"95115697E");
			}else {
				fail("No coinciden las referencias");
			}
		} catch (ImportarException e) {
			fail("No debería lanzarse excepción");
		} catch (AlumnoException e) {
			// TODO Auto-generated catch block
			fail("No debería lanzarse excepción");
		}
	}
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarAlumno() {
		
		//Probamos si un alumno que ya tenemos en la base de datos es el mismo que obtenemos al llamar al método.
		Alumno a = new Alumno();
		a.setID(1234L);
		a.setNombre("Bryan");
		a.setApellido1("velicka");
		a.setDNI("125678A");
		a.setEmail_institucional("velicka.b@uma.es");
		
		
		try {
			assertEquals(a,interfazAlumno.VisualizarAlumno("125678A"));
		} catch (AlumnoException e) {
			fail("No debería lanzarse excepción");
		}
	}
}
