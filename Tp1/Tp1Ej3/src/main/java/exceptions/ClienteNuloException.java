package exceptions;

public class ClienteNuloException extends RuntimeException {
    public ClienteNuloException() {
        super("El cliente no puede ser nulo");
    }
}
