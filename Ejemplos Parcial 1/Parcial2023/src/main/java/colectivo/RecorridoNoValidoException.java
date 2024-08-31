package colectivo;

public class RecorridoNoValidoException extends RuntimeException {
    public RecorridoNoValidoException() {
        super("Recorrido no valido");
    }
}
