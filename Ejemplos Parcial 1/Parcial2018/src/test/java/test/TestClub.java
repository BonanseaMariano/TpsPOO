package test;

import club.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClub {
    private Club club;
    private Socio socio1, socio2, socio3, socio4, socio5, socio6;
    private Velero velero1, velero2, velero3;
    private Lancha lancha1, lancha2;


    @BeforeEach
    public void setUp() {
        club = new Club("Club Nautico");

        socio1 = club.agregarSocio(1, "Juan Perez");
        socio1.setCuotaSocial(100.0);
        Barco.setValorAlquilerM2(15);

        socio2 = club.agregarSocio(2, "Maria Lopez");
        socio3 = club.agregarSocio(3, "Carlos Gomez");
        socio4 = club.agregarSocio(4, "Ana Martinez");
        socio5 = club.agregarSocio(5, "Luis Fernandez");

        socio6 = club.agregarSocio(6, "Laura Martinez");

        velero1 = club.agregarVelero("Velero1", "MAT123", socio2, 3.0, 10.0);
        velero2 = club.agregarVelero("Velero2", "MAT789", socio4, 3.0, 10.0);
        velero3 = club.agregarVelero("Velero3", "MAT345", socio5, 3.0, 10.0);

        lancha1 = club.agregarLancha("Lancha1", "MAT456", socio3, 2.5, 8.0, true);
        lancha2 = club.agregarLancha("Lancha2", "MAT012", socio4, 2.5, 8.0, false);
    }

    @Test
    public void testSocioSinBarco() {
        assertEquals(100.0, socio1.calcularCuota());
    }

    @Test
    public void testSocioConVelero() {
        assertEquals(100.0 + velero1.calcularAlquilerGuarderia(), socio2.calcularCuota());
    }

    @Test
    public void testSocioConLanchaGuarderiaCubierta() {
        assertEquals(100.0 + lancha1.calcularAlquilerGuarderia(), socio3.calcularCuota());
    }

    @Test
    public void testSocioConMasDeUnBarco() {
        double expectedCuota = 100.0 + velero2.calcularAlquilerGuarderia() + lancha2.calcularAlquilerGuarderia();
        assertEquals(expectedCuota, socio4.calcularCuota());
    }

    @Test
    public void testSocioCompartiendoBarco() {
        velero3.agregarSocio(socio6);
        assertEquals(325.0, socio5.calcularCuota());
        assertEquals(325.0, socio6.calcularCuota());
    }
}