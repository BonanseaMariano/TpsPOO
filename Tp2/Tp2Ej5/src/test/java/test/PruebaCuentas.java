package test;

import carlosfontela.cuentas.*;
import junit.framework.TestCase;

public class PruebaCuentas extends TestCase {

	private CuentaBancaria cajaAhorro1;
	private CuentaBancaria cuentaCorriente1;
	private CuentaBancaria cajaAhorro2;
	private CuentaBancaria cuentaCorriente2;
	private Cliente juan;
	private Cliente ines;
	private Cliente empresa;

	protected void setUp() throws Exception {
		super.setUp();
		juan = new Persona("Juan", "P�rez", 5122122, "Lima", 444, "Belgrano",
					"Venezuela", "C1000AAA", "01140101010", "juan@perez.com.ar");
		ines = new Persona ("In�s", "Garc�a", 4011011, "Cerrito", 1111, "Santa Fe",
					"Arenales", "C1111ZZZ", "01140001111", "ines@garcia.com.ar");
		empresa = new Empresa("Luna Nueva S.A.", "1701234562", "Freire", 8888,
					"Dorrego", "Concepci�n Arenal", "C0000YYY", "01199991000",
					"info@empresa.com.ar");
		cajaAhorro1 = new CajaAhorro (1234, juan);
		cajaAhorro2 = new CajaAhorro (5678, ines);
		cuentaCorriente1 = new CuentaCorriente(9012, ines, 0);
		cuentaCorriente2 = new CuentaCorriente (3456, empresa, 2000);
	}

	public void test ( ) {
		assertEquals ( 1, juan.getCantidadCuentas() );
		assertEquals ( 2, ines.getCantidadCuentas() );
		assertEquals ( 1, empresa.getCantidadCuentas() );
		assertEquals ( juan, cajaAhorro1.getTitular() );
		assertEquals ( ines, cajaAhorro2.getTitular() );
		assertEquals ( ines, cuentaCorriente1.getTitular() );
		assertEquals ( empresa, cuentaCorriente2.getTitular() );
		CuentaBancaria[ ] cuentasJuan = juan.getCuentas();
		CuentaBancaria[ ] cuentasInes = ines.getCuentas();
		CuentaBancaria[ ] cuentasEmpresa = empresa.getCuentas();
		assertEquals ( cajaAhorro1, cuentasJuan[0]);
		assertEquals ( cajaAhorro2, cuentasInes[0]);
		assertEquals ( cuentaCorriente1, cuentasInes[1]);
		assertEquals ( cuentaCorriente2, cuentasEmpresa[0]);
	}
}
