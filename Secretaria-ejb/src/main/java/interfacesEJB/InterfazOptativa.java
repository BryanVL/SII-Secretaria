package interfacesEJB;

import java.util.List;

import javax.ejb.Local;

import excepcionesEJB.ImportarException;
import excepcionesEJB.OptativaException;
import jpa.Asignatura;
import jpa.Optativa;

@Local
public interface InterfazOptativa {

	/**Este método es para visualizar el expediente
	 * @param e expediente a visualizar*/
	public Optativa VisualizarOptativa(Integer referencia) throws OptativaException;

	public List<Asignatura> mostrarDatosAdmin() throws OptativaException;
	
	public void borrarOptativas() throws OptativaException;
	
	public void Importar(String dir) throws ImportarException;
	
}
