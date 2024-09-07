package gasto;

import java.time.LocalDate;
import java.util.Objects;

public class Pago {
    private LocalDate fecha;
    private double importe;
    private Persona transfiere;
    private Persona recibe;

    public Pago(LocalDate fecha, double importe, Persona transfiere, Persona recibe) {
        this.fecha = fecha;
        this.importe = importe;
        this.transfiere = transfiere;
        this.recibe = recibe;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Persona getTransfiere() {
        return transfiere;
    }

    public void setTransfiere(Persona transfiere) {
        this.transfiere = transfiere;
    }

    public Persona getRecibe() {
        return recibe;
    }

    public void setRecibe(Persona recibe) {
        this.recibe = recibe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pago pago)) return false;
        return Double.compare(importe, pago.importe) == 0 && Objects.equals(fecha, pago.fecha) && Objects.equals(transfiere, pago.transfiere) && Objects.equals(recibe, pago.recibe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, importe, transfiere, recibe);
    }

    @Override
    public String toString() {
        return "Pago{" +
                "fecha=" + fecha +
                ", importe=" + importe +
                ", transfiere=" + transfiere +
                ", recibe=" + recibe +
                '}';
    }
}
