package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import interfacesEJB.InterfazEncuesta;
import jpa.Encuesta;

@Stateless
@LocalBean
public class EncuestaImpl implements InterfazEncuesta{

	@Override
	public String conseguirAsignaturasIngles(Encuesta encuesta) {
		Boolean res = encuesta.getAsignatura_ingles();
		return res.toString();
	}

}
