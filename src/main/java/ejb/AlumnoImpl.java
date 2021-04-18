package ejb;


import java.net.URI;
import java.util.Random;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import excepcionesEJB.AlumnoException;
import excepcionesEJB.ImportarException;
import interfacesEJB.InterfazAlumno;
import interfacesEJB.InterfazImportar;
import jpa.Alumno;



@Stateless
@LocalBean
public class AlumnoImpl implements InterfazImportar,InterfazAlumno{
    @PersistenceContext(unitName = "Secretaria")
    private EntityManager em;

	@Override
	public void Importar(String dir) throws ImportarException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validarAcceso(String dir) throws AlumnoException {
		// TODO Auto-generated method stub
		
	}
	
}
