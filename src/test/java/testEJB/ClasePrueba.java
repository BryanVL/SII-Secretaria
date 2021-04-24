package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Time;
import java.util.Date;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.ClaseException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazHorarios;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazTitulacion;
import jpa.Asignatura;
import jpa.Clase;
import jpa.Clase_PK;
import jpa.Grupo;
import jpa.Titulacion;

public class ClasePrueba {

	private static final Logger LOG = Logger.getLogger(ClasePrueba.class.getCanonicalName());

	private static final String CLASE_EJB = "java:global/classes/ClaseImpl!ejb.ClaseImpl";	
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	
	
	private InterfazImportar interfazImportar;
	private InterfazHorarios interfazClases;
	
	
	
	@Before
	public void setup() throws NamingException  {
		interfazClases = (InterfazHorarios) SuiteTest.ctx.lookup(CLASE_EJB);
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(CLASE_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testImportarClase() {
		try {
			interfazImportar.Importar("/Secretaria/src/test/resources/horarios.csv");
		} catch (ImportarException e) {
			fail("Error al importar");
		}
		
		/*Clase c = new Clase();
		Clase_PK cpk = new Clase_PK();
		cpk.setDia(new Date("25/05/2021"));
		cpk.setHora_inicio(new Time(10,45,0));
		cpk.setIdG(1l);
		c.setId(cpk);
		Asignatura asig = new Asignatura();
		asig.setReferencia(50658);
		c.setAsignatura(asig);
		Grupo g = new Grupo();
		g.setID(1l);
		c.setGrupo(g);
		
		Clase cl = new Clase();
		try {
			cl = interfazClases.VisualizarHorarios(c);
		} catch (ClaseException e) {
			fail("No se encontro la clase");
		}
		*/
		
		
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
		
		Clase cl = new Clase();
		try {
			cl = interfazClases.VisualizarHorarios(cAsig);
		} catch (ClaseException e) {
			fail("No se encontro la clase");
		}
	}
	
	@Ignore
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarHorarioGrupo() {
		/*
		Titulacion tit = new Titulacion();
		tit.setCodigo(1234);
		tit.setNombre("Informatica");
		tit.setCreditos( 240f );
		
		Grupo grupo = new Grupo();
		grupo.setID(1l);
		grupo.setCurso(1);
		grupo.setLetra("A");
		grupo.setTurno_Mañana_Tarde("Mañana");
		grupo.setIngles("Sí");
		grupo.setPlazas(50);
		grupo.setPlazasDisponibles(50);
		grupo.setTitulacion(tit);
		
		try {
			interfazClases.VisualizarHorarios(grupo);
		} catch (ClaseException e) {
			fail("No se encontro el grupo");
		}
		*/
		
	}
	
	@Ignore
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarHorarioAlumnoporMatricula() {
		
	}
	
	
}
