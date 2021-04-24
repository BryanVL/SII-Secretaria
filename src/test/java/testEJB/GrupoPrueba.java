package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Logger;
import javax.naming.NamingException;
import org.junit.Before;
import org.junit.Test;

import excepcionesEJB.GrupoException;
import interfacesEJB.InterfazGrupo;
import interfacesEJB.InterfazTitulacion;
import jpa.Grupo;
import jpa.Titulacion;


public class GrupoPrueba {
	
	private static final Logger LOG = Logger.getLogger(TitulacionPrueba.class.getCanonicalName());
	private static final String GRUPO_EJB = "java:global/classes/GrupoImpl!ejb.GrupoImpl";	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private InterfazGrupo interfazGrupo;
	private InterfazTitulacion interfazTitulacion; 

		
	
	@Before
	public void setup() throws NamingException  {
		interfazGrupo = (InterfazGrupo) SuiteTest.ctx.lookup(GRUPO_EJB);
		
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	public void testCrearGrupo() {
		Grupo grupoB = new Grupo();
		Titulacion tit = new Titulacion();
		tit.setCodigo(1234);
		tit.setNombre("Informatica");
		tit.setCreditos( 240f );
		
		grupoB.setCurso(2);
		grupoB.setLetra("A");
		grupoB.setTurno_Mañana_Tarde("Mañana");
		grupoB.setIngles("No");
		grupoB.setPlazas(50);
		grupoB.setPlazasDisponibles(50);
		grupoB.setTitulacion(tit);
		try {
			interfazGrupo.Crear(grupoB);
		} catch (GrupoException e) {
			fail("El grupo ya existia");
		}
	}
	
	@Test
	public void testLeerGrupo() {
	
	}
	
	@Test
	public void testBorrarGrupo() {
	
	}
	
	@Test
	public void testActualizarGrupo() {
	
	}
}
