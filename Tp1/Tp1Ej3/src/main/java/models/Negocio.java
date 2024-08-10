package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Negocio {
    private String nombre;
    private String CUIL;
    private List<Articulo> articulos;
    private List<Factura> facturas;
    private List<Cliente> clientes;

    public Negocio(String CUIL, String nombre) {
        this.nombre = nombre;
        this.CUIL = CUIL;
        this.articulos = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public Factura agregarFactura(int numeroFactura, LocalDate fecha, Cliente cliente, Articulo articulo, int cantidad, boolean ctaCte) {
        Factura factura = new Factura(numeroFactura, fecha, cliente, articulo, cantidad, ctaCte);
        facturas.add(factura);
        return factura;
    }

    public Factura agregarFactura(int numeroFactura, LocalDate fecha, Articulo articulo, int cantidad) {
        Factura factura = new Factura(numeroFactura, fecha, articulo, cantidad);
        facturas.add(factura);
        return factura;
    }

    public Articulo agregarArticulo(int codigo, String descripcion, double precio, int cantidad) {
        Articulo articulo = new Articulo(codigo, descripcion, precio, cantidad);
        articulos.add(articulo);
        return articulo;
    }

    public Cliente agregarCliente(int codigo, String nombre, String CUIT, String telefono, String direccion) {
        Cliente cliente = new Cliente(codigo, nombre, CUIT, telefono, direccion);
        return cliente;
    }

    /**
     * Cambia en un porcentaje el precio de todos los artículos del stock
     *
     * @param porcCambio: porcentaje de cambio de precio
     */
    public void cambiarPrecio(double porcCambio) {
        for (Articulo articulo : articulos) {
            double precio = articulo.getPrecio();
            articulo.setPrecio(precio + ((precio * porcCambio) / 100));
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

    public double totalFacturado(LocalDate f1, LocalDate f2) {
        double total = 0;
        for (Factura factura : facturas) {
            if ((factura.getFecha().isAfter(f1) || factura.getFecha().isEqual(f1)) && (factura.getFecha().isBefore(f2)) || (factura.getFecha().isEqual(f2))) {
                total += factura.importeTotal();
            }
        }
        return total;
    }

    public double totalFacturadoCtaCte(LocalDate f1, LocalDate f2) {
        double total = 0;
        for (Factura factura : facturas) {
            if (factura.isCtaCte() && (factura.getFecha().isAfter(f1) || factura.getFecha().isEqual(f1)) && (factura.getFecha().isBefore(f2)) || (factura.getFecha().isEqual(f2))) {
                total += factura.importeTotal();
            }
        }
        return total;
    }

    public double totalFacturadoCliente(LocalDate f1, LocalDate f2, Cliente cliente) {
        double total = 0;
        for (Factura factura : facturas) {
            if (factura.getCliente() != null && factura.getCliente().equals(cliente) && (factura.getFecha().isAfter(f1) || factura.getFecha().isEqual(f1)) && (factura.getFecha().isBefore(f2)) || (factura.getFecha().isEqual(f2))) {
                total += factura.importeTotal();
            }
        }
        return total;
    }

    public double totalFacturadoClienteCtaCte(LocalDate f1, LocalDate f2, Cliente cliente) {
        double total = 0;
        for (Factura factura : facturas) {
            if (factura.isCtaCte() && factura.getCliente().equals(cliente) && factura.getFecha().isAfter(f1) && factura.getFecha().isBefore(f2)) {
                total += factura.importeTotal();
            }
        }
        return total;
    }
}
