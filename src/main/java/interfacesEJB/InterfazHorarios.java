package interfacesEJB;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.ClaseException;
import excepcionesEJB.MatriculaException;
import jpa.Alumno;
import jpa.Asignatura;
import jpa.Clase;
import jpa.Grupo;
import jpa.Matricula;

@Local
public interface InterfazHorarios {
	
	public List<Clase> visTodasClase() throws ClaseException;
	
	public Clase VisualizarHorarios(Clase c) throws ClaseException;
	
	public List<Clase> VisualizarHorarios(Grupo g) throws ClaseException;
	
	public HashMap<Asignatura, List<Clase>> VisualizarHorarios(Alumno a, Matricula matricula) throws ClaseException, AlumnoException, MatriculaException;
	
}
