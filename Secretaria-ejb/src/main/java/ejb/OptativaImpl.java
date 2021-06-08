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
import java.util.logging.Logger;

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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excepcionesEJB.AsignaturaException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.MatriculaException;
import excepcionesEJB.OptativaException;
import excepcionesEJB.TitulacionException;
import interfacesEJB.InterfazOptativa;
import jpa.Asignatura;
import jpa.Matricula;
import jpa.Optativa;
import jpa.Titulacion;


@Stateless
@LocalBean
public class OptativaImpl implements InterfazOptativa{
	@PersistenceContext(unitName = "Secretaria")
    private EntityManager em;
	
	private static final Logger LOGGER = Logger.getLogger(Optativa.class.getCanonicalName());

	
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
				for(int i = 0; i < 2; i++) {
				
					XSSFSheet sheet = workbook.getSheetAt(i);
					
					int contF = 1;
					Row fila = sheet.getRow(contF);
					while(fila != null && fila.getCell(0) != null) {
						if(contF >= 1) {
								
							Integer referencia = (int) fila.getCell(0).getNumericCellValue();
							
							Optativa opt = em.find(Optativa.class, referencia);
							if(opt == null) {
								Integer plazas = (int) fila.getCell(1).getNumericCellValue();
								String mencion;
								if(i == 1) {
									mencion = fila.getCell(2).getStringCellValue();
								} else {
									mencion = "Informatica";
								}
								Asignatura asignaturaExistente = em.find(Asignatura.class, referencia);
					    		if(asignaturaExistente != null) {

						    		Optativa o = new Optativa();
						    		o.setPlazas(plazas);
						    		o.setMencion(mencion);
						    		o.setAsignatura(asignaturaExistente);
						    		
						    		Titulacion titulacion = asignaturaExistente.getTitulacion();
						    		List<Titulacion> titulaciones = new ArrayList<>();
						    		titulaciones.add(titulacion);
						    		o.setTitulaciones(titulaciones);
						    		em.persist(o);
					    		}
							}
				    	}
				    		contF++;
				    		fila = sheet.getRow(contF);
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
	            		
			    		Integer referencia = Integer.parseInt(csvRecord.get(0));
			    		
			    		Optativa opt = em.find(Optativa.class, referencia);
						if(opt == null) {
				    		String plazas = csvRecord.get(1);
				    		
				    		Asignatura asignaturaExistente = em.find(Asignatura.class, referencia);
				    		if(asignaturaExistente == null) {
				    			throw new AsignaturaException();
				    		}
				    		
				    		Optativa o = new Optativa();
				    		o.setPlazas( Integer.parseInt(plazas) );
				    		
				    		if(csvRecord.size() == 3) {
				    			String mencion = csvRecord.get(2);
				    			o.setMencion(mencion);
				    		}
				    		o.setAsignatura(asignaturaExistente);
				    		
				    		Titulacion titulacion = em.find(Titulacion.class, asignaturaExistente.getTitulacion().getCodigo());
				    		List<Titulacion> titulaciones = new ArrayList<>();
				    		titulaciones.add(titulacion);
				    		o.setTitulaciones(titulaciones);
				    		
				    		em.persist(o);
						}
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
	public Optativa VisualizarOptativa(Integer referencia) throws OptativaException{
		// TODO Auto-generated method stub
		
		Optativa optativaExistente = em.find(Optativa.class, referencia);
		
		if (optativaExistente == null) {
			throw new OptativaException();
		}
		
		return  optativaExistente;
	}
	
	
	@Override
	public List<Asignatura> mostrarDatosAdmin() throws OptativaException {
		TypedQuery<Asignatura> query = em.createQuery("SELECT a FROM Asignatura a where a.optativa is not null",Asignatura.class);
		List<Asignatura> optativas = query.getResultList();
		if(optativas == null || optativas.size() == 0) {
			throw new OptativaException("No se han encontrado optativas");
		}
		return optativas;
	}

	@Override
	public void borrarOptativas() throws OptativaException {
		for(Asignatura a : mostrarDatosAdmin()) {
			LOGGER.info(a.getOptativa().toString());
			if(a.getOptativa()!= null) {
			em.remove(a.getOptativa());
			}
		}
	}
}
