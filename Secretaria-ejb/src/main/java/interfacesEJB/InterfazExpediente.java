package interfacesEJB;

import java.util.List;

import javax.ejb.Local;

import excepcionesEJB.AsignaturaException;
import excepcionesEJB.ExpedienteException;
import excepcionesEJB.ImportarException;
import excepcionesEJB.TitulacionException;
import jpa.Asignatura;
import jpa.Expediente;


@Local
public interface InterfazExpediente {

	/**Este método es para visualizar el expediente
	 * @param e expediente a visualizar*/
	public Expediente VisualizarExpediente(Integer num_expediente) throws ExpedienteException;
	
	public List<Expediente> mostrarDatosAdmin() throws ExpedienteException;
	
	public void borrarExpedientes() throws ExpedienteException;
	
	public void Importar(String dir) throws ImportarException;
}
