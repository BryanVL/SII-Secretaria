package ejb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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

import com.sun.tools.hat.internal.parser.Reader;

import excepcionesEJB.GrupoException;
import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazExpediente;
import interfacesEJB.InterfazImportar;
import jpa.Expediente;
import jpa.Grupo;

@Stateless
@LocalBean
public class ExpedienteImpl implements InterfazImportar,InterfazExpediente{

	@PersistenceContext(name="Secretaria")
	private EntityManager em;
	
	@Override
	public void Importar(String dir) throws ImportarException {
		// TODO Auto-generated method stub
		if(dir.substring(dir.length()-4).equals("xlsx")) {
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
		      
		       
		       Sheet sh = wb.getSheetAt(0);
		       int iRow = 0;
		       Row row = sh.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
		       int n=1;
		       while(row!=null) 
		       {
		    	
		    	   if(n>=5) {
		           Cell cell = row.getCell(4);  
		           String notaMedia = cell.getStringCellValue();
		           cell = row.getCell(17);  
		           String creditosSuperados = cell.getStringCellValue();
		           cell = row.getCell(18);  
		           String nExpediente = cell.getStringCellValue();
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
		}else if(dir.substring(dir.length()-3).equals("csv")){
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
            
		}else {
			throw new ImportarException();
		}

	}

	@Override
	public Expediente VisualizarExpediente(Expediente e) throws GrupoException {
		// TODO Auto-generated method stub
		Expediente expedienteExistente = em.find(Expediente.class, e.getNum_expediente());
		if (expedienteExistente == null) {
			throw new GrupoException();
		}
		return expedienteExistente;
	}
	
	
}
