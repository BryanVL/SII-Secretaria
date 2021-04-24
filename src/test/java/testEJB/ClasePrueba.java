package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazTitulacion;
import jpa.Titulacion;

public class ClasePrueba {

	private static final Logger LOG = Logger.getLogger(ClasePrueba.class.getCanonicalName());

	private static final String CLASE_EJB = "java:global/classes/TitulacionImpl!ejb.ClaseImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazTitulacion interfazTitulacion;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(CLASE_EJB);
		
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	public void testImportarClase() {
		
	}
	
	@Test
	public void testVisualizarHorarioClase() {
		assertEquals(1,1);
	}
	
	@Test
	public void testVisualizarHorarioGrupo() {
		assertEquals(1,1);
	}
	
	@Test
	public void testVisualizarHorarioAlumnoporMatricula() {
		assertEquals(1,1);
	}
	
	
}
