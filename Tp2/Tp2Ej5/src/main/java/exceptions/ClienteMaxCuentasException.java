package exceptions;

public class ClienteMaxCuentasException extends RuntimeException {
    public ClienteMaxCuentasException() {
        super("El cliente ya tiene la cantidad maxima de cuentas");
    }
}
