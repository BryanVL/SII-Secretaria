package ejb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.ExpedienteException;
import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazMatricula;
import excepcionesEJB.MatriculaException;
import excepcionesEJB.TitulacionException;
import jpa.Alumno;
import jpa.Asignatura;
import jpa.Asignaturas_Matricula;
import jpa.Asignaturas_Matricula_PK;
import jpa.Expediente;
import jpa.Grupo;
import jpa.Matricula;
import jpa.Matricula_PK;


@Stateless
@LocalBean
public class MatriculaImpl implements InterfazMatricula{

	@PersistenceContext(name = "Secretaria")
	private EntityManager em;

	
	@Override
	public void Importar(String dir) throws ImportarException {
		// TODO Auto-generated method stub
		if(dir.endsWith("xlsx")) {
			//Para el archivo xlsx de 'Datos alumnadoFAKE'
			
			FileInputStream inp = null;
			
			try {
				 inp = new FileInputStream(new File(dir));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			XSSFWorkbook workbook = null;
			
			try {
				workbook = new XSSFWorkbook(inp);
			} catch(IOException e) {
				e.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			int contF = 1;
			Row fila = sheet.getRow(contF);
			try {
				while(fila != null) {
					if(contF >= 1 && (fila.getCell(0) !=null)) {
					
			    		String Curso_academico = fila.getCell(0).getStringCellValue();
			    		String idExp = fila.getCell(1).getStringCellValue();
			    		String Estado = fila.getCell(2).getStringCellValue();
			    		Date Fecha_de_matricula = fila.getCell(3).getDateCellValue();
			    		Integer Num_Archivo = (int) fila.getCell(4).getNumericCellValue();
			    		String Turno_Preferente = fila.getCell(5).getStringCellValue();
			    		String Nuevo_Ingreso = fila.getCell(6).getStringCellValue();
			    		String Listado_Asignaturas = fila.getCell(7).getStringCellValue();
			    		
			    		//Administro la clave primaria de Matricula:
			    		Matricula_PK mpk = new Matricula_PK();
			    		mpk.setCurso_academico(Curso_academico);
			    		mpk.setIdExp(Integer.parseInt(idExp));
			    		
			    		//Administro el expediente asignado a la Matricula:
			    		TypedQuery<Expediente> query = em.createQuery("Select e from Expediente e where e.Num_expediente = :ide", Expediente.class);
			            query.setParameter("ide", Integer.parseInt(idExp));
			    		Expediente e = query.getSingleResult();
			    		if(e == null) {
			    			throw new ExpedienteException();
			    		}
			    		
			    		//Administro los datos de la fila total:
			    		Matricula m = new Matricula();
			    		m.setId(mpk);
			    		m.setEstado(Estado);
		            	m.setFecha_de_matricula(Fecha_de_matricula);
			    		m.setNum_Archivo(Num_Archivo);
			    		m.setTurno_Preferente(Turno_Preferente);
			    		m.setNuevo_Ingreso(Nuevo_Ingreso);
			    		m.setListado_Asignaturas(Listado_Asignaturas);
			    		m.setExpediente(e);
			    		
			    		em.persist(m);
			    	}
					contF++;
					fila = sheet.getRow(contF);
				}
		    	
			} catch(ExpedienteException e) {
				e.printStackTrace();
			}
		    
		       
		} else if (dir.substring(dir.length()-3).equals("csv")){
			 //Para el archivo csv de 'alumnos' 
			BufferedReader reader;
			
			try {
				reader = Files.newBufferedReader(Paths.get(dir));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.newFormat(';'));		
				int n=0;
				
	            for (CSVRecord csvRecord : csvParser) {
	            	if(n>=1) {
	            		
			    		String Curso_academico = csvRecord.get(0);
			    		String idExp = csvRecord.get(1);
			    		String Estado = csvRecord.get(2);
			    		String Fecha_de_matricula = csvRecord.get(3);
			    		String Num_Archivo = csvRecord.get(4);
			    		String Turno_Preferente = csvRecord.get(5);
			    		String Nuevo_Ingreso = csvRecord.get(6);
			    		String Listado_Asignaturas = csvRecord.get(7);
			    		
			    		//Administro la clave primaria de Matricula
			    		Matricula_PK mpk = new Matricula_PK();
			    		mpk.setCurso_academico(Curso_academico);
			    		mpk.setIdExp(Integer.parseInt(idExp));
			    		
			    		//Administro el expediente asignado a la Matricula:
			    		TypedQuery<Expediente> query = em.createQuery("Select e from Expediente e where e.Num_expediente = :ide", Expediente.class);
			            query.setParameter("ide", Integer.parseInt(idExp));
			    		Expediente e = query.getSingleResult();
			            
			    		//Administro los datos de la fila total:
			    		Matricula m = new Matricula();
			    		m.setId(mpk);
			    		m.setEstado(Estado);
		            	m.setFecha_de_matricula(new Date(Fecha_de_matricula));
			    		m.setNum_Archivo(Integer.parseInt(Num_Archivo));
			    		m.setTurno_Preferente(Turno_Preferente);
			    		m.setNuevo_Ingreso(Nuevo_Ingreso);
			    		m.setListado_Asignaturas(Listado_Asignaturas);
			    		m.setExpediente(e);
			    		em.persist(m);
			    		
//			    		//Asigno los valores de la lista de asignaturas_matricula:
//			    		String[] asig = Listado_Asignaturas.split(",");
//			    		List<Asignaturas_Matricula> lista = new ArrayList();
//			    		Asignaturas_Matricula_PK amk = new Asignaturas_Matricula_PK();
//			    		Asignaturas_Matricula am = new Asignaturas_Matricula();
//			    		for(int i = 0; i < asig.length; i++) {
//			    			amk.setIdM(mpk);
//			    			amk.setIdAsig(Integer.parseInt(asig[i].substring(0,3)));
//			    			am.setId(amk);
//			    			TypedQuery<Asignatura> query2 = em.createQuery("Select a from Asignatura a where a.Codigo = :codi", Asignatura.class);
//				            query2.setParameter("codi",Integer.parseInt(asig[i].substring(0,3)));
//				    		Asignatura asignatura = query2.getSingleResult();
//				    		am.setAsignatura(asignatura);
//			    			am.setMatricula(m);
//				    		lista.add(am);
//			    			em.persist(am);
//			    		}
//		            	m.setAsigMat(lista);
//		            	em.merge(m);
//			    		
			    		
			    	}
	            	n++;
				}
	            
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
		} else {
			throw new ImportarException();
		}
		
	}

	@Override
	public Matricula VisualizarMatricula(String curso, Integer idexp) throws MatriculaException {
		// TODO Auto-generated method stub
		Matricula_PK mpk = new Matricula_PK();
		mpk.setCurso_academico(curso);
		mpk.setIdExp(idexp);
		Matricula matriculaExistente = em.find(Matricula.class, mpk);
		
		if(matriculaExistente == null) {
			throw new MatriculaException();
		}
		
		return matriculaExistente;
	}
	
	@Override
	public List<Matricula> mostrarDatosAdmin() throws MatriculaException{
		
		TypedQuery<Matricula> query = em.createQuery("SELECT m FROM Matricula m",Matricula.class);
		List<Matricula> matriculas = query.getResultList();
		if(matriculas == null || matriculas.size() == 0) {
			throw new MatriculaException("No se ha encontrado matriculas");
		}
		
		return matriculas;
	}
	
	@Override
	public void borrarMatriculas() throws MatriculaException {
		for(Matricula m : mostrarDatosAdmin()) {
			em.remove(m);
		}
	}
	
}
