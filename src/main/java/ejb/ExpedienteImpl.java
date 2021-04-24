package ejb;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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

import com.sun.tools.hat.internal.parser.Reader;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.ExpedienteException;
import excepcionesEJB.GrupoException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazExpediente;
import interfacesEJB.InterfazImportar;
import jpa.Alumno;
import jpa.Expediente;
import jpa.Grupo;
import jpa.Titulacion;


@Stateless
@LocalBean
public class ExpedienteImpl implements InterfazImportar,InterfazExpediente{

	@PersistenceContext(name="Secretaria")
	private EntityManager em;
	
	@Override
	public void Importar(String dir) throws ImportarException {
		// TODO Auto-generated method stub
		if(dir.endsWith("xlsx")) {
			//Para el archivo xlsx de 'Datos alumnadoFAKE' sin título
			
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
		      
			try {
				Sheet sh = wb.getSheetAt(1);
				int iRow = 1;
				Row row = sh.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
				int n=1;
				while(row!=null) 
				{
		    	
					if(n>=6) {
		    		   Cell cell = row.getCell(4);  
		    		   String nExpediente = cell.getStringCellValue();
		    		   cell = row.getCell(17);  
		    		   String notaMedia = cell.getStringCellValue();
		    		   cell = row.getCell(18);  
		    		   String creditosSuperados = cell.getStringCellValue();
		           
		    		   cell = row.getCell(1); 
		    		   String dniAlumno = cell.getStringCellValue();
		    		   TypedQuery <Alumno> query = em.createQuery("Select a from Alumno a where a.DNI = :fdni", Alumno.class);
		    		   query.setParameter("fdni", dniAlumno);
		    		   List<Alumno> alumnos = query.getResultList();
		    		   if(alumnos.size() == 0) {
		    			   throw new AlumnoException();
		    		   }
               	
		    		   String codigoTitulacion = nExpediente.substring(0, 4);
		    		   Titulacion titulacionExistente = em.find(Titulacion.class, codigoTitulacion );
		    		   if(titulacionExistente == null) {
		    			   throw new TitulacionException();
		    		   }
		           
		           
		    		   Expediente e = new Expediente();  
		    		   e.setNota_media_provisional(Float.parseFloat(notaMedia));
		    		   e.setCreditos_superados(Float.parseFloat(creditosSuperados));
		    		   e.setNum_expediente(Integer.parseInt(nExpediente));
		    		   e.setActivo(true);
		    		   e.setTitulacion(titulacionExistente);
		    		   e.setAlumno(alumnos.get(0));
	            	
		    		   em.persist(e);
		    	   }
		           n++;
		           iRow++;  
		           row = sh.getRow(iRow);
		       }
			} catch (AlumnoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TitulacionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (dir.endsWith("csv")){
			 //Para el archivo csv de 'alumnos' 
			BufferedReader reader;
			try {
				reader = Files.newBufferedReader(Paths.get(dir));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);		
				int n=0;
	            for (CSVRecord csvRecord : csvParser) {
	            	
	            	if(n>=4) {
	            		
	            		String[] lista = csvRecord.get(0).split(";");
	            		String nExpediente = lista[4];
	                	String notaMedia = lista[17];
	                	String creditosSuperados = lista[18];
	                	
	                	String dniAlumno = lista[0];
	                	TypedQuery <Alumno> query = em.createQuery("Select a from Alumno a where a.DNI = :fdni", Alumno.class);
	                	query.setParameter("fdni", dniAlumno);
	                    List<Alumno> alumnos = query.getResultList();
	                    if(alumnos.size() == 0) {
	                    	throw new AlumnoException();
	                    }
	                	
	                	String codigoTitulacion = nExpediente.substring(0, 4);
	                	Titulacion titulacionExistente = em.find(Titulacion.class, codigoTitulacion );
	                	if(titulacionExistente == null) {
	                    	throw new TitulacionException();
	                    }

	                	Expediente e = new Expediente();
	                	e.setNota_media_provisional(Float.parseFloat(notaMedia));
	                	e.setCreditos_superados(Float.parseFloat(creditosSuperados));
	                	e.setNum_expediente(Integer.parseInt(nExpediente));
	                	e.setActivo(true);
	                	e.setTitulacion(titulacionExistente);
	                	e.setAlumno(alumnos.get(0));
	                
	            		em.persist(e);
	            	}
	            	n++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AlumnoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (TitulacionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
		}else {
			throw new ImportarException();
		}

	}

	@Override
	public Expediente VisualizarExpediente(Integer num_expediente) throws ExpedienteException {
		// TODO Auto-generated method stub
		Expediente expedienteExistente = em.find(Expediente.class, num_expediente);
		if (expedienteExistente == null) {
			throw new ExpedienteException();
		}
		return expedienteExistente;
	}
	
	
}
