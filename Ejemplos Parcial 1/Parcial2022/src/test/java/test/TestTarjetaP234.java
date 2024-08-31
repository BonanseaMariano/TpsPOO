package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import banco.CajaAhorro;
import banco.SaldoInsuficienteException;
import banco.Tarjeta;
import banco.TarjetaCredito;
import banco.TarjetaDebito;
import banco.TarjetaInvalidaException;
import banco.Titular;
import banco.TitularNoValidoException;

public class TestTarjetaP234 {

    private Titular juan;
    private Titular ana;
    private Titular pedro;
    private CajaAhorro ca1, ca2;
    private Tarjeta tc1, tc2;
    private Tarjeta td1, td2;

    @Before
    public void setUp() throws Exception {
        juan = new Titular("Juan", "11111111");
        ana = new Titular("Ana", "22222222");
        pedro = new Titular("Pedro", "33333333");
        ca1 = new CajaAhorro(25, juan);
        ca1.agregarTitular(ana);
        ca1.setSaldo(7000);

        tc1 = new TarjetaCredito(ca1, 3000, ana, LocalDate.of(2021, 1, 1), LocalDate.of(2024, 12, 31), 7000);
        td1 = new TarjetaDebito(ca1, 4000, juan, LocalDate.of(2021, 1, 1), LocalDate.of(2024, 12, 31));
    }

    @Test
    public void testTD1() {
        td1.realizarPago(LocalDate.of(2022, 9, 2), "Servicoop", 2000);
        td1.realizarPago(LocalDate.of(2022, 9, 3), "Carrefour", 3000);
        assertEquals(ca1.getSaldo(), 7000 - 2000 - 3000, 0.01);
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void testTD2() {
        td1.realizarPago(LocalDate.of(2022, 9, 4), "Supercanal", 7200);
    }

    @Test
    public void testTC1() {
        tc1.realizarPago(LocalDate.of(2022, 9, 2), "Servicoop", 2000);
        tc1.realizarPago(LocalDate.of(2022, 9, 3), "Carrefour", 3000);
        tc1.realizarPago(LocalDate.of(2022, 9, 4), "Supercanal", 1500);
        assertEquals(tc1.obtenerSaldo(), 7000 - 2000 - 3000 - 1500, 0.01);
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void testTC2() {
        tc1.realizarPago(LocalDate.of(2022, 9, 2), "Servicoop", 2000);
        tc1.realizarPago(LocalDate.of(2022, 9, 3), "Carrefour", 3000);
        tc1.realizarPago(LocalDate.of(2022, 9, 4), "Supercanal", 1500);
        tc1.realizarPago(LocalDate.of(2022, 9, 4), "La Anonima", 1000);
    }

    @Test(expected = TarjetaInvalidaException.class)
    public void testTarjetaValidaTD1() {
        td2 = new TarjetaDebito(ca1, 7000, ana, LocalDate.of(2019, 1, 1), LocalDate.of(2021, 12, 31));
        td2.realizarPago(LocalDate.of(2022, 1, 1), "Servicoop", 2000);
    }

    @Test(expected = TarjetaInvalidaException.class)
    public void testTarjetaValidaTD2() {
        td2 = new TarjetaDebito(ca1, 7000, ana, LocalDate.of(2022, 10, 1), LocalDate.of(2023, 10, 1));
        td2.realizarPago(LocalDate.of(2022, 9, 1), "Servicoop", 2000);
    }

    @Test(expected = TarjetaInvalidaException.class)
    public void testTarjetaValidaTD3() {
        td2 = new TarjetaDebito(ca1, 7000, ana, LocalDate.of(2022, 1, 1), LocalDate.of(2024, 12, 31));
        td2.setBloqueada(true);
        td2.realizarPago(LocalDate.of(2023, 1, 1), "Servicoop", 2000);
    }

    @Test(expected = TarjetaInvalidaException.class)
    public void testTarjetaValidaTC1() {
        tc2 = new TarjetaCredito(ca1, 7000, ana, LocalDate.of(2019, 1, 1), LocalDate.of(2021, 12, 31), 7000);
        tc2.realizarPago(LocalDate.of(2022, 1, 1), "Servicoop", 2000);
    }

    @Test(expected = TarjetaInvalidaException.class)
    public void testTarjetaValidaTC2() {
        tc2 = new TarjetaCredito(ca1, 7000, ana, LocalDate.of(2022, 10, 1), LocalDate.of(2023, 10, 1), 7000);
        tc2.realizarPago(LocalDate.of(2022, 9, 1), "Servicoop", 2000);
    }

    @Test(expected = TarjetaInvalidaException.class)
    public void testTarjetaValidaTC3() {
        tc2 = new TarjetaCredito(ca1, 7000, ana, LocalDate.of(2022, 1, 1), LocalDate.of(2024, 12, 31), 7000);
        tc2.setBloqueada(true);
        tc2.realizarPago(LocalDate.of(2023, 1, 1), "Servicoop", 2000);
    }

    public void testEsTitular() {
        assertTrue(ca1.esTitular(ana));
        assertFalse(ca1.esTitular(pedro));
    }

    @Test(expected = TitularNoValidoException.class)
    public void testTitularInvalido() {
        td2 = new TarjetaDebito(ca1, 4000, pedro, LocalDate.of(2021, 1, 1), LocalDate.of(2022, 12, 31));
    }

    public void testCATarjetas() {
        assertEquals(ca1.cantidadTarjetas(), 2);
        assertTrue(ca1.contieneTarjeta(td1));
        assertTrue(ca1.contieneTarjeta(tc1));
        ca2 = new CajaAhorro(50, pedro);
        assertTrue(ca2.contieneTarjeta(td1));
    }

    public void testCATarjetas1() {
        assertEquals(ca1.getTarjetas().size(), 2);
        assertTrue(ca1.getTarjetas().contains(td1));
        assertTrue(ca1.getTarjetas().contains(tc1));
        ca2 = new CajaAhorro(50, pedro);
        assertTrue(ca2.getTarjetas().contains(td1));
    }
}
