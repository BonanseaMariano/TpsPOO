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
    public boolean extraer(double monto) {
        double saldo = super.getSaldo();
        if (monto > saldo)
            return false;
        else {
            saldo -= monto;
            super.setSaldo(saldo);
            return true;
        }
    }
}
