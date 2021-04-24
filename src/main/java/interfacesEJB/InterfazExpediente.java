package interfacesEJB;

import javax.ejb.Local;

import excepcionesEJB.ExpedienteException;
import excepcionesEJB.TitulacionException;
import jpa.Expediente;


@Local
public interface InterfazExpediente {

	/**Este método es para visualizar el expediente
	 * @param e expediente a visualizar*/
	public Expediente VisualizarExpediente(Integer num_expediente) throws ExpedienteException;
	
}
