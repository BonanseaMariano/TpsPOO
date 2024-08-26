// PruebaConjuntos.java
package carlosfontela.pruebas;

import junit.framework.TestCase;
import carlosfontela.algebra.*;

public class PruebaConjuntos2 extends TestCase {

    private Conjunto a = new ConjuntoArreglo(5);
    private Conjunto b = new ConjuntoArreglo(10);
    private Conjunto c;

    protected void setUp() throws Exception {
        a.agregarElemento(0);
        a.agregarElemento(2);
        a.agregarElemento(4);
        b.agregarElemento(4);
        b.agregarElemento(8);
    }

    public void testDiferencia_ab() {
        c = a.diferencia(b);
        assertEquals(2, c.cardinalidad());
        assertTrue(c.pertenece(0));
        assertTrue(c.pertenece(2));
    }

    public void testDiferencia_ba() {
        c = b.diferencia(a);
        assertEquals(1, c.cardinalidad());
        assertTrue(c.pertenece(8));
    }

    public void testIguales() {
        b.agregarElemento(0);
        b.agregarElemento(2);
        b.sacarElemento(8);
        assertTrue(a.igual(b));
        assertTrue(b.igual(a));
    }

    public void testNoIguales() {
        assertFalse(a.igual(b));
        assertFalse(b.igual(a));
    }


}
