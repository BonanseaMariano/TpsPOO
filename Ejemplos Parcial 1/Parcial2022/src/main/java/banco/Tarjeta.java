package banco;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Tarjeta implements OperacionBanco {
    private int numeroTarjeta;
    private LocalDate validaDesde;
    private LocalDate validaHasta;
    private boolean bloqueada;
    private Titular titular;
    private CajaAhorro cajaAhorro;

    public Tarjeta(CajaAhorro cajaAhorro, int numeroTarjeta, Titular titular, LocalDate validaDesde, LocalDate validaHasta) {
        if (!cajaAhorro.esTitular(titular)) {
            throw new TitularNoValidoException(titular + " no es titular de la caja de ahorro");
        }
        this.numeroTarjeta = numeroTarjeta;
        this.validaDesde = validaDesde;
        this.validaHasta = validaHasta;
        this.titular = titular;
        this.cajaAhorro = cajaAhorro;
        this.cajaAhorro.agregarTarjeta(this);
    }

    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public LocalDate getValidaDesde() {
        return validaDesde;
    }

    public void setValidaDesde(LocalDate validaDesde) {
        this.validaDesde = validaDesde;
    }

    public LocalDate getValidaHasta() {
        return validaHasta;
    }

    public void setValidaHasta(LocalDate validaHasta) {
        this.validaHasta = validaHasta;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public CajaAhorro getCajaAhorro() {
        return cajaAhorro;
    }

    public void setCajaAhorro(CajaAhorro cajaAhorro) {
        this.cajaAhorro = cajaAhorro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarjeta tarjeta)) return false;
        return numeroTarjeta == tarjeta.numeroTarjeta;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroTarjeta);
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "numeroTarjeta=" + numeroTarjeta +
                ", validaDesde=" + validaDesde +
                ", validaHasta=" + validaHasta +
                ", bloqueada=" + bloqueada +
                ", titular=" + titular +
                ", cajaAhorro=" + cajaAhorro +
                '}';
    }

    public abstract void realizarPago(LocalDate fecha, String detalle, double importe) throws SaldoInsuficienteException;

    public void verificarTarjeta() throws TarjetaInvalidaException {
        if (LocalDate.now().isBefore(validaDesde) || LocalDate.now().isAfter(validaHasta) || isBloqueada()) {
            throw new TarjetaInvalidaException();
        }
    }
}
