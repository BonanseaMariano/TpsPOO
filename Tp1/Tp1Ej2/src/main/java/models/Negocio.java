package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public int getCUIL() {
        return CUIL;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public List<Factura> getFacturas() {
        return facturas;
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


}
