package interfacesEJB;

import excepcionesEJB.OptativaException;
import jpa.Optativa;


public interface InterfazOptativa {

	/**Este método es para visualizar el expediente
	 * @param e expediente a visualizar*/
	public Optativa VisualizarOptativa(Optativa o) throws OptativaException;
	
}
