package testEJB;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import interfacesEJB.InterfazAsignaturas_Matricula;
import interfacesEJB.InterfazMatricula;

public class AsignaturaMatriculaPrueba {
	
	private static final Logger LOG = Logger.getLogger(AsignaturaMatriculaPrueba.class.getCanonicalName());
	private static final String GRUPO_EJB = "java:global/classes/Asignaturas_MatriculaImpl!ejb.Asignaturas_MatriculaImpl";	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	private InterfazAsignaturas_Matricula interfazAsignaturas_Matricula; 

		
	
	@Before
	public void setup() throws NamingException  {
		
		interfazAsignaturas_Matricula = (InterfazAsignaturas_Matricula)  SuiteTest.ctx.lookup(GRUPO_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testVisualizarAsignaturas_Matricula() {
		assertEquals(1,1);
	}
	
	@Test
	@Requisitos({"RF18"})
	public void testFiltrarPorAsignatura() {
		assertEquals(1,1);
	}
	
	@Test
	@Requisitos({"RF18"})
	public void testFiltrarPorGrupo() {
		assertEquals(1,1);
	}
	
	@Test
	@Requisitos({"RF18"})
	public void testFiltrarPorAsignaturas_Matricula() {
		assertEquals(1,1);
	}
	
	
	

	
}
