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
import interfacesEJB.InterfazEncuesta;
import interfacesEJB.InterfazTitulacion;
import jpa.Encuesta;

public class EncuestaPrueba {

	private static final Logger LOG = Logger.getLogger(TitulacionPrueba.class.getCanonicalName());

	private static final String ENCUESTA_EJB = "java:global/classes/EncuestaImpl!interfacesEJB.InterfazEncuesta";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazEncuesta interfazEncuesta;

		
	
	@Before
	public void setup() throws NamingException  {
		interfazEncuesta = (InterfazEncuesta) SuiteTest.ctx.lookup(ENCUESTA_EJB);
		
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF8"})
	public void testPedirIngles() {
		Encuesta encuesta = new Encuesta();
		encuesta.setAsignatura_ingles("");
		encuesta.setFecha_envio(new Date("12/09/2019"));
		assertEquals(interfazEncuesta.conseguirAsignaturasIngles(encuesta), encuesta.getAsignatura_ingles().toString());
		
	}

	
}
