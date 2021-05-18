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
import interfacesEJB.InterfazExpediente;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazMatricula;
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
	
	private InterfazImportar interfazImportar;
	private InterfazMatricula interfazMatricula; 
	private InterfazImportar interfazImportarExp;
	private InterfazImportar interfazImportarAl;
	private InterfazImportar interfazImportarTit;
//	private InterfazImportar interfazImportarAsig;

		
	
	@Before
	public void setup() throws NamingException  {
		interfazImportar = (InterfazImportar) SuiteTest.ctx.lookup(MATRICULA_EJB);
		interfazMatricula = (InterfazMatricula)  SuiteTest.ctx.lookup(MATRICULA_EJB);
		interfazImportarExp = (InterfazImportar) SuiteTest.ctx.lookup(EXPEDIENTE_EJB);
		interfazImportarTit = (InterfazImportar) SuiteTest.ctx.lookup(TITULACION_EJB);
		interfazImportarAl  = (InterfazImportar) SuiteTest.ctx.lookup(ALUMNO_EJB);
//		interfazImportarAsig  = (InterfazImportar) SuiteTest.ctx.lookup(ASIGNATURA_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	@Requisitos({"RF7"})
	public void testImportarMatricula() {
		//Elegir desde donde realizar la importacion:
//		String dir = "src/test/resources/Matriculas.csv";
		String dir2 = "src/test/resources/alumnos.csv";
		String dir3 = "src/test/resources/Titulacion.csv";	
//		String dir4 = "src/test/resources/GII.csv";
		String dir = "src/test/resources/Matriculas.xlsx";
//		String dir2 = "src/test/resources/alumnos.xlsx";
//		String dir3 = "src/test/resources/Titulacion.xlsx";
		
		
		try {
			//HEMOS DE IMPORTAR ESTOS DATOS PREVIAMENTE A MATRICULAS:
			interfazImportarAl.Importar(dir2);
			interfazImportarTit.Importar(dir3);
			interfazImportarExp.Importar(dir2);
//			interfazImportarAsig.Importar(dir4);
			
			//UNA VEZ IMPORTADOS TODOS LOS DATOS RELACIONADOS, IMPORTAMOS LAS MATRICULAS.
			interfazImportar.Importar(dir);
			
			Matricula m = new Matricula();
			
			Matricula_PK mpk=new Matricula_PK();
			mpk.setCurso_academico("2020/2021");
			mpk.setIdExp(104200001);
			m.setId(mpk);
			interfazMatricula.VisualizarMatricula(m);
			
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
			Matricula mat = interfazMatricula.VisualizarMatricula(m);
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
