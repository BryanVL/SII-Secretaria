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
		      
			Sheet sh = wb.getSheet("Hoja1");
			int iRow = 1;
		    
			Row row = sh.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
		    int n=1; 
		    
		    while(row!=null) 
		    {
		    	
		    	if(n>=2) {
		    		  
		    		Cell cell = row.getCell(1);  
		    		String codigo = cell.getStringCellValue();
		    		cell = row.getCell(2); 
		    		String nombre = cell.getStringCellValue();
		    		cell = row.getCell(3); 
		    		String creditos = cell.getStringCellValue(); 

            		Titulacion t = new Titulacion();
		    		t.setCodigo( Integer.parseInt(codigo) );
		    		t.setNombre(nombre);
		    		t.setCreditos( Float.parseFloat(creditos) );
		    		
		    		em.persist(t);
		    	}
		    	
		        n++;
		        iRow++;  
		        row = sh.getRow(iRow);
		    }
		       
		} else if (dir.endsWith("csv")){
			 //Para el archivo csv de 'alumnos' 
			BufferedReader reader;
			
			try {
				reader = Files.newBufferedReader(Paths.get(dir));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);		
				int n=0;
				
	            for (CSVRecord csvRecord : csvParser) {
	            	if(n>=1) {
	            		
	            		String[] lista = csvRecord.get(0).split(";");
	            		
			    		String codigo = lista[0];
			    		String nombre = lista[1];
			    		String creditos = lista[2];  
			    		
	            		
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
		Titulacion titulacionExistente = em.find(Titulacion.class, codigo);
		
		if (titulacionExistente == null) {
			throw new TitulacionException();
		}
		
		return titulacionExistente;
	}

	
	
}
