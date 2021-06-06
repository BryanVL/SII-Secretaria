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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.UsuarioException;
import interfacesEJB.InterfazAlumno;
import jpa.Alumno;
import jpa.Usuario;




@Stateless
@LocalBean
public class AlumnoImpl implements InterfazAlumno{
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
			
			int contF = 4;
			Row fila = sheet.getRow(contF);
			while(fila != null) {
				if(contF >= 4) {
						
					String dni= fila.getCell(0).getStringCellValue();
					String nombre = fila.getCell(1).getStringCellValue();
					String apellido1 = fila.getCell(2).getStringCellValue();
					String apellido2 = fila.getCell(3).getStringCellValue();
					String email_i = fila.getCell(6).getStringCellValue();
					String email_p = fila.getCell(7).getStringCellValue();
					String telefono = fila.getCell(8).getStringCellValue();
					String movil = fila.getCell(9).getStringCellValue();
					String direccion = fila.getCell(10).getStringCellValue();
					String localidad = fila.getCell(11).getStringCellValue();
					String provincia = fila.getCell(12).getStringCellValue();
					Integer cp = (int) fila.getCell(13).getNumericCellValue();
					
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
		    		a.setCP(cp);
					
		    		em.persist(a);
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
	public Alumno VisualizarAlumno(String dni) throws AlumnoException {
		
		TypedQuery <Alumno> query = em.createQuery("Select a from Alumno a where a.DNI = :fdni", Alumno.class);
    	query.setParameter("fdni", dni);
        List<Alumno> alumnos = query.getResultList();
		
        if(alumnos == null || alumnos.size() == 0) {
        	throw new AlumnoException("No se ha encontrado el alumno");
        }
        
		Alumno alumnoExistente = alumnos.get(0);
		
		return alumnoExistente;
	}
	
	@Override
	public List<Alumno> mostrarDatosAdmin() throws AlumnoException{
		
		TypedQuery<Alumno> query = em.createQuery("SELECT a FROM Alumno a",Alumno.class);
		List<Alumno> alumnos = query.getResultList();
		if(alumnos == null || alumnos.size() == 0) {
			throw new AlumnoException("No se ha encontrado alumnos");
		}
		
		return alumnos;
	}
	
	@Override
	public void borrarAlumnos() throws AlumnoException {
		for(Alumno a : mostrarDatosAdmin()) {
			em.remove(a);
		}
	}
	
}
