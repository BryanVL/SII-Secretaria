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

import excepcionesEJB.AsignaturaException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.OptativaException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazImportar;
import interfacesEJB.InterfazOptativa;
import jpa.Asignatura;
import jpa.Optativa;
import jpa.Titulacion;


@Stateless
@LocalBean
public class OptativaImpl implements InterfazOptativa, InterfazImportar{
	@PersistenceContext(unitName = "Secretaria")
    private EntityManager em;
	
	@Override
	public void Importar(String dir) throws ImportarException {
		// TODO Auto-generated method stub
		if(dir.endsWith("xlsx")) {
			
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
		    	
			Sheet sh = wb.getSheetAt(2);
			int iRow = 0;
		    
			Row row = sh.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
		    int n=1; 
		    
		    while(row!=null) 
		    {
		    	
		    	if(n>=2) {

		    		Cell cell = row.getCell(1); 
		    		String referencia =cell.getStringCellValue();
		    		cell = row.getCell(2); 
		    		String plazas = cell.getStringCellValue();
		    		cell = row.getCell(3); 
		    		String mencion = cell.getStringCellValue();
            		
		    		Asignatura asignaturaExistente = em.find(Asignatura.class, referencia );
		    		if(asignaturaExistente == null) {
		    			throw new AsignaturaException();
		    		}
		    		
		    		Optativa o = new Optativa();
		    		o.setPlazas( Integer.parseInt(plazas) );
		    		o.setMencion(mencion);
		    		o.setAsignatura(asignaturaExistente);
		    		
		    		Titulacion titulacion = asignaturaExistente.getTitulacion();
		    		List<Titulacion> titulaciones = new ArrayList<>();
		    		titulaciones.add(titulacion);
		    		o.setTitulaciones(titulaciones);
		    		
		    		em.persist(o);
		    		
		    	}
		    	
		        n++;
		        iRow++;  
		        row = sh.getRow(iRow);
		    }
		    
		    
		    }  catch (AsignaturaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		       
		} else if (dir.endsWith("csv")){
			BufferedReader reader;
			
			try {
				reader = Files.newBufferedReader(Paths.get(dir));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);		
				int n=0;
				
	            for (CSVRecord csvRecord : csvParser) {
	            	if(n>=1) {
	            		
	            		String[] lista = csvRecord.get(0).split(";");
			    		String referencia = lista[0];
			    		String plazas = lista[1];
			    		String mencion;
			    		
			    		Asignatura asignaturaExistente = em.find(Asignatura.class, referencia );
			    		if(asignaturaExistente == null) {
			    			throw new AsignaturaException();
			    		}
			    		
			    		Optativa o = new Optativa();
			    		o.setPlazas( Integer.parseInt(plazas) );
			    		
			    		if(lista.length == 3) {
			    			mencion = lista[2];
			    			o.setMencion(mencion);
			    		}
			    		o.setAsignatura(asignaturaExistente);
			    		
			    		Titulacion titulacion = asignaturaExistente.getTitulacion();
			    		List<Titulacion> titulaciones = new ArrayList<>();
			    		titulaciones.add(titulacion);
			    		o.setTitulaciones(titulaciones);
			    		
			    		em.persist(o);
			    		
	            	}
	            	n++;
				}
	            
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AsignaturaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
		} else {
			throw new ImportarException();
		}
		
	}

	@Override
	public Optativa VisualizarOptativa(Optativa o) throws OptativaException {
		// TODO Auto-generated method stub
		Optativa optativaExistente = em.find(Optativa.class, o.getAsignatura().getReferencia() );
		
		if (optativaExistente == null) {
			throw new OptativaException();
		}
		
		return  optativaExistente;
	}

}
