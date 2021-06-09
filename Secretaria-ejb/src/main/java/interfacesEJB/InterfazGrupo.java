package interfacesEJB;

import java.util.List;

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
	public void crear(Integer titulacion, Integer curso, String letra) throws GrupoException;
	
	/**Este  método es para leer un grupo
	 * @param g grupo a leer*/
	public Grupo leer(Grupo g) throws GrupoException;
	
	/**Este  método es para borrar un grupo
	 * @param g grupo a borrar*/
	public void borrar(Grupo g) throws GrupoException;
	
	/**Este  método es para actualizar un grupo
	 * @param g grupo a actualizar*/
	public void actualizar(Grupo g) throws GrupoException;
	
	/**	//Este método se ha hecho acorde con que suponemos que el número de plazas
	 * máximo va disminuyendo conforme haya más alumnos asignados a un grupo.
	 * Si es 0 el número de plazas lanzamos excepción porque significaría que no nos quedan plazas.
	 * @param g grupo a actualizar*/
	public void comprobarPlazas(Grupo g) throws GrupoException;
	
	public Grupo buscarPorCursoLetra(Integer titulacion, Integer curso, String letra) throws GrupoException;
	public List<Grupo> buscarPorCursoLetra(Integer curso, String letra) throws GrupoException;

	public List<Grupo> mostrarDatosAdmin() throws GrupoException;

	
	public void asignarGrupo(Matricula matricula, Grupo grupo, Asignatura asignatura) throws MatriculaException, GrupoException, AsignaturaException;

	Grupo leer2(Long id) throws GrupoException;
	
}
