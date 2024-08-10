package models;

import exceptions.ArticuloRepetidoException;
import exceptions.ClienteNuloException;
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
    private Cliente cliente;
    private boolean ctaCte;

    public Factura(int numeroFactura, LocalDate fecha, Cliente cliente, Articulo articulo, int cantidad, boolean ctaCte) throws ClienteNuloException {
        this.numeroFactura = numeroFactura;
        this.fecha = fecha;
        this.itemFacturas = new ArrayList<>();
        this.agregarItem(articulo, cantidad);
        if (ctaCte && cliente == null) {
            throw new ClienteNuloException();
        }
        this.cliente = cliente;
        this.ctaCte = ctaCte;
    }

    public Factura(int numeroFactura, LocalDate fecha, Articulo articulo, int cantidad) {
        this(numeroFactura, fecha, null, articulo, cantidad, false);
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

    public boolean isCtaCte() {
        return ctaCte;
    }

    public Cliente getCliente() {
        return cliente;
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
