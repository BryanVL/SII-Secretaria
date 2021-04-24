package testEJB;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import interfacesEJB.InterfazAsignatura;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazOptativa;

public class AsignaturaPrueba {

	private static final Logger LOG = Logger.getLogger(OptativaPrueba.class.getCanonicalName());

	private static final String TITULACION_EJB = "java:global/classes/AsignaturaImpl!ejb.AsignaturaImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazAsignatura interfazAsignatura;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(TITULACION_EJB);
		interfazAsignatura = (InterfazAsignatura) SuiteTest.ctx.lookup(TITULACION_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testImportarAsignatura() {

	}
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarAsignatura() {
		
		

		
	}
	
}
