package test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import colectivo.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestColectivo {

    private static Empresa empresa;
    private static Localidad bas, azu, bbl, gco, sao, sgr, pmy, tre, cri;
    private static Recorrido r1, r2;
    private static Colectivo c1, c2;
    private static Viaje v1, v2, v3, v4;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        empresa = new Empresa("Bus", "30-32112321-4");
        bas = new Localidad("BAS", "Buenos Aires");
        azu = new Localidad("AZU", "Azul");
        bbl = new Localidad("BBL", "Bahía Blanca");
        gco = new Localidad("GCO", "General Conesa");
        sao = new Localidad("SAO", "San Antonio Oeste");
        sgr = new Localidad("SGR", "Sierra Grande");
        pmy = new Localidad("PMY", "Puerto Madryn");
        tre = new Localidad("TRE", "Trelew");
        cri = new Localidad("PDI", "Comodoro Rivadavia");

        r1 = new Recorrido(510, bas, azu);
        r1.agregarLocalidad(bbl);
        r1.agregarLocalidad(gco);
        r1.agregarLocalidad(sao);
        r1.agregarLocalidad(sgr);
        r1.agregarLocalidad(pmy);
        r1.agregarLocalidad(tre);
        r1.agregarLocalidad(cri);

        r2 = new Recorrido(520, bas, bbl);
        r2.agregarLocalidad(tre);
        r2.agregarLocalidad(cri);

        c1 = new Colectivo("AF-202-AA", 4);
        c2 = new Colectivo("AB-302-BB", 4);

        empresa.agregarTramo(bas, azu, 3100);
        empresa.agregarTramo(bas, bbl, 6620);
        empresa.agregarTramo(bas, gco, 9730);
        empresa.agregarTramo(bas, sao, 10620);
        empresa.agregarTramo(bas, sgr, 11870);
        empresa.agregarTramo(bas, pmy, 13210);
        empresa.agregarTramo(bas, tre, 13730);
        empresa.agregarTramo(bas, cri, 17480);
        empresa.agregarTramo(azu, bbl, 3520);
        empresa.agregarTramo(azu, gco, 6630);
        empresa.agregarTramo(azu, sao, 7520);
        empresa.agregarTramo(azu, sgr, 8770);
        empresa.agregarTramo(azu, pmy, 10110);
        empresa.agregarTramo(azu, tre, 10630);
        empresa.agregarTramo(azu, cri, 14380);
        empresa.agregarTramo(bbl, gco, 3110);
        empresa.agregarTramo(bbl, sao, 4000);
        empresa.agregarTramo(bbl, sgr, 5250);
        empresa.agregarTramo(bbl, pmy, 6590);
        empresa.agregarTramo(bbl, tre, 7110);
        empresa.agregarTramo(bbl, cri, 10860);
        empresa.agregarTramo(gco, sgr, 2140);
        empresa.agregarTramo(gco, pmy, 3480);
        empresa.agregarTramo(gco, tre, 4000);
        empresa.agregarTramo(gco, cri, 7750);
        empresa.agregarTramo(sao, pmy, 2590);
        empresa.agregarTramo(sao, tre, 3110);
        empresa.agregarTramo(sao, cri, 6860);
        empresa.agregarTramo(sgr, cri, 5610);
        empresa.agregarTramo(pmy, cri, 4270);
        empresa.agregarTramo(tre, cri, 3750);

        v1 = empresa.agregarViaje(c1, r1, LocalDate.of(2023, 9, 21));
        v2 = empresa.agregarViaje(c2, r2, LocalDate.of(2023, 9, 21));
        v3 = empresa.agregarViaje(c1, r1, LocalDate.of(2023, 9, 24));
        v4 = empresa.agregarViaje(c2, r1, LocalDate.of(2023, 9, 26));
    }

    @BeforeEach
    void setUp() throws Exception {

    }

    /**
     * r1.tramos(pmy, pmy) = {}
     * <p>
     * r1.tramos(tre, pmy) = {}
     * <p>
     * r2.tramos(bas, pmy) = {}
     * <p>
     * r1.tramos(sao, tre) = {sao, sgr, pmy}
     */
    @Test
    void testRecorrido() {
        assertTrue(r1.tramos(pmy, pmy).isEmpty());
        assertTrue(r1.tramos(tre, pmy).isEmpty());
        assertTrue(r2.tramos(bas, pmy).isEmpty());
        assertEquals(Arrays.asList(sao, sgr, pmy), r1.tramos(sao, tre));
    }

    /**
     * tramoPasaje(azu, pmy).getPrecio() = 10110.00
     * <p>
     * tramoPasaje(pmy, azu) = null
     */
    @Test
    void testTramoPasaje() {
        assertEquals(10110.00, empresa.tramoPasaje(azu, pmy).getPrecio());
        assertEquals(null, empresa.tramoPasaje(pmy, azu));
    }

    /**
     * viaje(21/09/2023, pmy, tre) = null
     * <p>
     * viaje(22/09/2023, bas, pmy) = {}
     * <p>
     * viaje(21/09/2023, bas, pmy) = {v1}
     * <p>
     * viaje(21/09/2023, bas, tre) = {v1, v2}
     */
    @Test
    void testViaje() {
        assertEquals(null, empresa.viaje(LocalDate.of(2023, 9, 21), pmy, tre));
        assertTrue(empresa.viaje(LocalDate.of(2023, 9, 22), bas, pmy).isEmpty());
        assertEquals(Arrays.asList(v1), empresa.viaje(LocalDate.of(2023, 9, 21), bas, pmy));
        assertEquals(Arrays.asList(v1, v2), empresa.viaje(LocalDate.of(2023, 9, 21), bas, tre));
    }

    /**
     * v2.asientosLibres() = {1, 2, 3, 4}
     * <p>
     * v2.venderPasaje("Juan", "35.212.213", tramoPasaje(bas, pmy), 3) ->
     * RecorridoNoValidoException
     * <p>
     * v2.venderPasaje("Juan", "35.212.213", tramoPasaje(bas, tre), 3)
     * v2.asientosLibres() = {1, 2, 4}
     * <p>
     * v2.venderPasaje("Ana", "38.212.213", tramoPasaje(bas, tre), 3) ->
     * AsientoOcupadoException
     * <p>
     * v2.venderPasaje("Ana", "38.212.213", tramoPasaje(bas, tre), 1)
     * v2.asientosLibres() = {2, 4}
     */

    @Test
    void testAsientosVentas() {
        List<Integer> asientos = Arrays.asList(1, 2, 3, 4);
        assertEquals(asientos, v2.asientosLibres());

        assertThrows(RecorridoNoValidoException.class, () -> v2.venderPasaje("Juan", "35.212.213", empresa.tramoPasaje(bas, pmy), 3));

        v2.venderPasaje("Juan", "35.212.213", empresa.tramoPasaje(bas, tre), 3);
        asientos = Arrays.asList(1, 2, 4);
        assertEquals(asientos, v2.asientosLibres());

        assertThrows(AsientoOcupadoException.class, () -> v2.venderPasaje("Ana", "38.212.213", empresa.tramoPasaje(bas, tre), 3));

        v2.venderPasaje("Ana", "38.212.213", empresa.tramoPasaje(bas, tre), 1);
        asientos = Arrays.asList(2, 4);
        assertEquals(asientos, v2.asientosLibres());
    }

}
