package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.logging.Logger;
import javax.naming.NamingException;
import org.junit.Before;
import org.junit.Ignore;
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
	@Ignore
	@Test
	public void testCrearGrupo() {
		Grupo grupoB = new Grupo();
		Titulacion tit = new Titulacion();
		tit.setCodigo(1234);
		tit.setNombre("Informatica");
		tit.setCreditos( 240f );
		
		grupoB.setID(35l);
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
	@Ignore
	@Test
	public void testLeerGrupo() {
		Grupo grupo = new Grupo();
		Grupo nuevo = new Grupo();
		grupo.setID(1l);
		try {
			nuevo = interfazGrupo.Leer(grupo);
		} catch (GrupoException e) {
			fail("no se encontro el objeto");
		}
		
		assertEquals(nuevo, grupo);
	}
	@Ignore
	@Test
	public void testBorrarGrupo() {
		Grupo grupo = new Grupo();
		grupo.setID(1l);
		
		try {
			interfazGrupo.Borrar(grupo);
		} catch (GrupoException e) {
			fail("no se encontro el objeto");
		}
		
		try {
			interfazGrupo.Leer(grupo);
		} catch (GrupoException e) {
			//No lo encuentra, como debe ser
		}
		
	}
	
	@Ignore
	@Test
	public void testActualizarGrupo() {
		Grupo g = new Grupo();
		Grupo grupo = new Grupo();
		g.setID(1l);
		try {
			grupo = interfazGrupo.Leer(g);
		} catch (GrupoException e) {
			fail("no se encontro el objeto");
		}
		
		grupo.setIngles("No");
		try {
			interfazGrupo.Actualizar(grupo);
		} catch (GrupoException e) {
			fail("no se encontro el objeto");
		}
		
		try {
			assertEquals(grupo, interfazGrupo.Leer(g));
		} catch (GrupoException e) {
			fail("no se encontro el objeto");
		}
	}
	
	@Ignore
	@Test
	public void testComprobarPlazas() {
		Grupo g = new Grupo();
		g.setID(1l);
		Grupo grupo = new Grupo();
		try {
			grupo=interfazGrupo.Leer(g);
		} catch (GrupoException e) {
			fail("no se encontro el objeto");
		}
		
		try {
			interfazGrupo.ComprobarPlazas(grupo);
		} catch (GrupoException e) {
			fail("no se encontro el objeto");
		}
	}
	
	
	@Test
	public void testBuscarGrupoLetraTitulacion() {
		Integer titulacion = 1234;
		Integer curso = 1;
		String letra = "A";
		Grupo g = new Grupo();
		try {
			g = interfazGrupo.buscarPorCursoLetra(titulacion, curso, letra);
		} catch (GrupoException e) {
			fail("no se encontro el objeto");
		}
		
		//assertTrue(g.getID()== 1l);
	}
	
	@Ignore
	@Test
	public void testBuscarGrupoLetra() {
		
	}
	
	@Ignore
	@Test
	public void testAsignarGrupo() {
		
	}
	
	
}
