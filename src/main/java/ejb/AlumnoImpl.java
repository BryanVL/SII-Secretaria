package ejb;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.logging.Logger;

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

import excepcionesEJB.AlumnoException;
import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazAlumno;
import interfacesEJB.InterfazImportar;
import jpa.Alumno;
import jpa.Expediente;




@Stateless
@LocalBean
public class AlumnoImpl implements InterfazImportar,InterfazAlumno{
    @PersistenceContext(unitName = "Secretaria")
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
		    	
		    	if(n>=5) {
		    		Cell cell = row.getCell(4);  
		    		String nExpediente = cell.getStringCellValue();
		    		cell = row.getCell(17);  
		    		String notaMedia = cell.getStringCellValue();
		    		cell = row.getCell(18);  
		    		String creditosSuperados = cell.getStringCellValue();
		    		Expediente e = new Expediente();
		               
		    		e.setNota_media_provisional(Float.parseFloat(notaMedia));
		    		e.setCreditos_superados(Float.parseFloat(creditosSuperados));
		    		e.setNum_expediente(Integer.parseInt(nExpediente));
		    		e.setActivo(true);
	            	
		    		em.persist(e);
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
	            	if(n>=5) {
	            		
	            		String nExpediente = csvRecord.get(4);
	            		String notaMedia = csvRecord.get(17);
	            		String creditosSuperados = csvRecord.get(18);
	            		Expediente e = new Expediente();
	               
	            		e.setNota_media_provisional(Float.parseFloat(notaMedia));
	            		e.setCreditos_superados(Float.parseFloat(creditosSuperados));
	            		e.setNum_expediente(Integer.parseInt(nExpediente));
	            		e.setActivo(true);
	            		em.persist(e);
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
	public void validarAcceso(Alumno a) throws AlumnoException {
		// TODO Auto-generated method stub
    	Alumno alumno = em.find(Alumno.class, a.getID());
    	
    	if(alumno==null) {
    		throw new AlumnoException();
    	}
    	
	}
	
	
	@Override
	public Alumno VisualizarAlumno(Alumno a) throws AlumnoException {
		// TODO Auto-generated method stub
		Alumno alumnoExistente = em.find(Alumno.class, a.getID() );
		
		if (alumnoExistente == null) {
			throw new AlumnoException();
		}
		
		return alumnoExistente;
	}
	
}
