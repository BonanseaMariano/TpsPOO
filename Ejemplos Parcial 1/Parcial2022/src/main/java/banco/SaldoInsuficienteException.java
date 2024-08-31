package banco;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException() {
        super("saldo insuficiente");
    }
}
