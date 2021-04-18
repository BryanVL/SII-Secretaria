package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazImportar;

@Stateless
@LocalBean
public class ExpedienteImpl implements InterfazImportar{

	@PersistenceContext(name="Secretaria")
	private EntityManager em;
	
	@Override
	public void Importar(String dir) throws ImportarException {
		// TODO Auto-generated method stub
		
	}
}
