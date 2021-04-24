package interfacesEJB;

import javax.ejb.Local;

import jpa.Encuesta;

@Local
public interface InterfazEncuesta {
	public String conseguirAsignaturasIngles(Encuesta encuesta);
}
