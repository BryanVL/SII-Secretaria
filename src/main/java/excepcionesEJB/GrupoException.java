package excepcionesEJB;


public class GrupoException extends RuntimeException{

    public GrupoException() {
    }

    public GrupoException(String msg) {
        super(msg);
    }
	
}
