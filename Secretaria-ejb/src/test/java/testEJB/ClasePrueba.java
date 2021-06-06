package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.AlumnoException;
import excepcionesEJB.ClaseException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.MatriculaException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazAsignatura;
import interfacesEJB.InterfazHorarios;
import interfacesEJB.InterfazTitulacion;
import jpa.Alumno;
import jpa.Asignatura;
import jpa.Clase;
import jpa.Clase_PK;
import jpa.Grupo;
import jpa.Matricula;
import jpa.Matricula_PK;
import jpa.Titulacion;

public class ClasePrueba {

	private static final Logger LOG = Logger.getLogger(ClasePrueba.class.getCanonicalName());

	private static final String CLASE_EJB = "java:global/classes/ClaseImpl!ejb.ClaseImpl";	
	private static final String ASIGNATURA_EJB = "java:global/classes/AsignaturaImpl!ejb.AsignaturaImpl";	
	private static final String TITULACION_EJB = "java:global/classes/TitulacionImpl!ejb.TitulacionImpl";
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazAsignatura interfazImportar2;
	private InterfazTitulacion interfazImportar3;
	private InterfazHorarios interfazClases;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazClases = (InterfazHorarios) SuiteTest.ctx.lookup(CLASE_EJB);
		interfazImportar2 = (InterfazAsignatura) SuiteTest.ctx.lookup(ASIGNATURA_EJB);
		interfazImportar3 = (InterfazTitulacion) SuiteTest.ctx.lookup(TITULACION_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testImportarClase() {
		try {
			//Elegir desde donde realizar cada importacion:
			interfazImportar3.Importar("src/test/resources/Titulacion.csv");
			interfazImportar2.Importar("src/test/resources/GII.csv");
			interfazClases.Importar("src/test/resources/horarios.csv");
//			interfazImportar3.Importar("src/test/resources/Titulacion.xlsx");
//			interfazImportar2.Importar("src/test/resources/Oferta asignaturas.xlsx");
//			interfazImportar.Importar("src/test/resources/horarios.xlsx");
		} catch (ImportarException e) {
			fail("Error al importar");
		}
		
		Grupo g = new Grupo();
		g.setID(1l);

		
		List<Clase> cl = new ArrayList<Clase>();
		try {
			cl = interfazClases.VisualizarHorarios(g);
			
			/*for(Clase c: cl) {
				System.out.println("\n\n");
				System.out.println(c.toString());
				System.out.println("\n\n");
			}*/
			
		} catch (ClaseException e) {
			fail("No se encontro la clase");
		}
		
		try {
			List<Clase> lista = interfazClases.visTodasClase();
			if(lista != null) {
				assertTrue(!lista.isEmpty());
			} else {
				fail("No se encontro la clase");
			}
		} catch (ClaseException e) {
			fail("No deberia lanzarse esta excepcion");
		}
		
	}
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarHorarioClase() {
		
		Titulacion tit = new Titulacion();
		tit.setCodigo(1234);
		tit.setNombre("Informatica");
		tit.setCreditos( 240f );
		
		Asignatura asignatura = new Asignatura();
		asignatura.setReferencia(12345);
		asignatura.setCodigo(900);
		asignatura.setCreditos_total((float)6);
		asignatura.setOfertada("Si");
		asignatura.setNombre("Pruebas con Junit");
		asignatura.setTitulacion(tit);
		
		Clase cAsig = new Clase();
		Clase_PK cAsigPK = new Clase_PK();
		cAsigPK.setDia(new Date("24/09/2018"));
		cAsigPK.setHora_inicio(new Time(10,45,0));
		cAsigPK.setIdG(1l);
		cAsig.setAsignatura(asignatura);
		cAsig.setId(cAsigPK);
		
		Grupo grupoA = new Grupo();
		grupoA.setID(1l);
		grupoA.setCurso(1);
		grupoA.setLetra("A");
		grupoA.setTurno_Mañana_Tarde("Mañana");
		grupoA.setIngles("Sí");
		grupoA.setPlazas(50);
		grupoA.setPlazasDisponibles(50);
		grupoA.setTitulacion(tit);
		
		cAsig.setGrupo(grupoA);
		try {
		Clase cl = interfazClases.VisualizarHorarios(cAsig);
		
			if(cl != null) {
				assertEquals(cAsig,cl);
			} else {
				fail("No coinciden las referencias");
			}
		} catch (ClaseException e) {
			fail("No se encontro la clase");
		}
	}
	
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarHorarioGrupo() {
		
		Grupo grupo = new Grupo();
		grupo.setID(1l);
		List<Clase> lista = new ArrayList<>();
		
		try {
			lista = interfazClases.VisualizarHorarios(grupo);
			assertEquals(lista.get(0).getId().getHora_inicio(), new Time(10,45,0));
			assertEquals(lista.get(0).getId().getDia(), new Date("24/09/2018"));
		} catch (ClaseException e) {
			fail("No se encontro el grupo");
		}
		
		
	}
	
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarHorarioAlumnoporMatricula() {
		
		Alumno alumno = new Alumno();
		alumno.setID(1234L);
			
		Matricula_PK brk = new Matricula_PK();
		brk.setCurso_academico("18/19");
		brk.setIdExp(123);
		Matricula matricula = new Matricula();
		matricula.setId(brk);
		
		HashMap<Asignatura, List<Clase>> lista = new HashMap<>();
		
		try {
			lista = interfazClases.VisualizarHorarios(alumno,matricula);
		} catch (ClaseException | AlumnoException | MatriculaException e) {
			fail("Ninguna clase encontrada");
		}
		if(lista != null) {
			assertTrue(!lista.isEmpty());
		}else {
			fail("No se ejecuta correctamente");
		}
		
	}
	
	
}
