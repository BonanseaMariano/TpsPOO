package carlosfontela.cuentas;

public class ClienteMaxCuentasException extends Exception {
    public ClienteMaxCuentasException() {
        super("El cliente ya tiene la cantidad maxima de cuentas");
    }
}
