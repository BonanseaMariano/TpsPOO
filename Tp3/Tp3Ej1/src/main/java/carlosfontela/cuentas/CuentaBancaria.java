package carlosfontela.cuentas;

public abstract class CuentaBancaria implements OperacionBanco {

    private int numero;
    private Cliente titular;
    private double saldo;

    public CuentaBancaria(int numero, Cliente titular) {
        titular.agregarCuenta(this);
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0;
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public abstract void extraer(double monto) throws SaldoInsuficienteException;

    public abstract double saldoDisponible();

    @Override
    public double obtenerSaldoDisponible() {
        return saldoDisponible();
    }

}
