package excepcionesEJB;

public class UsuarioException extends Exception {

    public UsuarioException() {
    }

    public UsuarioException(String msg) {
        super(msg);
    }
}
