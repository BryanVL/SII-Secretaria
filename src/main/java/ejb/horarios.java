package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazImportar;

/**
 * Session Bean implementation class horarios
 */
@Stateless
@LocalBean
public class horarios implements InterfazImportar{

    /**
     * Default constructor. 
     */
    public horarios() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void Importar(String dir) throws ImportarException {
		// TODO Auto-generated method stub
		
	}

}
