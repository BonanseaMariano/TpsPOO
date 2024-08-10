package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Articulo;
import models.Cliente;
import exceptions.ClienteNuloException;
import models.Factura;
import models.Negocio;

public class TestFerreteria3 {

    private Negocio ferreteria = new Negocio("30-37148498-8", "Ferreteria");

    private Articulo des, mar, pin;
    private Cliente cliente1, cliente2;
    private Factura factura1, factura2, factura3, factura4;

    @Before
    public void antesDelTest() {

        des = ferreteria.agregarArticulo(10010, "destornillador", 150.20, 30);
        mar = ferreteria.agregarArticulo(10020, "martillo", 250.00, 20);
        pin = ferreteria.agregarArticulo(10030, "pinza", 350.00, 5);

        cliente1 = ferreteria.agregarCliente(100, "Juan", "20-24545789-3", "280-421-2322", "Roca 245");
        cliente2 = ferreteria.agregarCliente(102, "Infa", "30-63062533-1", "445-9500", "Ruta 4, km 2");

        factura1 = ferreteria.agregarFactura(100, LocalDate.of(2022, 8, 1), cliente1, des, 4, false);
        factura1.agregarItem(mar, 1);
        factura1.agregarItem(pin, 2);

        factura2 = ferreteria.agregarFactura(101, LocalDate.of(2022, 8, 2), des, 10);
        factura2.agregarItem(mar, 2);

        factura3 = ferreteria.agregarFactura(102, LocalDate.of(2022, 8, 3), cliente2, des, 5, true);

        factura4 = ferreteria.agregarFactura(103, LocalDate.of(2022, 8, 4), cliente2, mar, 2, true);

    }

    @After
    public void despuesDelTest() {
        // System.out.println("Sale del test");
    }


    @Test(expected = ClienteNuloException.class)
    public void ventaCtaCteSinCliente() {
        ferreteria.agregarFactura(104, LocalDate.of(2022, 8, 5), null, mar, 2, true);
    }

    @Test
    public void totalFacturado() {
        assertEquals(ferreteria.totalFacturado(LocalDate.of(2022, 8, 1), LocalDate.of(2022, 8, 31)), 4803.8, 0.01);
        assertEquals(ferreteria.totalFacturado(LocalDate.of(2022, 8, 2), LocalDate.of(2022, 8, 2)), 2002, 0.01);
        assertEquals(ferreteria.totalFacturado(LocalDate.of(2022, 8, 2), LocalDate.of(2022, 8, 3)), 2753, 0.01);
    }

    @Test
    public void totalFacturadoCtaCte() {
        assertEquals(ferreteria.totalFacturadoCtaCte(LocalDate.of(2022, 8, 1), LocalDate.of(2022, 8, 31)), 1251, 0.01);
    }

    @Test
    public void totalFacturadoCliente() {
        assertEquals(ferreteria.totalFacturadoCliente(LocalDate.of(2022, 8, 1), LocalDate.of(2022, 8, 31), cliente1),
                1550.8, 0.01);
        assertEquals(ferreteria.totalFacturadoCliente(LocalDate.of(2022, 8, 1), LocalDate.of(2022, 8, 31), cliente2),
                1251, 0.01);
    }

    @Test
    public void totalFacturadoClienteCtaCte() {
        assertEquals(
                ferreteria.totalFacturadoClienteCtaCte(LocalDate.of(2022, 8, 1), LocalDate.of(2022, 8, 31), cliente1),
                0.0, 0.01);
        assertEquals(ferreteria.totalFacturadoClienteCtaCte(LocalDate.of(2022, 8, 1), LocalDate.of(2022, 8, 31),
                new Cliente(102, "", "", "", "")), 1251, 0.01);
    }

    @Test
    public void stockValorizado() {
        assertEquals(ferreteria.stockValorizado(), 6452.2, 0.01);
    }

    // Si se ejecuta primero dejan de andar los otros test?
    // Cuantas instancias se crean de la clase Negocio?
    @Test
    public void cambiarPrecio() {
        ferreteria.cambiarPrecio(10);
        assertEquals(ferreteria.stockValorizado(), 7097.42, 0.01);
    }

}
