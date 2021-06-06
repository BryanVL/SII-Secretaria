package interfacesEJB;

import java.util.List;

import javax.ejb.Local;

import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import jpa.Titulacion;

@Local
public interface InterfazTitulacion {

	
	/**Este método es para visualizar la titulacion
	 * @param codigo Titulacion a visualizar*/
	public Titulacion VisualizarTitulacion(Integer codigo) throws TitulacionException;

	public List<Titulacion> mostrarDatosAdmin() throws TitulacionException;
	
	public void borrarTitulaciones() throws TitulacionException;
	
	public void Importar(String dir) throws ImportarException;
	
}