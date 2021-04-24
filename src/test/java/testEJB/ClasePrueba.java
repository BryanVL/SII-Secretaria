package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazTitulacion;
import jpa.Titulacion;

public class ClasePrueba {

	private static final Logger LOG = Logger.getLogger(ClasePrueba.class.getCanonicalName());

	private static final String CLASE_EJB = "java:global/classes/ClaseImpl!ejb.ClaseImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazTitulacion interfazTitulacion;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(CLASE_EJB);
		
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testImportarClase() {
		
	}
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarHorarioClase() {
		assertEquals(1,1);
	}
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarHorarioGrupo() {
		assertEquals(1,1);
	}
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarHorarioAlumnoporMatricula() {
		assertEquals(1,1);
	}
	
	
}
