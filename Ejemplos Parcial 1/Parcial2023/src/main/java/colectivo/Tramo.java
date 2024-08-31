package colectivo;

import java.util.Objects;

public class Tramo {
    private double precio;
    private Localidad origen;
    private Localidad destino;

    public Tramo(double precio, Localidad origen, Localidad destino) {
        this.precio = precio;
        this.origen = origen;
        this.destino = destino;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Localidad getOrigen() {
        return origen;
    }

    public void setOrigen(Localidad origen) {
        this.origen = origen;
    }

    public Localidad getDestino() {
        return destino;
    }

    public void setDestino(Localidad destino) {
        this.destino = destino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tramo tramo)) return false;
        return Double.compare(precio, tramo.precio) == 0 && Objects.equals(origen, tramo.origen) && Objects.equals(destino, tramo.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(precio, origen, destino);
    }

    @Override
    public String toString() {
        return "Tramo{" +
                "precio=" + precio +
                ", origen=" + origen +
                ", destino=" + destino +
                '}';
    }
}
