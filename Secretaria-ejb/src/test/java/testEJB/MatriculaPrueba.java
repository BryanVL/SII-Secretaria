package testEJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import excepcionesEJB.ImportarException;
import excepcionesEJB.MatriculaException;
import interfacesEJB.InterfazAlumno;
import interfacesEJB.InterfazExpediente;
import interfacesEJB.InterfazMatricula;
import interfacesEJB.InterfazTitulacion;
import jpa.Matricula;
import jpa.Matricula_PK;




public class MatriculaPrueba {

	
	private static final Logger LOG = Logger.getLogger(MatriculaPrueba.class.getCanonicalName());
	private static final String MATRICULA_EJB = "java:global/classes/MatriculaImpl!ejb.MatriculaImpl";
	private static final String ALUMNO_EJB = "java:global/classes/AlumnoImpl!ejb.AlumnoImpl";
	private static final String TITULACION_EJB = "java:global/classes/TitulacionImpl!ejb.TitulacionImpl";
	private static final String EXPEDIENTE_EJB = "java:global/classes/ExpedienteImpl!ejb.ExpedienteImpl";
//	private static final String ASIGNATURA_EJB = "java:global/classes/AsignaturaImpl!ejb.AsignaturaImpl";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private InterfazMatricula interfazMatricula; 
	private InterfazExpediente interfazImportarExp;
	private InterfazAlumno interfazImportarAl;
	private InterfazTitulacion interfazImportarTit;
//	private InterfazImportar interfazImportarAsig;

		
	
	@Before
	public void setup() throws NamingException  {
		interfazMatricula = (InterfazMatricula)  SuiteTest.ctx.lookup(MATRICULA_EJB);
		interfazImportarExp = (InterfazExpediente) SuiteTest.ctx.lookup(EXPEDIENTE_EJB);
		interfazImportarTit = (InterfazTitulacion) SuiteTest.ctx.lookup(TITULACION_EJB);
		interfazImportarAl  = (InterfazAlumno) SuiteTest.ctx.lookup(ALUMNO_EJB);
//		interfazImportarAsig  = (InterfazImportar) SuiteTest.ctx.lookup(ASIGNATURA_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testImportarMatricula() {
		//Elegir desde donde realizar la importacion:
		String dir2 = "src/test/resources/alumnos.csv";
		String dir3 = "src/test/resources/Titulacion.csv";	
//		String dir4 = "src/test/resources/GII.csv";
//		String dir3 = "src/test/resources/Titulacion.xlsx";
		
		
		try {
			//HEMOS DE IMPORTAR ESTOS DATOS PREVIAMENTE A MATRICULAS:
			interfazImportarAl.Importar(dir2);
			interfazImportarTit.Importar(dir3);
			interfazImportarExp.Importar(dir2);
			
			//UNA VEZ IMPORTADOS TODOS LOS DATOS RELACIONADOS, IMPORTAMOS LAS MATRICULAS.
			interfazMatricula.Importar(dir2);
			
			interfazMatricula.VisualizarMatricula("2020/2021",104200001);
			
		} catch (ImportarException e) {
			fail("No debería lanzarse excepción(importar)");
		} catch (MatriculaException e) {
			// TODO Auto-generated catch block
			fail("No debería lanzarse excepción(matricula)");
		}
		
	}
	
	
	@Test
	@Requisitos({"RF11"})
	public void testVisualizarMatricula() {
		try {

			Matricula m = new Matricula();
			Matricula_PK mpk=new Matricula_PK();
			mpk.setCurso_academico("18/19");
			mpk.setIdExp(123);
			m.setId(mpk);
			m.setEstado("Activa");
			m.setNum_Archivo(306000001);
			m.setTurno_Preferente("Mañana");
			m.setNuevo_Ingreso("Si");
			Matricula mat = interfazMatricula.VisualizarMatricula("18/19",123);
			if(mat != null) {
				assertEquals(mat,m);
			} else {
				fail("No coinciden las referencias");
			}
		} catch (MatriculaException e) {
			fail("No debería lanzarse excepción");
		}
	}
}
