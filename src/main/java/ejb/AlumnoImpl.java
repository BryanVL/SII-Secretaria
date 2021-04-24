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
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
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
		    		  
		    		Cell cell = row.getCell(1);  
		    		String dni = cell.getStringCellValue();
		    		cell = row.getCell(2);  
		    		String nombre = cell.getStringCellValue();
		    		cell = row.getCell(3);  
		    		String apellido1 = cell.getStringCellValue();
		    		cell = row.getCell(4);  
		    		String apellido2 = cell.getStringCellValue();
		    		cell = row.getCell(7);  
		    		String email_i = cell.getStringCellValue();
		    		cell = row.getCell(8);  
		    		String email_p = cell.getStringCellValue();
		    		cell = row.getCell(9);  
		    		String telefono = cell.getStringCellValue();
		    		cell = row.getCell(10);  
		    		String movil = cell.getStringCellValue();
		    		cell = row.getCell(11);  
		    		String direccion = cell.getStringCellValue();
		    		cell = row.getCell(12);  
		    		String localidad = cell.getStringCellValue();
		    		cell = row.getCell(13);  
		    		String provincia = cell.getStringCellValue();
		    		cell = row.getCell(14);  
		    		String cp = cell.getStringCellValue();
		    		
		    		Alumno a = new Alumno();
		    		a.setDNI(dni);
		    		a.setNombre(nombre);
		    		a.setApellido1(apellido1);
		    		a.setApellido2(apellido2);
		    		a.setEmail_institucional(email_i);
		    		a.setEmail_personal(email_p);
		    		a.setTelefono( Integer.parseInt(telefono.replaceAll(" ","")) );
		    		a.setMovil(Integer.parseInt(movil.replaceAll(" ", "")) );
		    		a.setDireccion(direccion);
		    		a.setLocalidad(localidad);
		    		a.setProvincia(provincia);
		    		a.setCP( Integer.parseInt(cp) );
		    		
		    		em.persist(a);
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
	            	if(n>=4) {
	            		
	            		String[] lista = csvRecord.get(0).split(";");
			    		String dni = lista[0];
			    		String nombre = lista[1];
			    		String apellido1 = lista[2];  
			    		String apellido2 = lista[3];
			    		String email_i = lista[6];
			    		String email_p = lista[7]; 
			    		String telefono = lista[8];
			    		String movil = lista[9];
			    		String direccion = lista[10];
			    		String localidad = lista[11];
			    		String provincia = lista[12];
			    		String cp = lista[13];
	            		
	            		Alumno a = new Alumno();
			    		a.setDNI(dni);
			    		a.setNombre(nombre);
			    		a.setApellido1(apellido1);
			    		a.setApellido2(apellido2);
			    		a.setEmail_institucional(email_i);
			    		a.setEmail_personal(email_p);
			    		a.setTelefono( Integer.parseInt(telefono.replaceAll(" ","")) );
			    		a.setMovil(Integer.parseInt(movil.replaceAll(" ", "")) );
			    		a.setDireccion(direccion);
			    		a.setLocalidad(localidad);
			    		a.setProvincia(provincia);
			    		a.setCP( Integer.parseInt(cp) );
			    		 
			    		em.persist(a);
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
	public Alumno VisualizarAlumno(String dni) throws AlumnoException {
		// TODO Auto-generated method stub
		
		TypedQuery <Alumno> query = em.createQuery("Select a from Alumno a where a.DNI = :fdni", Alumno.class);
    	query.setParameter("fdni", dni);
        List<Alumno> alumnos = query.getResultList();
		
		Alumno alumnoExistente = em.find(Alumno.class, alumnos.get(0).getID() );
		
		if (alumnoExistente == null) {
			throw new AlumnoException();
		}
		
		return alumnoExistente;
	}
	
	
	
}
