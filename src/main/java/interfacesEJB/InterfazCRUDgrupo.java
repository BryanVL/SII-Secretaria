package interfacesEJB;

import javax.ejb.Local;

import excepcionesEJB.GrupoException;
import jpa.Grupo;

@Local
public interface InterfazCRUDgrupo {

	/**Este método es para crear un grupo
	 * @param g grupo a crear*/
	public void Crear(Grupo g) throws GrupoException;
	
	/**Este  método es para leer un grupo
	 * @param g grupo a leer*/
	public void Leer(Grupo g) throws GrupoException;
	
	/**Este  método es para borrar un grupo
	 * @param g grupo a borrar*/
	public void Borrar(Grupo g) throws GrupoException;
	
	/**Este  método es para actualizar un grupo
	 * @param g grupo a actualizar*/
	public void Actualizar(Grupo g) throws GrupoException;
	
}
