package interfacesEJB;

import javax.ejb.Local;

import excepcionesEJB.OptativaException;
import jpa.Optativa;

@Local
public interface InterfazOptativa {

	/**Este método es para visualizar el expediente
	 * @param e expediente a visualizar*/
	public Optativa VisualizarOptativa(Integer referencia) throws OptativaException;
	
}
