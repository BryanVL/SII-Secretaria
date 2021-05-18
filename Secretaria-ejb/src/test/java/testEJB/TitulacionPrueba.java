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

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazTitulacion;
import jpa.Titulacion;

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

	private static final String TITULACION_EJB = "java:global/classes/TitulacionImpl!ejb.TitulacionImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazTitulacion interfazTitulacion;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(TITULACION_EJB);
		interfazTitulacion = (InterfazTitulacion) SuiteTest.ctx.lookup(TITULACION_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testImportarTitulacion() {
		//Elegir desde donde realizar la importacion:
		String dir = "src/test/resources/Titulacion.csv";
//		String dir = "src/test/resources/Titulacion.xlsx";
		
		try {
			interfazImportar.Importar(dir);
			
			Titulacion titulacion = new Titulacion();
			titulacion.setCodigo(1041);
			titulacion.setNombre("Grado en Ingeniería Informática");
			titulacion.setCreditos(240f);
			
			Titulacion t = interfazTitulacion.VisualizarTitulacion(1041);
			
			if(t!=null) {
				assertEquals(t,titulacion);
			}else {
				fail("No coinciden las referencias");
			}
		} catch (ImportarException e) {
			fail("No debería lanzarse excepción");
		} catch (TitulacionException e) {
			// TODO Auto-generated catch block
			fail("No debería lanzarse excepción");
		}
	}
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarTitulacion() {
		
		//Probamos si la titulacion que ya tenemos en la base de datos es la misma que obtenemos al llamar al método.
		Titulacion t = new Titulacion();
		t.setCodigo(1234);
		t.setNombre("Informatica");
		t.setCreditos( 240f );
		
		try {
			assertEquals(t,interfazTitulacion.VisualizarTitulacion(1234));
		} catch (TitulacionException e) {
			fail("No debería lanzarse excepción");
		}
		
	}
	
}
	
	
	





