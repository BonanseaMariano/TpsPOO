package test;

import exceptions.ArticuloRepetidoException;
import exceptions.StockInsuficienteException;
import models.Articulo;
import models.Negocio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NegocioTest {
    private static Negocio negocio;
    private static final Articulo a1 = new Articulo(1, 1000, "Martillo", 2);
    private static final Articulo a2 = new Articulo(2, 1500, "Destornillador", 2);
    private static final Articulo a3 = new Articulo(2, 2500, "Taladro", 1);

    @BeforeAll
    static void setUp() {
        negocio = new Negocio("La mejor ferreteria", 12345678);
        negocio.agregarArticulo(a1.getCodigo(), a1.getPrecio(), a1.getDescripcion(), a1.getCantidad());
        negocio.agregarArticulo(a2.getCodigo(), a2.getPrecio(), a2.getDescripcion(), a2.getCantidad());
    }

    @Test
    void agregarItemFacturaTest() {

        negocio.agregarFactura(10, LocalDate.now(), a1, 1);

        assertDoesNotThrow(() -> negocio.getFacturas().getFirst().agregarItemFactura(a2, 1, a2.getPrecio()));

        assertThrows(ArticuloRepetidoException.class, () -> negocio.getFacturas().getFirst().agregarItemFactura(a1, 1, a1.getPrecio()));

        assertThrows(StockInsuficienteException.class, () -> negocio.getFacturas().getFirst().agregarItemFactura(a3, 2, a1.getPrecio()));

    }

    @Test
    void cambiarPrecioTest() {
        negocio.agregarFactura(10, LocalDate.now(), a1, 1);
        System.out.println("Precios originales:");
        for (Articulo articulo : negocio.getArticulos()) {
            System.out.println(articulo);
        }
        System.out.println("Stock valorizado original: " + negocio.stockValorizado());
        negocio.cambiarPrecio(0.5);
        System.out.println("Precios aumentados un 50%:");
        for (Articulo articulo : negocio.getArticulos()) {
            System.out.println(articulo);
        }
        System.out.println("Stock valorizado aumentado un 50%: " + negocio.stockValorizado());

    }
}