package ejb;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.ExpedienteException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazExpediente;
import jpa.Alumno;
import jpa.Expediente;
import jpa.Titulacion;


@Stateless
@LocalBean
public class ExpedienteImpl implements InterfazExpediente{

	@PersistenceContext(name="Secretaria")
	private EntityManager em;
	
	@Override
	public void Importar(String dir) throws ImportarException {
		// TODO Auto-generated method stub
		if(dir.endsWith("xlsx")) {
			//Para el archivo xlsx de 'Datos alumnadoFAKE' sin título
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
			try {
				while(fila != null) {
					if(contF >= 4 && (fila.getCell(0) !=null)) {
	    	 
		    		   Integer nExpediente = (int) fila.getCell(4).getNumericCellValue();
		    		   
		    		   Expediente exp = em.find(Expediente.class, nExpediente);
		    		   if(exp == null) {
			    		   
			    		   Float notaMedia;
			    		   if(fila.getCell(17).getCellType() == CellType.STRING) {
			    			   notaMedia = Float.parseFloat(fila.getCell(17).getStringCellValue());
			    		   } else {
			    			   notaMedia = (float) fila.getCell(17).getNumericCellValue();
			    		   }
			    		   
			    		   Float creditosSuperados;
			    		   if(fila.getCell(18).getCellType() == CellType.NUMERIC) {
			    			   creditosSuperados = (float) fila.getCell(18).getNumericCellValue();
			    		   } else {
			    			   creditosSuperados = Float.parseFloat(fila.getCell(18).getStringCellValue());
			    		   }
			    		   
			    		   String dniAlumno = fila.getCell(0).getStringCellValue();
			    		   TypedQuery <Alumno> query = em.createQuery("Select a from Alumno a where a.DNI = :fdni", Alumno.class);
			    		   query.setParameter("fdni", dniAlumno);
			    		   List<Alumno> alumnos = query.getResultList();
			    		   if(alumnos.size() == 0) {
			    			   throw new AlumnoException();
			    		   }
	               	
			    		   Integer codigoTitulacion = nExpediente/100000;
			    		   Titulacion titulacionExistente = em.find(Titulacion.class, codigoTitulacion );
			    		   if(titulacionExistente == null) {
			    			   throw new TitulacionException();
			    		   }
			           
			           
			    		   Expediente e = new Expediente();  
			    		   e.setNota_media_provisional(notaMedia);
			    		   e.setCreditos_superados(creditosSuperados);
			    		   e.setNum_expediente(nExpediente);
			    		   e.setActivo(true);
			    		   e.setTitulacion(titulacionExistente);
			    		   e.setAlumno(alumnos.get(0));
		            	
			    		   em.persist(e);
		    		   }
		    	   }
					
			    	contF++;
			    	fila = sheet.getRow(contF);
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
				CSVParser csvParser = new CSVParser(reader, CSVFormat.newFormat(';'));		
				int n=0;
	            for (CSVRecord csvRecord : csvParser) {
	            	
	            	if(n>=4) {
	            		
	            		String nExpediente = csvRecord.get(4);
	            		
	            		Expediente exp = em.find(Expediente.class, Integer.parseInt(nExpediente));
	            		if(exp == null) {
		            		
		                	String notaMedia = csvRecord.get(17);
		                	String creditosSuperados = csvRecord.get(18);
		                	String dniAlumno = csvRecord.get(0);
		                	TypedQuery <Alumno> query = em.createQuery("Select a from Alumno a where a.DNI = :fdni", Alumno.class);
		                	query.setParameter("fdni", dniAlumno);
		                    List<Alumno> alumnos = query.getResultList();
		                    
		                    if(alumnos.size() == 0) {
		                    	throw new AlumnoException();
		                    }
		          
		                    Integer codigoTitulacion = Integer.parseInt(nExpediente.substring(0, 4));
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
	            	}
	            	n++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AlumnoException e) {
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
	public Expediente VisualizarExpediente(Integer num_expediente) throws ExpedienteException {
		
		Expediente expedienteExistente = em.find(Expediente.class, num_expediente);
		
		if (expedienteExistente == null) {
			throw new ExpedienteException("No se ha encontrado el expediente");
		}
		
		return expedienteExistente;
	}

	@Override
	public List<Expediente> mostrarDatosAdmin() throws ExpedienteException {
		TypedQuery<Expediente> query = em.createQuery("SELECT a FROM Expediente a",Expediente.class);
		List<Expediente> expedientes = query.getResultList();
		if(expedientes == null || expedientes.size() == 0) {
			throw new ExpedienteException("No se han encontrado expedientes");
		}
		
		return expedientes;
	}

	@Override
	public void borrarExpedientes() throws ExpedienteException {
		for(Expediente e : mostrarDatosAdmin()) {
			em.remove(e);
		}
	}
	
	
}
