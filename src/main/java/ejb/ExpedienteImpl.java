package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import excepcionesEJB.GrupoException;
import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazExpediente;
import interfacesEJB.InterfazImportar;
import jpa.Expediente;
import jpa.Grupo;

@Stateless
@LocalBean
public class ExpedienteImpl implements InterfazImportar,InterfazExpediente{

	@PersistenceContext(name="Secretaria")
	private EntityManager em;
	
	@Override
	public void Importar(String dir) throws ImportarException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void VisualizarExpediente(Expediente e) throws ImportarException {
		// TODO Auto-generated method stub
		Expediente expedienteExistente = em.find(Expediente.class, e.getNum_expediente());
		if (expedienteExistente == null) {
			throw new GrupoException();
		}
		System.out.println(e);
	}
	
	
}
