package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.AlumnoException;
import excepcionesEJB.AsignaturaException;
import excepcionesEJB.GrupoException;
import excepcionesEJB.MatriculaException;
import interfacesEJB.InterfazAlumno;
import interfacesEJB.InterfazAsignaturas_Matricula;
import jpa.Alumno;
import jpa.Asignaturas_Matricula;
import jpa.Grupo;

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
		
		Grupo grupoA = new Grupo();
		grupoA.setID(1l);
		List<Alumno> lista = interfazAsignaturas_Matricula.VisualizarAlumnosPorGrupo(grupoA);
		boolean seCumple = false;
		if(lista != null) {
			seCumple = true;
			for(Alumno a : lista) {
				seCumple = seCumple && interfazAlumno.VisualizarAlumno(a.getDNI()).equals(a);
			}
		} else {
			fail("No se encuentran datos");
		}
		
		
		if(seCumple) {
			assertTrue(seCumple);
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
	public void testFiltrarPorGrupo() {
		try {
			Grupo grupoA = new Grupo();
			grupoA.setID(1l);
			List<Asignaturas_Matricula> listaAsig = interfazAsignaturas_Matricula.AplicarFiltros(grupoA);
			boolean seCumple = false;
		
			if(listaAsig != null) {
				seCumple = true;
				for(int i = 0; i < listaAsig.size();i++) {
					seCumple = seCumple && (listaAsig.get(i) != null);
				}
			} else {
				fail("No se han encontrado datos");
			}
		
			if(seCumple) {
				assertTrue(seCumple);
			} else {
				fail("No se han encontrado asignaturas_matricula");
			}
		
		} catch (GrupoException e) {
			fail("No deberia lanzar excepcion");
		}
		
	}
	
	@Test
	@Requisitos({"RF18"})
	public void testFiltrarPorAsignatura() {
		try {
			Integer refer = 12345;
			List<Asignaturas_Matricula> listaAsig = interfazAsignaturas_Matricula.AplicarFiltros(refer);
			boolean seCumple = false;
			
			if(listaAsig != null) {
				seCumple = true;
				for(int i = 0; i < listaAsig.size();i++) {
					seCumple = seCumple && (listaAsig.get(i) != null);
				}
			} else {
				fail("No se han encontrado datos");
			}
			
			if(seCumple) {
				assertTrue(seCumple);
			} else {
				fail("No se han encontrado asignaturas_matricula");
			}
			
		} catch(AsignaturaException e) {
			fail("No deberia lanzarse excepcion");
		}
	}
	
	@Test
	@Requisitos({"RF18"})
	public void testFiltrarPorAsignaturas_Matricula() {
		try {
			String curso = "18/19";
			Integer idExp = 123;
			List<Asignaturas_Matricula> listaAsig = interfazAsignaturas_Matricula.AplicarFiltros(curso,idExp);
			boolean seCumple = false;
			
			if(listaAsig != null) {
				seCumple = true;
				for(int i = 0; i < listaAsig.size();i++) {
					seCumple = seCumple && (listaAsig.get(i) != null);
				}
			} else {
				fail("No se han encontrado datos");
			}
			
			if(seCumple) {
				assertTrue(seCumple);
			} else {
				fail("No se han encontrado asignaturas_matricula");
			}
			
		} catch(MatriculaException e) {
			fail("No deberia lanzarse excepcion");
		}
	}
	
	
	

	
}
