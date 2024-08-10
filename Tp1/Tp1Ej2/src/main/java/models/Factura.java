package models;

import exceptions.ArticuloRepetidoException;
import exceptions.StockInsuficienteException;
import jdk.jshell.spi.SPIResolutionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Factura {
    //Inner-Class
    private class ItemFactura {
        private Articulo articulo;
        private int cantidad;
        private double precio;

        public ItemFactura(Articulo articulo, int cantidad) {
            this.articulo = articulo;
            this.cantidad = cantidad;
            this.precio = articulo.getPrecio();
        }

        public Articulo getArticulo() {
            return articulo;
        }

        public int getCantidad() {
            return cantidad;
        }

        public double getPrecio() {
            return precio;
        }
    }

    //Atributos
    private int numeroFactura;
    private LocalDate fecha;
    private List<ItemFactura> itemFacturas;

    public Factura(int numeroFactura, LocalDate fecha, Articulo articulo, int cantidad) {
        this.numeroFactura = numeroFactura;
        this.fecha = fecha;
        this.itemFacturas = new ArrayList<>();
        itemFacturas.add(new ItemFactura(articulo, cantidad));
    }

    public void agregarItem(Articulo articulo, int cantidad) throws ArticuloRepetidoException, StockInsuficienteException {
        if (articulo.getCantidad() < cantidad) {
            throw new StockInsuficienteException();
        }
        for (ItemFactura item : itemFacturas) {
            if (item.getArticulo().equals(articulo)) {
                throw new ArticuloRepetidoException();
            }
        }
        itemFacturas.add(new ItemFactura(articulo, cantidad));
        articulo.setCantidad(articulo.getCantidad() - cantidad);
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public List<ItemFactura> getItemFacturas() {
        return itemFacturas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Factura factura)) return false;
        return numeroFactura == factura.numeroFactura;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroFactura);
    }

    public double importeTotal() {
        double total = 0;
        for (ItemFactura item : itemFacturas) {
            total += item.getPrecio() * item.getCantidad();
        }
        return total;
    }
}
