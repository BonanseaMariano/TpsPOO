package carlosfontela.cuentas;

public class CajaAhorro extends CuentaBancaria {

    private double interesesGanados;

    public CajaAhorro(int numero, Cliente titular) {
        super(numero, titular);
        this.interesesGanados = 0;
    }


    public double getInteresesGanados() {
        return interesesGanados;
    }

    public void setInteresesGanados(double valor) {
        interesesGanados = valor;
    }

    public void pagarIntereses() {
        setSaldo(getSaldo() + interesesGanados);
        interesesGanados = 0;
    }

    @Override
    public void extraer(double monto) throws SaldoInsuficienteException {
        if (getSaldo() < monto) {
            throw new SaldoInsuficienteException("Saldo: " + getSaldo());
        }
        setSaldo(getSaldo() - monto);
    }

    @Override
    public double saldoDisponible() {
        return getSaldo();
    }
}
