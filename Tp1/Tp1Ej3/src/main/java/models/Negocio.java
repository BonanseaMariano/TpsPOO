package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Negocio {
    private String nombre;
    private int CUIL;
    private List<Articulo> articulos;
    private List<Factura> facturas;

    public Negocio(String nombre, int CUIL) {
        this.nombre = nombre;
        this.CUIL = CUIL;
        this.articulos = new ArrayList<>();
        this.facturas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCUIL() {
        return CUIL;
    }

    public void setCUIL(int CUIL) {
        this.CUIL = CUIL;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Negocio negocio)) return false;
        return CUIL == negocio.CUIL;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(CUIL);
    }

    @Override
    public String toString() {
        return "Negocio{" +
                "nombre='" + nombre + '\'' +
                ", CUIL=" + CUIL +
                ", articulos=" + articulos +
                ", facturas=" + facturas +
                '}';
    }

    public void agregarFactura(int numeroFactura, LocalDate fecha, Articulo articulo, int cantidad) {
        facturas.add(new Factura(numeroFactura, fecha, articulo, cantidad));
    }

    public void agregarArticulo(int codigo, double precio, String descripcion, int cantidad) {
        articulos.add(new Articulo(codigo, precio, descripcion, cantidad));
    }

    /**
     * Cambia en un porcentaje el precio de todos los artículos del stock
     *
     * @param porcCambio: porcentaje de cambio de precio
     */
    public void cambiarPrecio(double porcCambio) {
        for (Articulo articulo : articulos) {
            double precio = articulo.getPrecio();
            articulo.setPrecio(precio + (precio * porcCambio));
        }
    }

    /**
     * Retorna el valor total del stock multiplicando la cantidad existente por el
     * precio
     *
     * @return stock valorizado
     */
    public double stockValorizado() {
        double valorStock = 0;
        for (Articulo articulo : articulos) {
            valorStock += articulo.getPrecio() * articulo.getCantidad();
        }
        return valorStock;
    }

    public void totalEntreFechas(LocalDate fecha1, LocalDate fecha2) {
        double total = 0;
        for (Factura factura : facturas) {
            if (factura.getFecha().isAfter(fecha1) && factura.getFecha().isBefore(fecha2)) {
                for (Factura.ItemFactura item : factura.getItemFacturas()) {
                    total += item.getArticulo().getPrecio() * item.getCantidad();
                }
            }
        }
    }

    public void totalEntreFechas(LocalDate fecha1, LocalDate fecha2, Cliente cliente) {
        for (Factura factura : facturas) {
            if (factura.getFecha().isAfter(fecha1) && factura.getFecha().isBefore(fecha2)) {
            }
        }
    }


}