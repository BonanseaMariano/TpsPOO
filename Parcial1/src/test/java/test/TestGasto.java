package test;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gasto.Gasto;
import gasto.Grupo;
import gasto.Pago;
import gasto.Persona;

class TestGasto {

    private Grupo viaje;
    private Persona juan, ana, pedro, lucia;
    private Gasto taxi, hostel, almuerzo, salida;
    private Pago pago1, pago2;

    @BeforeEach
    void setUp() throws Exception {
        viaje = new Grupo("Viaje a Europa");
        juan = viaje.agregarIntegrante("Juan", "juan@gmail.com");
        ana = viaje.agregarIntegrante("Ana", "ana@gmail.com");
        pedro = viaje.agregarIntegrante("Pedro", "pedro@gmail.com");
        lucia = viaje.agregarIntegrante("Lucia", "lucia@gmail.com");

        taxi = viaje.agregarGasto(LocalDate.of(2024, 9, 4), "Taxi", juan);
        taxi.agregarDivision(juan, 2000);
        taxi.agregarDivision(ana, 2000);
        taxi.agregarDivision(pedro, 2000);
        taxi.agregarDivision(lucia, 2000);

        pago1 = viaje.agregarPago(LocalDate.of(2024, 9, 4), pedro, juan, 2000);

        hostel = viaje.agregarGasto(LocalDate.of(2024, 9, 4), "Hostel", ana);
        hostel.agregarDivision(juan, 10000);
        hostel.agregarDivision(ana, 10000);
        hostel.agregarDivision(pedro, 10000);
        hostel.agregarDivision(lucia, 10000);

        almuerzo = viaje.agregarGasto(LocalDate.of(2024, 9, 4), "Almuerzo", pedro);
        almuerzo.agregarDivision(juan, 5000);
        almuerzo.agregarDivision(ana, 3000);
        almuerzo.agregarDivision(pedro, 10000);

        salida = viaje.agregarGasto(LocalDate.of(2024, 9, 4), "Cerveza", pedro);
        salida.agregarDivision(juan, 4000);
        salida.agregarDivision(pedro, 8000);

        pago2 = viaje.agregarPago(LocalDate.of(2024, 9, 4), juan, pedro, 4000);
    }

    @Test
    void testImportePagado() {
        //Ejemplo: En el almuerzo Juan gasta $5000, Ana $3000 y Pedro $10000 el total gastado es $18000
        Assertions.assertEquals(5000 + 3000 + 10000, almuerzo.importePagado());
    }

    @Test
    void testImporteGastado() {
        //Ejemplo: En el almuerzo Juan gasta $5000, Ana $3000 y Pedro $10000 el total gastado por Juan es $5000
        Assertions.assertEquals(5000, almuerzo.importeGastado(juan));
        Assertions.assertEquals(3000, almuerzo.importeGastado(ana));
        Assertions.assertEquals(10000, almuerzo.importeGastado(pedro));
    }

    @Test
    void testTotalPagado() {
        // taxi = viaje.agregarGasto(LocalDate.of(2024, 9, 4), "Taxi", juan);
        Assertions.assertEquals(8000, viaje.totalPagado(juan));
        // hostel = viaje.agregarGasto(LocalDate.of(2024, 9, 4), "Hostel", ana);
        Assertions.assertEquals(40000, viaje.totalPagado(ana));
        // almuerzo = viaje.agregarGasto(LocalDate.of(2024, 9, 4), "Almuerzo", pedro);
        // +
        // salida = viaje.agregarGasto(LocalDate.of(2024, 9, 4), "Cerveza", pedro);
        Assertions.assertEquals(30000, viaje.totalPagado(pedro));
    }

    @Test
    void testTotalGastado() {
        // taxi.agregarDivision(juan, 2000);
        // hostel.agregarDivision(juan, 10000);
        // almuerzo.agregarDivision(juan, 5000);
        // salida.agregarDivision(juan, 4000);
        Assertions.assertEquals(21000, viaje.totalGastado(juan));
        // taxi.agregarDivision(ana, 2000);
        // hostel.agregarDivision(ana, 10000);
        // almuerzo.agregarDivision(ana, 3000);
        Assertions.assertEquals(15000, viaje.totalGastado(ana));
        // taxi.agregarDivision(pedro, 2000);
        // hostel.agregarDivision(pedro, 10000);
        // almuerzo.agregarDivision(pedro, 10000);
        // salida.agregarDivision(pedro, 8000);
        Assertions.assertEquals(30000, viaje.totalGastado(pedro));
        // taxi.agregarDivision(lucia, 2000);
        // hostel.agregarDivision(lucia, 10000);
        Assertions.assertEquals(12000, viaje.totalGastado(lucia));
    }

    @Test
    void testDeudaEntrePersonas() {
        // juan en deuda con pedro
        Assertions.assertEquals(-5000, viaje.saldo(juan, pedro));
        // pedro en deuda con juan
        Assertions.assertEquals(5000, viaje.saldo(pedro, juan));
        // ana en deuda con juan
        Assertions.assertEquals(8000, viaje.saldo(ana, juan));
        // juan en deuda con ana
        Assertions.assertEquals(-8000, viaje.saldo(juan, ana));
    }

}