package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Articulo;
import exceptions.ArticuloRepetidoException;
import models.Factura;
import models.Negocio;
import exceptions.StockInsuficienteException;

public class TestFerreteria2 {

    private Negocio ferreteria = new Negocio("30-37148498-8", "Ferreteria");

    private Articulo des, mar, pin;
    private Factura factura1, factura2, factura3, factura4;

    @Before
    public void antesDelTest() {

        des = ferreteria.agregarArticulo(10010, "destornillador", 150.20, 30);
        mar = ferreteria.agregarArticulo(10020, "martillo", 250.00, 20);
        pin = ferreteria.agregarArticulo(10030, "pinza", 350.00, 5);

        factura1 = ferreteria.agregarFactura(100, LocalDate.of(2022, 8, 1), des, 4);
        factura1.agregarItem(mar, 1);
        factura1.agregarItem(pin, 2);

        factura2 = ferreteria.agregarFactura(101, LocalDate.of(2022, 8, 2), des, 10);
        factura2.agregarItem(mar, 2);

        factura3 = ferreteria.agregarFactura(102, LocalDate.of(2022, 8, 3), des, 5);

        factura4 = ferreteria.agregarFactura(103, LocalDate.of(2022, 8, 4), mar, 2);

    }

    @After
    public void despuesDelTest() {
        // System.out.println("Sale del test");
    }

    @Test
    public void importeFactura() {
        assertEquals(factura1.importeTotal(), 1550.80, 0.01);
        assertEquals(factura2.importeTotal(), 2002.00, 0.01);
        assertEquals(factura3.importeTotal(), 751.00, 0.01);
        assertEquals(factura4.importeTotal(), 500.00, 0.01);
    }

    // cambio precio art�culo no cambia el importe de lo facturado
    @Test
    public void cambioPrecio() {
        mar.setPrecio(275.00);
        assertEquals(factura4.importeTotal(), 500.00, 0.01);
    }

    // articulo ya ingresado
    @Test(expected = ArticuloRepetidoException.class)
    public void articuloIngresado() {
        factura4.agregarItem(mar, 2);
    }

    // articulo sin stock
     @Test(expected = StockInsuficienteException.class)
//    @Test
    public void stockInsuficiente() {
//        try {
            factura4.agregarItem(pin, 5);
//        } catch (StockInsuficienteException e) {
//            System.out.println(e);
            // modelo.StockInsuficienteException: No hay stock suficiente, quedan 3 pinza
//        }
    }

}
