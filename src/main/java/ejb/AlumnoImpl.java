package ejb;


import java.net.URI;
import java.util.Random;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import excepcionesEJB.AlumnoException;
import interfacesEJB.InterfazAlumno;
import jpa.Alumno;



@Stateless
public class AlumnoImpl implements InterfazAlumno{
    @PersistenceContext(unitName = "Alumno")
    private EntityManager em;

	@Override
	public void Importar_Alumno(Alumno a) throws AlumnoException {
		// TODO Auto-generated method stub
		
	}
}
