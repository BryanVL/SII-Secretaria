package interfacesEJB;

import javax.ejb.Local;

import excepcionesEJB.AsignaturaException;
import excepcionesEJB.GrupoException;
import excepcionesEJB.MatriculaException;
import jpa.Asignatura;
import jpa.Grupo;
import jpa.Matricula;

@Local
public interface InterfazGrupo {

	/**Este método es para crear un grupo
	 * @param g grupo a crear*/
	public void Crear(Grupo g) throws GrupoException;
	
	/**Este  método es para leer un grupo
	 * @param g grupo a leer*/
	public Grupo Leer(Grupo g) throws GrupoException;
	
	/**Este  método es para borrar un grupo
	 * @param g grupo a borrar*/
	public void Borrar(Grupo g) throws GrupoException;
	
	/**Este  método es para actualizar un grupo
	 * @param g grupo a actualizar*/
	public void Actualizar(Grupo g) throws GrupoException;
	
	/**	//Este método se ha hecho acorde con que suponemos que el número de plazas
	 * máximo va disminuyendo conforme haya más alumnos asignados a un grupo.
	 * Si es 0 el número de plazas lanzamos excepción porque significaría que no nos quedan plazas.
	 * @param g grupo a actualizar*/
	public void ComprobarPlazas(Grupo g) throws GrupoException;
	
	public void asignarGrupo(Matricula matricula, Grupo grupo, Asignatura asignatura) throws MatriculaException, GrupoException, AsignaturaException;
	
}
