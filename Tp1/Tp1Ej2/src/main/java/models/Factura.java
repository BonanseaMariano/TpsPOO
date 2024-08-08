package models;

import exceptions.ArticuloRepetidoException;
import exceptions.StockInsuficienteException;

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

        public ItemFactura(Articulo articulo, int cantidad, double precio) {
            this.articulo = articulo;
            this.cantidad = cantidad;
            this.precio = precio;
        }

        public Articulo getArticulo() {
            return articulo;
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
        itemFacturas.add(new ItemFactura(articulo, cantidad, articulo.getPrecio()));
    }

    public void agregarItemFactura(Articulo articulo, int cantidad, double precio) throws ArticuloRepetidoException, StockInsuficienteException {
        if (articulo.getCantidad() < cantidad) {
            throw new StockInsuficienteException();
        }
        for (ItemFactura item : itemFacturas) {
            if (item.getArticulo().equals(articulo)) {
                throw new ArticuloRepetidoException();
            }
        }
        itemFacturas.add(new ItemFactura(articulo, cantidad, precio));
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

}
