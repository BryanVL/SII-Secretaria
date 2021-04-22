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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazMatricula;
import excepcionesEJB.MatriculaException;
import jpa.Asignaturas_Matricula;
import jpa.Asignaturas_Matricula_PK;
import jpa.Expediente;
import jpa.Matricula;
import jpa.Matricula_PK;


@Stateless
@LocalBean
public class MatriculaImpl implements InterfazImportar,InterfazMatricula{

	@PersistenceContext(name = "Secretaria")
	private EntityManager em;

	
	@Override
	public void Importar(String dir) throws ImportarException {
		// TODO Auto-generated method stub
		if(dir.endsWith("xlsx")) {
			//Para el archivo xlsx de 'Datos alumnadoFAKE'
			
			File f = new File(dir);
			InputStream inp = null;
			
			try {
				inp = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    Workbook wb = null;
			
		    try {
				wb = WorkbookFactory.create(inp);
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
			Sheet sh = wb.getSheetAt(0);
			int iRow = 0;
		    
			Row row = sh.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
		    int n=1; 
		    
		    while(row!=null) 
		    {
		    	
		    	if(n>=1) {
		    		
		    		//Extraigo todos los datos de una fila:
		    		Cell cell = row.getCell(0);  
		    		String Curso_academico = cell.getStringCellValue();
		    		cell = row.getCell(1);
		    		String idExp = cell.getStringCellValue();
		    		cell = row.getCell(2);  
		    		String Estado = cell.getStringCellValue();
		    		cell = row.getCell(3);  
		    		String Fecha_de_matricula = cell.getStringCellValue();
		    		cell = row.getCell(4);
		    		String Num_Archivo = cell.getStringCellValue();
		    		cell = row.getCell(5); 
		    		String Turno_Preferente = cell.getStringCellValue();
		    		cell = row.getCell(6); 
		    		String Nuevo_Ingreso = cell.getStringCellValue();
		    		cell = row.getCell(7); 
		    		String Listado_Asignaturas = cell.getStringCellValue();
		    		
		    		//Administro la clave primaria de Matricula:
		    		Matricula_PK mpk = new Matricula_PK();
		    		mpk.setCurso_academico(Curso_academico);
		    		mpk.setIdExp(Integer.parseInt(idExp));
		    		
		    		//Administro el expediente asignado a la Matricula:
		    		Expediente e = new Expediente();
		    		e.setNum_expediente(Integer.parseInt(idExp));
		    		
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
		    		
		    		//Asigno los valores de la lista de asignaturas_matricula:
		    		String[] asig = Listado_Asignaturas.split(",");
		    		List<Asignaturas_Matricula> lista = new ArrayList();
		    		Asignaturas_Matricula_PK amk = new Asignaturas_Matricula_PK();
		    		Asignaturas_Matricula am = new Asignaturas_Matricula();
		    		for(int i = 0; i < asig.length; i++) {
		    			amk.setIdM(mpk);
		    			amk.setIdAsig(Integer.parseInt(asig[i].substring(0,3)));
		    			am.setId(amk);
		    			lista.add(am);
		    		}
	            	m.setAsigMat(lista);
		    		
		    		em.persist(m);
		    	}
		    	
		        n++;
		        iRow++;  
		        row = sh.getRow(iRow);
		    }
		       
		} else if (dir.substring(dir.length()-3).equals("csv")){
			 //Para el archivo csv de 'alumnos' 
			BufferedReader reader;
			
			try {
				reader = Files.newBufferedReader(Paths.get(dir));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);		
				int n=1;
				
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
			    		Expediente e = new Expediente();
			    		e.setNum_expediente(Integer.parseInt(idExp));
			    		
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
			    		
			    		//Asigno los valores de la lista de asignaturas_matricula:
			    		String[] asig = Listado_Asignaturas.split(",");
			    		List<Asignaturas_Matricula> lista = new ArrayList();
			    		Asignaturas_Matricula_PK amk = new Asignaturas_Matricula_PK();
			    		Asignaturas_Matricula am = new Asignaturas_Matricula();
			    		for(int i = 0; i < asig.length; i++) {
			    			amk.setIdM(mpk);
			    			amk.setIdAsig(Integer.parseInt(asig[i].substring(0,3)));
			    			am.setId(amk);
			    			lista.add(am);
			    		}
		            	m.setAsigMat(lista);
			    		
			    		em.persist(m);
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
	public Matricula VisualizarMatricula(Matricula m) throws MatriculaException {
		// TODO Auto-generated method stub
		Matricula matriculaExistente = em.find(Matricula.class, m.getId());
		
		if(matriculaExistente == null) {
			throw new MatriculaException();
		}
		
		return matriculaExistente;
	}
	
}
