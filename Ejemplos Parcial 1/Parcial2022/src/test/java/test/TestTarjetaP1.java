package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import banco.*;
import org.junit.Before;
import org.junit.Test;

public class TestTarjetaP1 {

    private Titular juan;
    private Titular ana;
    private CajaAhorro ca1;
    private Tarjeta tc1;
    private Tarjeta td1;

    @Before
    public void setUp() throws Exception {
        juan = new Titular("Juan", "22222222");
        ana = new Titular("Ana", "33333333");
        ca1 = new CajaAhorro(25, juan);
        ca1.agregarTitular(ana);
        ca1.setSaldo(7000);

        tc1 = new TarjetaCredito(ca1, 3000, ana, LocalDate.of(2021, 1, 1), LocalDate.of(2024, 12, 31), 10000);
        td1 = new TarjetaDebito(ca1, 4000, juan, LocalDate.of(2021, 1, 1), LocalDate.of(2024, 12, 31));
    }

    @Test
    public void testTD1() {
        td1.realizarPago(LocalDate.of(2022, 9, 2), "Servicoop", 3542.58);
        td1.realizarPago(LocalDate.of(2022, 9, 3), "Carrefour", 2543.22);
        assertEquals(ca1.getSaldo(), 7000 - 3542.58 - 2543.22, 0.01);
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void testTD2() {
        td1.realizarPago(LocalDate.of(2022, 9, 4), "Supercanal", 7200);
    }

    @Test
    public void testTC1() {
        tc1.realizarPago(LocalDate.of(2022, 9, 2), "Servicoop", 3542.58);
        tc1.realizarPago(LocalDate.of(2022, 9, 3), "Carrefour", 2543.22);
        assertEquals(tc1.obtenerSaldo(), 10000 - 3542.58 - 2543.22, 0.01);
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void testTC2() {
        tc1.realizarPago(LocalDate.of(2022, 9, 2), "Servicoop", 3542.58);
        tc1.realizarPago(LocalDate.of(2022, 9, 3), "Carrefour", 2543.22);
        tc1.realizarPago(LocalDate.of(2022, 9, 4), "Supercanal", 7200);
    }

    @Test(expected = TarjetaInvalidaException.class)
    public void testTarjetaInvalida() {
        TarjetaDebito td2 = new TarjetaDebito(ca1, 4000, juan, LocalDate.of(2021, 1, 1), LocalDate.of(2022, 12, 31));
        td2.realizarPago(LocalDate.of(2022, 9, 4), "Supercanal", 7200);
    }

    @Test(expected = TitularNoValidoException.class)
    public void testTitularNoValido() {
        Titular pedro = new Titular("Pedro", "44444444");
        TarjetaDebito td3 = new TarjetaDebito(ca1, 4000, pedro, LocalDate.of(2021, 1, 1), LocalDate.of(2022, 12, 31));
        td3.realizarPago(LocalDate.of(2022, 9, 4), "Supercanal", 7200);
    }

}
