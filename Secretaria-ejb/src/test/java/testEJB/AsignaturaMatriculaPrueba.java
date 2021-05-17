package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.AlumnoException;
import excepcionesEJB.GrupoException;
import interfacesEJB.InterfazAlumno;
import interfacesEJB.InterfazAsignaturas_Matricula;
import interfacesEJB.InterfazMatricula;
import jpa.Alumno;
import jpa.Grupo;
import jpa.Titulacion;

public class AsignaturaMatriculaPrueba {
	
	private static final Logger LOG = Logger.getLogger(AsignaturaMatriculaPrueba.class.getCanonicalName());
	private static final String AM_EJB = "java:global/classes/Asignaturas_MatriculaImpl!ejb.Asignaturas_MatriculaImpl";
	private static final String ALUMNO_EJB = "java:global/classes/AlumnoImpl!ejb.AlumnoImpl";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	private InterfazAsignaturas_Matricula interfazAsignaturas_Matricula; 
	private InterfazAlumno interfazAlumno;
		
	
	@Before
	public void setup() throws NamingException  {
		
		interfazAsignaturas_Matricula = (InterfazAsignaturas_Matricula)  SuiteTest.ctx.lookup(AM_EJB);
		interfazAlumno = (InterfazAlumno)  SuiteTest.ctx.lookup(ALUMNO_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testVisualizarAlumnosPorGrupo() {
		try {
		Titulacion tit = new Titulacion();
		tit.setCodigo(1234);
		tit.setNombre("Informatica");
		tit.setCreditos( 240f );
		Grupo grupoA = new Grupo();
		grupoA.setID(1l);
		grupoA.setCurso(1);
		grupoA.setLetra("A");
		grupoA.setTurno_Mañana_Tarde("Mañana");
		grupoA.setIngles("Sí");
		grupoA.setPlazas(50);
		grupoA.setPlazasDisponibles(50);
		grupoA.setTitulacion(tit);
		List<Alumno> lista = interfazAsignaturas_Matricula.VisualizarAlumnosPorGrupo(grupoA);
		boolean seCumple = true;
		for(Alumno a : lista) {
			 seCumple = seCumple && interfazAlumno.VisualizarAlumno(a.getDNI()).equals(a);
		}
		if(seCumple) {
			assertEquals(1,1);
		} else {
			fail("No se encuentran los alumnos de forma correcta.");
		}
		} catch (GrupoException e){
			fail("No deberia lanzar excepcion");
		} catch (AlumnoException e) {
			fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	@Requisitos({"RF18"})
	@Ignore
	public void testFiltrarPorAsignatura() {
		assertEquals(1,1);
	}
	
	@Test
	@Requisitos({"RF18"})
	@Ignore
	public void testFiltrarPorGrupo() {
		assertEquals(1,1);
	}
	
	@Test
	@Requisitos({"RF18"})
	@Ignore
	public void testFiltrarPorAsignaturas_Matricula() {
		assertEquals(1,1);
	}
	
	
	

	
}
