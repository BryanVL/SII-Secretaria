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
import javax.persistence.TypedQuery;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.AsignaturaException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazAsignatura;
import interfacesEJB.InterfazImportar;
import jpa.Alumno;
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
			for(int i = 2; i < 6; i++) {
				
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				int contF = 1;
				Row fila = sheet.getRow(contF);
				try {
					while(fila != null) {
						if(contF >= 1 && (fila.getCell(0) !=null)) {
								
							Integer referencia = (int) fila.getCell(3).getNumericCellValue();
							Asignatura asignaturaExistente = em.find(Asignatura.class, referencia);
							if(asignaturaExistente == null) {
								Integer codigo = (int) fila.getCell(2).getNumericCellValue();
								Float creditos_total;
								Float creditos_teoria;
								
								if(fila.getCell(8).getCellType() == CellType.NUMERIC) {
									creditos_total = (float) fila.getCell(8).getNumericCellValue();
									creditos_teoria = (float) fila.getCell(6).getNumericCellValue();
								} else {
									creditos_total = Float.parseFloat(fila.getCell(8).getStringCellValue());
									creditos_teoria = Float.parseFloat(fila.getCell(6).getStringCellValue());
								}
								
								String ofertada = fila.getCell(1).getStringCellValue();
								String nombre = fila.getCell(4).getStringCellValue();
								Integer curso = (int) fila.getCell(5).getNumericCellValue();
								String duracion = fila.getCell(9).getStringCellValue();
								Integer codigoTitulacion = (int) fila.getCell(0).getNumericCellValue();
								
								Titulacion titulacionExistente = em.find(Titulacion.class, codigoTitulacion );
								if(titulacionExistente == null) {
					    			throw new TitulacionException();
					    		}
								
								Asignatura a = new Asignatura();
					    		a.setReferencia(referencia);
					    		a.setCodigo(codigo);
					    		a.setCreditos_total(creditos_total);
					    		a.setCreditos_teoria(creditos_teoria);
					    		a.setOfertada(ofertada);
					    		a.setNombre(nombre);
					    		a.setCurso(curso);
					    		a.setDuracion(duracion);
			            		a.setTitulacion(titulacionExistente);
			            		
			            		if(fila.getCell(11) != null) {
			            			String otro_idioma = fila.getCell(11).getStringCellValue();
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
						
				    	contF++;
				    	fila = sheet.getRow(contF);
					} 
					
				} catch (TitulacionException e) {
					e.printStackTrace();
				}
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
		
		Asignatura asignaturaExistente = em.find(Asignatura.class, Referencia);
		
		if (asignaturaExistente == null) {
			throw new AsignaturaException("No se ha encontrado la asignatura");
		}
		
		return asignaturaExistente;
	}

	@Override
	public List<Asignatura> mostrarDatosAdmin() throws AsignaturaException {
		TypedQuery<Asignatura> query = em.createQuery("SELECT a FROM Asignatura a",Asignatura.class);
		List<Asignatura> asignaturas = query.getResultList();
		if(asignaturas == null || asignaturas.size() == 0) {
			throw new AsignaturaException("No se han encontrado asignaturas");
		}
		
		return asignaturas;
	}

	@Override
	public void borrarAsignaturas() throws AsignaturaException {
		for(Asignatura a : mostrarDatosAdmin()) {
			em.remove(a);
		}
	}
	
	
}
