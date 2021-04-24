package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import excepcionesEJB.AsignaturaException;
import excepcionesEJB.GrupoException;
import excepcionesEJB.MatriculaException;
import interfacesEJB.InterfazGrupo;
import interfacesEJB.InterfazTitulacion;
import jpa.Asignatura;
import jpa.Grupo;
import jpa.Matricula;
import jpa.Matricula_PK;
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
		
		assertTrue(g.getID()== 1l);
	}
	
	
	@Test
	public void testBuscarGrupoLetra() {
		Integer curso = 1;
		String letra = "A";
		List<Grupo> g = new ArrayList<Grupo>();

		g = interfazGrupo.buscarPorCursoLetra(curso, letra);
		
		boolean probando = true;
		for(Grupo grupo: g) {
			if(grupo.getCurso()!=1 || !grupo.getLetra().equals("A")) {
				probando = false;
			}
		}
		assertTrue(probando);
	}
	
	
	@Test
	public void testAsignarGrupo() {
		Matricula matricula = new Matricula();
		Matricula_PK mPK = new Matricula_PK();
		mPK.setCurso_academico("18/19");
		mPK.setIdExp(123);
		matricula.setId(mPK);
		matricula.setEstado("Activo");
		matricula.setFecha_de_matricula(new Date("12/09/2018"));
		Grupo grupo = new Grupo();
		grupo.setID(1l);
		Asignatura asignatura = new Asignatura();
		asignatura.setReferencia(12345);
		
		try {
			interfazGrupo.asignarGrupo(matricula, grupo, asignatura);
		} catch (MatriculaException | GrupoException | AsignaturaException e) {
			fail("Alguno de los argumentos no ha sido encontrado (matricula, grupo o asignatura)");
		}
		
		try {
			grupo=interfazGrupo.Leer(grupo);
		} catch (GrupoException e) {
			fail("Grupo no encontrado");
		}
		
		assertTrue(grupo.getID() == 1l);
	}
	
	
}
