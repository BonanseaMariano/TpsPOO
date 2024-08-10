package exceptions;

public class ProfesorRepetidoException extends RuntimeException {
    public ProfesorRepetidoException() {
        super("Profesor ya existente");
    }
}
