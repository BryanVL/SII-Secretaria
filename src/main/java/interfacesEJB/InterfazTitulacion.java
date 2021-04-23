package interfacesEJB;

import excepcionesEJB.TitulacionException;
import jpa.Titulacion;

public interface InterfazTitulacion {

	
	/**Este método es para visualizar el expediente
	 * @param e expediente a visualizar*/
	public Titulacion VisualizarTitulacion(Titulacion t) throws TitulacionException;
	
}
