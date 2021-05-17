package interfacesEJB;

import javax.ejb.Local;

import excepcionesEJB.TitulacionException;
import jpa.Titulacion;

@Local
public interface InterfazTitulacion {

	
	/**Este método es para visualizar la titulacion
	 * @param codigo Titulacion a visualizar*/
	public Titulacion VisualizarTitulacion(Integer codigo) throws TitulacionException;
	
}
