package ejb;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazAlumno;
import interfacesEJB.InterfazImportar;
import jpa.Alumno;




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
			
			FileInputStream inp = null;
			
			try {
				 inp = new FileInputStream(new File(dir));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			HSSFWorkbook workbook = null;
			
			try {
				workbook = new HSSFWorkbook(inp);
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> filaIterator = sheet.iterator();
			
			int contF = 0;
			Row fila;
			
			while(filaIterator.hasNext()) {
				
				fila = filaIterator.next();
				
				if(contF > 3) {
					
					Iterator<Cell> celdaIterator = fila.cellIterator();
					
					int contC = 1;
					Cell celda;
					
					if(celdaIterator.hasNext()) {
						
						String dni="";
						String nombre="";
						String apellido1="";
						String apellido2="";
						String email_i="";
						String email_p="";
						String telefono="";
						String movil="";
						String direccion="";
						String localidad="";
						String provincia="";
						String cp="";
					
						while(celdaIterator.hasNext()) {
							celda = celdaIterator.next();
							
							switch(contC) {
								case(1):
									dni = celda.getStringCellValue();
								case(2):
									nombre = celda.getStringCellValue();
								case(3):
									apellido1 = celda.getStringCellValue();
								case(4):
									apellido2 = celda.getStringCellValue();
								case(7):
									email_i = celda.getStringCellValue();
								case(8):
									email_p = celda.getStringCellValue();
								case(9):
									telefono = celda.getStringCellValue();
								case(10):
									movil = celda.getStringCellValue();
								case(11):
									direccion = celda.getStringCellValue();
								case(12):
									localidad = celda.getStringCellValue();
								case(13):
									provincia = celda.getStringCellValue();
								case(14):
									cp = celda.getStringCellValue();
								default:
							}
							contC++;
						}	
						
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
			    		a.setCP(Integer.parseInt(cp));
						
			    		em.persist(a);
					}
					
				}else {
					contF++;
				}
				
			}
			
		} else if (dir.endsWith("csv")){
			 //Para el archivo csv de 'alumnos' 
			BufferedReader reader;
			
			try {
				reader = Files.newBufferedReader(Paths.get(dir));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.newFormat(';'));		
				int n=0;
				
	            for (CSVRecord csvRecord : csvParser) {
	            	if(n>=4) {
	            		
			    		String dni = csvRecord.get(0);
			    		String nombre = csvRecord.get(1);
			    		String apellido1 = csvRecord.get(2);  
			    		String apellido2 = csvRecord.get(3);
			    		String email_i = csvRecord.get(6);
			    		String email_p = csvRecord.get(7); 
			    		String telefono = csvRecord.get(8);
			    		String movil = csvRecord.get(9);
			    		String direccion = csvRecord.get(10);
			    		String localidad = csvRecord.get(11);
			    		String provincia = csvRecord.get(12);
			    		String cp = csvRecord.get(13);
	            		
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
				e.printStackTrace();
			}
            
		} else {
			throw new ImportarException();
		}
		
	}

	
	@Override
	public void validarAcceso(Alumno a) throws AlumnoException {
    	Alumno alumno = em.find(Alumno.class, a.getID());
    	
    	if(alumno==null) {
    		throw new AlumnoException();
    	}
    	
	}
	
	
	@Override
	public Alumno VisualizarAlumno(String dni) throws AlumnoException {
		
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
