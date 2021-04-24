package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazOptativa;
import jpa.Titulacion;


public class OptativaPrueba {

	private static final Logger LOG = Logger.getLogger(OptativaPrueba.class.getCanonicalName());

	private static final String TITULACION_EJB = "java:global/classes/OptativaImpl!ejb.OptativaImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazOptativa interfazOptativa;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(TITULACION_EJB);
		interfazOptativa = (InterfazOptativa) SuiteTest.ctx.lookup(TITULACION_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	public void testImportarOptativa() {

	}
	
	@Test
	public void testVisualizarOptativa() {
		
		//Probamos si la titulacion que ya tenemos en la base de datos es la misma que obtenemos al llamar al método.

		
	}
	
	
}
