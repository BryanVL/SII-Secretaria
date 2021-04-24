package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazTitulacion;

/*import es.uma.informatica.sii.ejb.practica.ejb.GestionLotes;
import es.uma.informatica.sii.ejb.practica.ejb.GestionProductos;
import es.uma.informatica.sii.ejb.practica.ejb.exceptions.IngredientesIncorrectosException;
import es.uma.informatica.sii.ejb.practica.ejb.exceptions.LoteExistenteException;
import es.uma.informatica.sii.ejb.practica.ejb.exceptions.LoteNoEncontradoException;
import es.uma.informatica.sii.ejb.practica.ejb.exceptions.ProductoNoEncontradoException;
import es.uma.informatica.sii.ejb.practica.ejb.exceptions.TrazabilidadException;
import es.uma.informatica.sii.ejb.practica.entidades.Ingrediente;
import es.uma.informatica.sii.ejb.practica.entidades.Lote;
import es.uma.informatica.sii.ejb.practica.entidades.Producto;*/

public class TitulacionPrueba {
	
	private static final Logger LOG = Logger.getLogger(TitulacionPrueba.class.getCanonicalName());

	private static final String TITULACION_EJB = "java:global/classes/TitulacionImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazTitulacion interfazTitulacion;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(TITULACION_EJB);
		
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	public void testImportarTitulacion() {
		String dir = "src/test/resources/Titulacion.xlsx";
		try {
			interfazImportar.Importar(dir);
			assertEquals(1,1);
		} catch (ImportarException e) {
			// TODO Auto-generated catch block
			fail("No debería lanzar excepción");
		}
		
	}
	
	@Test
	public void testVisualizarTitulacion() {
		assertEquals(1,1);
	}
	




}
