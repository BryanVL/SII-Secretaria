package interfacesEJB;

import javax.ejb.Local;

import excepcionesEJB.GrupoException;
import excepcionesEJB.ImportarException;
import jpa.Expediente;


@Local
public interface InterfazExpediente {

	/**Este método es para visualizar el expediente
	 * @param e expediente a visualizar*/
	public Expediente VisualizarExpediente(Expediente e) throws GrupoException;
}
