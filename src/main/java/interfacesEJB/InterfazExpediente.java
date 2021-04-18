package interfacesEJB;

import javax.ejb.Local;

import excepcionesEJB.ImportarException;
import jpa.Expediente;


@Local
public interface InterfazExpediente {

	/**Este método es para visualizar el expediente
	 * @param e expediente a visualizar*/
	public void VisualizarExpediente(Expediente e) throws ImportarException;
}
