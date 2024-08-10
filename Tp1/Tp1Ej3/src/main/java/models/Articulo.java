package models;

import java.util.Objects;

public class Articulo {
    private int codigo;
    private double precio;
    private String descripcion;
    private int cantidad;

    public Articulo(int codigo, String descripcion, double precio, int cantidad) {
        this.codigo = codigo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Articulo articulo)) return false;
        return codigo == articulo.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "codigo=" + codigo +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
