package carlosfontela.cuentas;

public class CajaAhorro extends CuentaBancaria {

    private double interesesGanados;
    private static double comisionCA;

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

    public static double getComisionCA() {
        return comisionCA;
    }

    public static void setComisionCA(double comisionCA) {
        CajaAhorro.comisionCA = comisionCA;
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

    @Override
    public double obtenerComision() {
        return CajaAhorro.getComisionCA();
    }
}
