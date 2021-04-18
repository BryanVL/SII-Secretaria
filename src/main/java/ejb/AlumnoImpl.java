package ejb;


import java.net.URI;
import java.util.Random;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazImportar;
import jpa.Alumno;



@Stateless
public class AlumnoImpl implements InterfazImportar{
    @PersistenceContext(unitName = "Alumno")
    private EntityManager em;

	@Override
	public void Importar(String dir) throws ImportarException {
		// TODO Auto-generated method stub
		
	}
}
