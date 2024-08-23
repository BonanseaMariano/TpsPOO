package carlosfontela.cuentas;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuentaBancaria that)) return false;
        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }

    @Override
    public String toString() {
        return "numero=" + numero +
                ", titular=" + titular +
                ", saldo=" + saldo;
    }
}
