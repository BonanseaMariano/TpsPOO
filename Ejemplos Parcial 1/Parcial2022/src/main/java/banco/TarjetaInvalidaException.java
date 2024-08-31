package banco;

public class TarjetaInvalidaException extends RuntimeException {
    public TarjetaInvalidaException() {
        super("tarjeta invalida");
    }
}
