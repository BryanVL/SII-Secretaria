package interfacesEJB;

import javax.ejb.Local;

import excepcionesEJB.ImportarException;


@Local
public interface InterfazImportar {
	
	/**Este metodo debe leer un archivo excel dado la dirección donde 
	 * se encuentra e importar los datos del mismo a las clases correspondientes
	 * @param dir dirección donde se encuentra el excel*/
	public void Importar(String dir) throws ImportarException;
	
}
