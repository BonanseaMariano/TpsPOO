package banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CajaAhorro implements OperacionBanco {
    private int nroCuenta;
    private double saldo;
    private List<Titular> titulares;
    private List<Tarjeta> tarjetas;

    public CajaAhorro(int nroCuenta, Titular titular) {
        this.nroCuenta = nroCuenta;
        this.titulares = new ArrayList<>();
        this.tarjetas = new ArrayList<>();
        agregarTitular(titular);
    }

    public void agregarTitular(Titular titular) {
        titulares.add(titular);
    }

    public int getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(int nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void agregarTarjeta(Tarjeta tarjeta) {
        tarjetas.add(tarjeta);
    }

    public List<Titular> getTitulares() {
        return titulares;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CajaAhorro that)) return false;
        return nroCuenta == that.nroCuenta;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nroCuenta);
    }

    @Override
    public String toString() {
        return "CajaAhorro{" +
                "nroCuenta=" + nroCuenta +
                ", saldo=" + saldo +
                ", titulares=" + titulares +
                ", tarjetas=" + tarjetas +
                '}';
    }

    @Override
    public double obtenerSaldo() {
        return this.saldo;
    }

    public boolean esTitular(Titular titular) {
        return titulares.contains(titular);
    }

    public int cantidadTarjetas() {
        return tarjetas.size();
    }

    public boolean contieneTarjeta(Tarjeta td1) {
        return tarjetas.contains(td1);
    }
}
