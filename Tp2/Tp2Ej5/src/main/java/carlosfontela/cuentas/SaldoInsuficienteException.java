package carlosfontela.cuentas;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException() {
        super("Saldo Insuficiente para realizar la operación");
    }
}
