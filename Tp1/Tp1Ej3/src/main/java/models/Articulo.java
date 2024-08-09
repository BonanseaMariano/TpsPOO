package models;

import java.util.Objects;

public class Articulo {
    private int codigo;
    private double precio;
    private String descripcion;
    private int cantidad;

    public Articulo(int codigo, double precio, String descripcion, int cantidad) {
        this.codigo = codigo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
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
