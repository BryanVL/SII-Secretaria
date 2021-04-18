package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import excepcionesEJB.GrupoException;
import interfacesEJB.InterfazCRUDgrupo;
import jpa.Grupo;

@Stateless
@LocalBean
public class GrupoImpl implements InterfazCRUDgrupo{

    @PersistenceContext(unitName = "Secretaria")
    private EntityManager em;

	@Override
	public void Crear(Grupo g) throws GrupoException {
		// TODO Auto-generated method stub
		Grupo grupoExistente = em.find(Grupo.class, g.getID());
		if (grupoExistente != null) {
			throw new GrupoException();
		}
		em.persist(g);
	}

	@Override
	public void Leer(Grupo g) throws GrupoException {
		// TODO Auto-generated method stub
		Grupo grupoExistente = em.find(Grupo.class, g.getID());
		if (grupoExistente != null) {
			throw new GrupoException();
		}
		System.out.println(g);
	}

	@Override
	public void Borrar(Grupo g) throws GrupoException {
		// TODO Auto-generated method stub
		Grupo grupoExistente = em.find(Grupo.class, g.getID());
		if (grupoExistente == null) {
			throw new GrupoException();
		}
		em.remove(em.merge(g));
	}

	@Override
	public void Actualizar(Grupo g) throws GrupoException {
		// TODO Auto-generated method stub
		Grupo grupoExistente = em.find(Grupo.class, g.getID());
		if (grupoExistente == null) {
			throw new GrupoException();
		}
		em.merge(g);
	}
    
}
