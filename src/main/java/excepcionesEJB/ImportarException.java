package excepcionesEJB;

public class ImportarException extends RuntimeException{

    public ImportarException() {
    }

    public ImportarException(String msg) {
        super(msg);
    }
	
}
