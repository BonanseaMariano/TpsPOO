// PruebaConjuntos.java
package carlosfontela.pruebas;

import junit.framework.TestCase;
import carlosfontela.algebra.*;

public class PruebaConjuntos extends TestCase {

	private Conjunto aA = new ConjuntoArreglo(30);
	private Conjunto eA = new ConjuntoBits(30);
	private Conjunto aB = new ConjuntoArreglo(30);
	private Conjunto eB = new ConjuntoBits(30);
	private Conjunto aC = new ConjuntoArreglo(30);
	private Conjunto eC = new ConjuntoBits(30);
	
	protected void setUp( ) throws Exception {
		aA.agregarElemento(8);
		eA.agregarElemento(8);
		aA.agregarElemento(9);
		eA.agregarElemento(9);
		aB.agregarElemento(3);
		eB.agregarElemento(3);
		aB.agregarElemento(9);
		eB.agregarElemento(9);
	}
	
	public void testVacio( ) {
		assertTrue (aC.vacio( ));
		assertTrue (eC.vacio( ));
		assertEquals (0, aC.cardinalidad( ));
		assertEquals (0, eC.cardinalidad( ));
	}

	public void testAgregarPertenenciaCardinalidad( ) {
		assertTrue (aA.pertenece(8));
		assertTrue (eA.pertenece(8));
		assertTrue (aA.pertenece(9));
		assertTrue (eA.pertenece(9));
		assertEquals (2, aA.cardinalidad( ));
		assertEquals (2, eA.cardinalidad( ));
		assertTrue (aB.pertenece(3));
		assertTrue (eB.pertenece(3));
		assertFalse (aB.pertenece(8));
		assertFalse (eB.pertenece(8));
		assertTrue (aB.pertenece(9));
		assertTrue (eB.pertenece(9));
		assertEquals (2, aB.cardinalidad( ));
		assertEquals (2, eB.cardinalidad( ));
	}

	public void testImplementacion( ) {
		// ambas implementaciones deben dar iguales resultados:
		assertTrue (eA.igual(aA));
		assertTrue (eB.igual(aB));
	}

	public void testUnionPertenencia( ) {
		aC = aA.union(eB);
		eC = eA.union(aB);
		assertEquals (3, aC.cardinalidad( ));
		assertEquals (3, eC.cardinalidad( ));
		assertTrue (aC.pertenece(3));
		assertTrue (aC.pertenece(8));
		assertTrue (aC.pertenece(9));
		assertTrue (eC.pertenece(3));
		assertTrue (eC.pertenece(8));
		assertTrue (eC.pertenece(9));
		// la unión es conmutativa:
		aC = eB.union(aA);
		eC = aB.union(eA);
		assertEquals (3, aC.cardinalidad( ));
		assertEquals (3, eC.cardinalidad( ));
		assertTrue (aC.pertenece(3));
		assertTrue (aC.pertenece(8));
		assertTrue (aC.pertenece(9));
		assertTrue (eC.pertenece(3));
		assertTrue (eC.pertenece(8));
		assertTrue (eC.pertenece(9));
	}

	public void testInterseccionPertenencia( ) {
		aC = eA.interseccion(eB);
		eC = aA.interseccion(aB);
		assertEquals (1, aC.cardinalidad( ));
		assertEquals (1, eC.cardinalidad( ));
		assertFalse (aC.pertenece(3));
		assertFalse (aC.pertenece(8));
		assertTrue (aC.pertenece(9));
		assertFalse (eC.pertenece(3));
		assertFalse (eC.pertenece(8));
		assertTrue (eC.pertenece(9));
		// la intersección es conmutativa:
		aC = eB.interseccion(eA);
		eC = aB.interseccion(aA);
		assertEquals (1, aC.cardinalidad( ));
		assertEquals (1, eC.cardinalidad( ));
		assertFalse (aC.pertenece(3));
		assertFalse (aC.pertenece(8));
		assertTrue (aC.pertenece(9));
		assertFalse (eC.pertenece(3));
		assertFalse (eC.pertenece(8));
		assertTrue (eC.pertenece(9));
	}

	public void testDiferenciaPertenencia( ) {
		aC = eA.diferencia(aB);
		eC = aA.diferencia(eB);
		assertEquals (1, aC.cardinalidad( ));
		assertEquals (1, eC.cardinalidad( ));
		assertFalse (aC.pertenece(3));
		assertTrue (aC.pertenece(8));
		assertFalse (aC.pertenece(9));
		assertFalse (eC.pertenece(3));
		assertTrue (eC.pertenece(8));
		assertFalse (eC.pertenece(9));
		// la diferencia no es conmutativa:
		aC = aB.diferencia(eA);
		eC = eB.diferencia(aA);
		assertEquals (1, aC.cardinalidad( ));
		assertEquals (1, eC.cardinalidad( ));
		assertTrue (aC.pertenece(3));
		assertFalse (aC.pertenece(8));
		assertFalse (aC.pertenece(9));
		assertTrue (eC.pertenece(3));
		assertFalse (eC.pertenece(8));
		assertFalse (eC.pertenece(9));
	}

	public void testInclusion( ) {
		assertFalse (eA.incluido(eB));
		assertFalse (eB.incluido(eA));
		assertFalse (aA.incluido(aB));
		assertFalse (aB.incluido(aA));
		aA.agregarElemento(3);
		eA.agregarElemento(3);
		assertFalse (eA.incluido(eB));
		assertTrue (eB.incluido(eA));
		assertFalse (aA.incluido(aB));
		assertTrue (aB.incluido(aA));
	}

	public void testVaciar( ) {
		aC.agregarElemento(4);
		eC.agregarElemento(12);
		aC.vaciar( );
		eC.vaciar( );
		assertTrue (aC.vacio( ));
		assertTrue (eC.vacio( ));
	}

	public void testEliminar( ) {
		// sacamos un elemento
		aA.sacarElemento(3);
		assertEquals (2, aA.cardinalidad( ));
		assertFalse (aA.pertenece(3));
		// sacamos un elemento que no está
		aA.sacarElemento(125);
		assertEquals (2, aA.cardinalidad( ));
		assertFalse (aA.pertenece(25));
		// sacamos un elemento
		aA.sacarElemento(8);
		assertEquals (1, aA.cardinalidad( ));
		assertFalse (aA.pertenece(8));
		// sacamos un elemento que no está pero estaba
		aA.sacarElemento(8);
		assertEquals (1, aA.cardinalidad( ));
		assertFalse (aA.pertenece(8));
		// sacamos un elemento
		aA.sacarElemento(9);
		assertEquals (0, aA.cardinalidad( ));
		assertFalse (aA.pertenece(9));
		assertTrue (aA.vacio( ));
	}
}
