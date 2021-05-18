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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazTitulacion;
import jpa.Alumno;
import jpa.Titulacion;


@Stateless
@LocalBean
public class TitulacionImpl implements InterfazTitulacion, InterfazImportar {
	@PersistenceContext(unitName = "Secretaria")
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
			while(fila != null) {
				if(contF >= 1) {
						
					Integer codigo = (int) fila.getCell(0).getNumericCellValue();
					String nombre = fila.getCell(1).getStringCellValue();
					Float creditos = (float) fila.getCell(2).getNumericCellValue();
					
					Titulacion t = new Titulacion();
		    		t.setCodigo(codigo);
		    		t.setNombre(nombre);
		    		t.setCreditos(creditos);
		    		
		    		em.persist(t);
		    	}
		    		contF++;
		    		fila = sheet.getRow(contF);
			}

            		
		    	
		       
		} else if (dir.endsWith("csv")){
			 //Para el archivo csv de 'alumnos' 
			BufferedReader reader;
			
			try {
				reader = Files.newBufferedReader(Paths.get(dir));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.newFormat(';'));		
				int n=0;
				
	            for (CSVRecord csvRecord : csvParser) {
	            	if(n>=1) {
	            		
	            		String codigo = csvRecord.get(0);
			    		String nombre = csvRecord.get(1);
			    		String creditos = csvRecord.get(2);  
			    		
	            		Titulacion t = new Titulacion();
			    		t.setCodigo( Integer.parseInt(codigo) );
			    		t.setNombre(nombre);
			    		t.setCreditos( Float.parseFloat(creditos) );
			    		 
			    		em.persist(t);
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
	public Titulacion VisualizarTitulacion(Integer codigo) throws TitulacionException {
		// TODO Auto-generated method stub
		Titulacion titulacionExistente = em.find(Titulacion.class, codigo );
		
		if (titulacionExistente == null) {
			throw new TitulacionException();
		}
		
		return titulacionExistente;
	}

	
	
}
