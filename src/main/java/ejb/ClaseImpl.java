package ejb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import excepcionesEJB.ClaseException;
import excepcionesEJB.ExpedienteException;
import excepcionesEJB.GrupoException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.MatriculaException;
import interfacesEJB.InterfazHorarios;
import interfacesEJB.InterfazImportar;
import jpa.Alumno;
import jpa.Asignatura;
import jpa.Asignaturas_Matricula;
import jpa.Asignaturas_Matricula_PK;
import jpa.Clase;
import jpa.Clase_PK;
import jpa.Expediente;
import jpa.Grupo;
import jpa.Matricula;

/**
 * Session Bean implementation class horarios
 */
@Stateless
@LocalBean
public class ClaseImpl implements InterfazImportar, InterfazHorarios{

	@PersistenceContext(name="Secretaria")
	private EntityManager em;
 

	@Override
	public void Importar(String dir) throws ImportarException {
		// TODO Auto-generated method stub
		
		if(dir.endsWith(".xlsx")) {
			//Para el archivo xlsx de 'Datos alumnadoFAKE' sin título
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
		       int n=0;
		       while(row!=null) {
		    	  
		    	   if(n>=1) {		    		   
			           Cell cell = row.getCell(0);  
			           String curso = cell.getStringCellValue();			           
			           cell = row.getCell(1);  
			           String gr = cell.getStringCellValue();			           
			           cell = row.getCell(2);  
			           String asig = cell.getStringCellValue();			           
			           cell = row.getCell(3);  
			           String dia = cell.getStringCellValue();			           
			           cell = row.getCell(4);  
			           String hora_inicio = cell.getStringCellValue();			           
			           cell = row.getCell(5);  
			           String hora_fin = cell.getStringCellValue();
			           		
			           Clase c = new Clase();
			           try {
			        	   c = procesado(curso,gr,asig,dia,hora_inicio, hora_fin);
			           }catch(GrupoException e) {
			        	   throw new RuntimeException();
			           }
		               em.persist(c);
		    	   }
		           n++;
		           iRow++;  
		           row = sh.getRow(iRow);
		       }
		}else if(dir.endsWith(".csv")){
			//Para el archivo csv de 'alumnos' 
			BufferedReader reader;
			try {
				reader = Files.newBufferedReader(Paths.get(dir));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.newFormat(';'));		
				int n=0;
	            for (CSVRecord csvRecord : csvParser) {
	            	
	            	if(n>=1) {    
	            		
		                String curso = csvRecord.get(0);  
		                String gr = csvRecord.get(1);  
			            String asig = csvRecord.get(2);  
			            String dia = csvRecord.get(3); 
			            String hora_inicio = csvRecord.get(4); 			           
			            String hora_fin = csvRecord.get(5);  
	               
			            Clase c = new Clase();
				           try {
				        	   c = procesado(curso,gr,asig,dia,hora_inicio, hora_fin);
				           }catch(GrupoException e) {
				        	   throw new RuntimeException();
				           }
		             	
			            em.persist(c);
	            	}
	            n++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
		}else {
			throw new ImportarException();
		}
		
	}
	
	private Clase procesado(String curso, String gr, String asig, String dia, String hora_inicio, String hora_fin) throws GrupoException {
		
		Clase c = new Clase();
        Asignatura asignatura = em.find(Asignatura.class, Integer.parseInt(asig));  
        c.setAsignatura(asignatura);
               
        TypedQuery<Grupo> query = em.createQuery("Select g from Grupo g where g.Letra = :letra and g.Curso = :curso", Grupo.class);
        query.setParameter("letra", gr);
        query.setParameter("curso", Integer.parseInt(curso));
        List<Grupo> grupos = query.getResultList();
        
        if(grupos.size()==0) {
        	throw new GrupoException("No se encontro el grupo");
        }
        
        Grupo g = grupos.get(0);
        c.setGrupo(g);
   
       /* boolean encontrado = false;     
        int i = 0;
        Grupo g = new Grupo();
        while(i<grupos.size() && !encontrado) {
     	   g = grupos.get(i); 
     	   if(g.getCurso()==Integer.parseInt(curso) && g.getLetra().equalsIgnoreCase(gr)) {
     		   encontrado=true;
     		   c.setGrupo(g);
     	   }
     	   i++;
        }
        
        if(!encontrado) {
        	throw new GrupoException();
        }*/
        String[] diaa = dia.split("/");
        int año = Integer.parseInt(diaa[2])-1900;
        int mes = Integer.parseInt(diaa[1]);
        int diab = Integer.parseInt(diaa[0]);
        c.setHora_fin(convertirTime(hora_fin));
        Clase_PK id = new Clase_PK();
        id.setDia(new Date(año,mes,diab));
        id.setHora_inicio(convertirTime(hora_inicio));
        id.setIdG(g.getID());
        c.setId(id);
        
        return c;
	}
	
	private Time convertirTime(String hora) {
		Time res;
		if(hora!=null) {
			String[] tiempo = hora.split(":");
			res = new Time(Integer.parseInt(tiempo[0]), Integer.parseInt(tiempo[1]),0);	
		}else {
			res= new Time(0,0,0);
		}

		return res;
	}
	
	public List<Clase> visTodasClase() throws ClaseException{
		Query query = em.createQuery("Select c from Clase c", Clase.class);	  
		List<Clase> clases = query.getResultList();
		return clases;
	}
	
	//Visualizar solo una clase
	public Clase VisualizarHorarios(Clase c) throws ClaseException {
		
		Clase claseExistente = em.find(Clase.class, c.getId());
		
		if (claseExistente == null) {
			throw new ClaseException();
		}

		return claseExistente;
	}

	//Visualizar todas las clases de un grupo
	public List<Clase> VisualizarHorarios(Grupo g) throws ClaseException {
		
		Query query = em.createQuery("Select c from Clase c where c.grupo = :clave", Clase.class);	  
		query.setParameter("clave", g); 
        List<Clase> clases = query.getResultList();
		
		if (clases == null) {
			throw new ClaseException();
		}

		return clases;
	}
	
	//visualizar horarios de asignaturas
	public HashMap<Asignatura, List<Clase>> VisualizarHorarios(Alumno a, Matricula matricula) throws ClaseException, AlumnoException, MatriculaException {

		
		HashMap<Asignatura, List<Clase>> res = new HashMap<Asignatura, List<Clase>>();
		
		Alumno alu = em.find(Alumno.class, a.getID());
		if (alu == null) {	
			throw new AlumnoException();
		}
		
		Matricula matr = em.find(Matricula.class, matricula.getId());
		if (matr == null) {	
			throw new MatriculaException();
		}
		
		for(Asignaturas_Matricula asiM: matr.getAsigMat()) {
			res.put(asiM.getAsignatura(), asiM.getAsignatura().getClases());
		}
		
		
		/*List<Expediente> exp = alu.getExpedientes();
		List<Matricula> mat;
		List<Asignaturas_Matricula> asigM;
		
		for(Expediente e: exp) {
			mat = e.getMatriculas();
			for(Matricula m: mat) {
				asigM = m.getAsigMat();
				for(Asignaturas_Matricula asiM: asigM) {
					List<Clase> clases = new ArrayList<Clase>();
					Asignatura asig = asiM.getAsignatura();
					clases.addAll(asig.getClases());
					res.put(asig, clases);
				}
			}
		}*/
		
		return res;
	}
	
}
