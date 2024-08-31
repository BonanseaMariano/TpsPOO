package banco;

import java.time.LocalDate;

public class Movimiento {
    private LocalDate fecha;
    private String detalle;
    private double importe;

    public Movimiento(LocalDate fecha, String detalle, double importe) {
        this.fecha = fecha;
        this.detalle = detalle;
        this.importe = importe;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "fecha=" + fecha +
                ", detalle='" + detalle + '\'' +
                ", importe=" + importe +
                '}';
    }
}
