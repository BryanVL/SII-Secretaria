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
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazAsignatura;
import interfacesEJB.InterfazImportar;
import jpa.Asignatura;
import jpa.Idiomas;
import jpa.Titulacion;



@Stateless
@LocalBean
public class AsignaturaImpl implements InterfazAsignatura, InterfazImportar {
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

		    		Cell cell = row.getCell(4); 
		    		String referencia = cell.getStringCellValue();
		    		
		    		Asignatura asignaturaExistente = em.find(Asignatura.class, referencia );
		    		if(asignaturaExistente == null) {

			    		cell = row.getCell(3); 
			    		String codigo = cell.getStringCellValue();
			    		cell = row.getCell(9); 
			    		String creditos_total = cell.getStringCellValue();
			    		cell = row.getCell(7); 
			    		String creditos_teoria = cell.getStringCellValue();
			    		cell = row.getCell(2); 
			    		String ofertada =cell.getStringCellValue();
			    		cell = row.getCell(5); 
			    		String nombre = cell.getStringCellValue();
			    		cell = row.getCell(6); 
			    		String curso = cell.getStringCellValue();
			    		//cell = row.getCell(); 
			    		//String caracter = csvRecord.get();
			    		cell = row.getCell(10); 
			    		String duracion = cell.getStringCellValue();
			    		//cell = row.getCell(); 
			    		//String unidad_temporal = csvRecord.get(2);
	            		
			    		cell = row.getCell(1); 
			    		String codigoTitulacion = cell.getStringCellValue();
			    		Titulacion titulacionExistente = em.find(Titulacion.class, codigoTitulacion );
			    		if(titulacionExistente == null) {
			    			throw new TitulacionException();
			    		}
		    		 
	            		Asignatura a = new Asignatura();
			    		a.setReferencia( Integer.parseInt(referencia));
			    		a.setCodigo( Integer.parseInt(codigo));
			    		a.setCreditos_total( Float.parseFloat(creditos_total) );
			    		a.setCreditos_teoria( Float.parseFloat(creditos_teoria) );
			    		a.setOfertada(ofertada);
			    		a.setNombre(nombre);
			    		a.setCurso( Integer.parseInt(curso) );
			    		//a.setCaracter(caracter);
			    		a.setDuracion(duracion);
			    		//a.setUnidad_temporal(unidad_temporal);
	            		a.setTitulacion(titulacionExistente);
	            		
	            		cell = row.getCell(12); 
			    		String otro_idioma = cell.getStringCellValue();
			    		if(otro_idioma!=null) {
			    			Idiomas idioma = new Idiomas();
			    			idioma.setNombre("Ingles");
			    			List<Idiomas> idiomas = new ArrayList<>();
			    			idiomas.add(idioma);
			    			a.setIdiomas(idiomas);
			    		}
			    		
			    		em.persist(a);
		    		}
		    		
		    	}
		    	
		        n++;
		        iRow++;  
		        row = sh.getRow(iRow);
		    }
		    
		    
		    } catch (TitulacionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		       
		} else if (dir.endsWith("csv")){
			BufferedReader reader;
			
			try {
				reader = Files.newBufferedReader(Paths.get(dir));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.newFormat(';'));		
				int n=0;
				
	            for (CSVRecord csvRecord : csvParser) {
	            	if(n>=1) {
	            		
			    		Integer referencia = Integer.parseInt(csvRecord.get(3));
			    		
			    		Asignatura asignaturaExistente = em.find(Asignatura.class, referencia );
			    		if(asignaturaExistente == null) {
			    		
				    		String codigo = csvRecord.get(2);
				    		String creditos_total = csvRecord.get(8);  
				    		String creditos_teoria = csvRecord.get(7);
				    		String ofertada = csvRecord.get(1);
				    		String nombre = csvRecord.get(4);
				    		String curso = csvRecord.get(5);
				    		String duracion = csvRecord.get(9);
		            		
				    		Integer codigoTitulacion = Integer.parseInt(csvRecord.get(0));
				    		Titulacion titulacionExistente = em.find(Titulacion.class, codigoTitulacion );
				    		if(titulacionExistente == null) {
				    			throw new TitulacionException();
				    		}
				    		
		            		Asignatura a = new Asignatura();
				    		a.setReferencia(referencia);
				    		a.setCodigo( Integer.parseInt(codigo));
				    		a.setCreditos_total( Float.parseFloat(creditos_total) );
				    		a.setCreditos_teoria( Float.parseFloat(creditos_teoria) );
				    		a.setOfertada(ofertada);
				    		a.setNombre(nombre);
				    		a.setCurso( Integer.parseInt(curso) );
				    		//a.setCaracter(caracter);
				    		a.setDuracion(duracion);
				    		//a.setUnidad_temporal(unidad_temporal);
		            		a.setTitulacion(titulacionExistente);
				    		
		            		//Hacer un if que compare el length de lista y luego se añade o no el otro_idioma
		            		if(csvRecord.size() == 12) {
		            			String otro_idioma = csvRecord.get(11);
		            			if(otro_idioma!=null) {
		            				Idiomas idiomaExistente = em.find(Idiomas.class, "Ingles");
		            				if(idiomaExistente == null) {
		            					Idiomas idioma = new Idiomas();
		            					idioma.setNombre("Ingles");
		            					idiomaExistente = idioma;
		            				}
		            				List<Idiomas> idiomas = new ArrayList<>();
		            				idiomas.add(idiomaExistente);
		            				a.setIdiomas(idiomas);
		            			}
		            		}
		            		
				    		em.persist(a);
		            	}
	            	}
	            	n++;
				}
	            
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TitulacionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
		} else {
			throw new ImportarException();
		}
		
	}

	@Override
	public Asignatura VisualizarAsignatura(Integer Referencia) throws AsignaturaException {
		// TODO Auto-generated method stub
		Asignatura asignaturaExistente = em.find(Asignatura.class, Referencia );
		
		if (asignaturaExistente == null) {
			throw new AsignaturaException();
		}
		
		return asignaturaExistente;
	}
	
	
}
