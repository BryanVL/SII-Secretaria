package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.ImportarException;
import excepcionesEJB.MatriculaException;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazMatricula;
import jpa.Matricula;
import jpa.Matricula_PK;




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
	@Requisitos({"RF7"})
	public void testImportarMatricula() {
		String dir = "src/test/resources/Titulacion.csv";
		
		try {
			interfazImportar.Importar(dir);
			
			Matricula m = new Matricula();
			
			Matricula_PK mpk=new Matricula_PK();
			mpk.setCurso_academico("2020/2021");
			mpk.setIdExp(1);
			m.setId(mpk);
			m.setEstado("Activa");
			
			m.setNum_Archivo(306000001);
			m.setTurno_Preferente("Mañana");
			m.setNuevo_Ingreso("Si");
			
			
			
			Matricula m2 = interfazMatricula.VisualizarMatricula();
			
			if(m!=null) {
				assertEquals(m,m2);
			}else {
				fail("No coinciden las referencias");
			}
		} catch (ImportarException e) {
			fail("No debería lanzarse excepción");
		} catch (MatriculaException e) {
			// TODO Auto-generated catch block
			fail("No debería lanzarse excepción");
		}
		
	}
	
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarMatricula() {
		//Probamos si la titulacion que ya tenemos en la base de datos es la misma que obtenemos al llamar al método.
		Matricula m = new Matricula();
		Matricula_PK mpk=new Matricula_PK();
		mpk.setCurso_academico("2020/2021");
		mpk.setIdExp(1);
		m.setId(mpk);
		m.setEstado("Activa");
		
		m.setNum_Archivo(306000001);
		m.setTurno_Preferente("Mañana");
		m.setNuevo_Ingreso("Si");
		
		
		
		try {
			assertEquals(m,interfazMatricula.VisualizarTitulacion(mpk.get));
		} catch (MatriculaException e) {
			fail("No debería lanzarse excepción");
		}
	}
}
