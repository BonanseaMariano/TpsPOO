package carlosfontela.pruebas;

import carlosfontela.algebra.Conjunto;
import carlosfontela.algebra.ConjuntoColeccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PruebaConjuntoColeccion {

    private static ConjuntoColeccion conjunto;

    @BeforeEach
    void setUp() {
        conjunto = new ConjuntoColeccion(10);
    }

    @Test
    void pertenece_ElementExists_ReturnsTrue() {
        conjunto.agregarElemento(5);
        assertTrue(conjunto.pertenece(5));
    }

    @Test
    void pertenece_ElementDoesNotExist_ReturnsFalse() {
        assertFalse(conjunto.pertenece(5));
    }

    @Test
    void vacio_EmptySet_ReturnsTrue() {
        assertTrue(conjunto.vacio());
    }

    @Test
    void vacio_NonEmptySet_ReturnsFalse() {
        conjunto.agregarElemento(5);
        assertFalse(conjunto.vacio());
    }

    @Test
    void vaciar_NonEmptySet_BecomesEmpty() {
        conjunto.agregarElemento(5);
        conjunto.vaciar();
        assertTrue(conjunto.vacio());
    }

    @Test
    void agregarElemento_ValidElement_ElementAdded() {
        conjunto.agregarElemento(5);
        assertTrue(conjunto.pertenece(5));
    }

    @Test
    void agregarElemento_InvalidElement_ElementNotAdded() {
        conjunto.agregarElemento(15);
        assertFalse(conjunto.pertenece(15));
    }

    @Test
    void sacarElemento_ElementExists_ElementRemoved() {
        conjunto.agregarElemento(5);
        conjunto.sacarElemento(5);
        assertFalse(conjunto.pertenece(5));
    }

    @Test
    void sacarElemento_ElementDoesNotExist_NoChange() {
        conjunto.sacarElemento(5);
        assertFalse(conjunto.pertenece(5));
    }

    @Test
    void cardinalidad_EmptySet_ReturnsZero() {
        assertEquals(0, conjunto.cardinalidad());
    }

    @Test
    void cardinalidad_NonEmptySet_ReturnsCorrectCount() {
        conjunto.agregarElemento(1);
        conjunto.agregarElemento(2);
        assertEquals(2, conjunto.cardinalidad());
    }

    @Test
    void diferenciaSimetrica_TwoSets_ReturnsCorrectDifference() {
        ConjuntoColeccion conjuntoA = new ConjuntoColeccion(10);
        ConjuntoColeccion conjuntoB = new ConjuntoColeccion(10);
        conjuntoA.agregarElemento(1);
        conjuntoA.agregarElemento(2);
        conjuntoB.agregarElemento(2);
        conjuntoB.agregarElemento(3);
        Conjunto resultado = conjuntoA.diferenciaSimetrica(conjuntoB);
        assertTrue(resultado.pertenece(1));
        assertTrue(resultado.pertenece(3));
        assertFalse(resultado.pertenece(2));
    }
}