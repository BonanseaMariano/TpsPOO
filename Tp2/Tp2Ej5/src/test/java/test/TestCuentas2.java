package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.CajaAhorro;
import models.Cliente;
import exceptions.ClienteMaxCuentasException;
import models.CuentaBancaria;
import models.CuentaCorriente;
import models.Empresa;
import models.Persona;
import exceptions.SaldoInsuficienteException;

class TestCuentas2 {

	private CuentaBancaria cajaAhorro1;
	private CuentaBancaria cuentaCorriente1;
	private CuentaBancaria cajaAhorro2;
	private CuentaBancaria cuentaCorriente2;
	private Cliente juan;
	private Cliente ines;
	private Cliente empresa;

	@BeforeEach
	void setUp() throws Exception {
		Cliente.setMaximoCuentas(5);
		juan = new Persona("Juan", "P�rez", 5122122, "Lima", 444, "Belgrano", "Venezuela", "C1000AAA", "01140101010",
				"juan@perez.com.ar");
		ines = new Persona("In�s", "Garc�a", 4011011, "Cerrito", 1111, "Santa Fe", "Arenales", "C1111ZZZ",
				"01140001111", "ines@garcia.com.ar");
		empresa = new Empresa("Luna Nueva S.A.", "1701234562", "Freire", 8888, "Dorrego", "Concepci�n Arenal",
				"C0000YYY", "01199991000", "info@empresa.com.ar");
		cajaAhorro1 = new CajaAhorro(1234, juan);
		cajaAhorro2 = new CajaAhorro(5678, ines);
		cuentaCorriente1 = new CuentaCorriente(9012, ines, 0);
		cuentaCorriente2 = new CuentaCorriente(3456, empresa, 2000);
	}

	@Test
	void test() {
		assertEquals(1, juan.getCantidadCuentas());
		assertEquals(2, ines.getCantidadCuentas());
		assertEquals(1, empresa.getCantidadCuentas());
		assertEquals(juan, cajaAhorro1.getTitular());
		assertEquals(ines, cajaAhorro2.getTitular());
		assertEquals(ines, cuentaCorriente1.getTitular());
		assertEquals(empresa, cuentaCorriente2.getTitular());
		CuentaBancaria[] cuentasJuan = juan.getCuentas();
		CuentaBancaria[] cuentasInes = ines.getCuentas();
		CuentaBancaria[] cuentasEmpresa = empresa.getCuentas();
		assertEquals(cajaAhorro1, cuentasJuan[0]);
		assertEquals(cajaAhorro2, cuentasInes[0]);
		assertEquals(cuentaCorriente1, cuentasInes[1]);
		assertEquals(cuentaCorriente2, cuentasEmpresa[0]);
	}

	@Test
	void testExtraerCA() {
		cajaAhorro1.depositar(1000.00);
		assertEquals(cajaAhorro1.getSaldo(), 1000.00, 0.01);
		cajaAhorro1.extraer(700.00);
		assertEquals(cajaAhorro1.getSaldo(), 300.00, 0.01);
		Exception exception = assertThrows(SaldoInsuficienteException.class, () -> cajaAhorro1.extraer(700));
		assertEquals(exception.getMessage(), "Saldo: " + cajaAhorro1.getSaldo());
	}

	@Test
	void testExtraerCC() {
		cuentaCorriente2.extraer(700.00);
		assertEquals(cuentaCorriente2.getSaldo(), -700.00, 0.01);
		cuentaCorriente2.extraer(700.00);
		assertEquals(cuentaCorriente2.getSaldo(), -1400, 0.01);
		Exception exception = assertThrows(SaldoInsuficienteException.class, () -> cuentaCorriente2.extraer(700.00));
		assertEquals(exception.getMessage(), "Saldo: " + cuentaCorriente2.getSaldo());
	}

	@Test
	void testMaximoCuentas() {
		assertEquals(ines.getCantidadCuentas(), 2);
		new CajaAhorro(8798, ines);
		assertEquals(ines.getCantidadCuentas(), 3);
		new CajaAhorro(7988, ines);
		new CajaAhorro(7989, ines);	
		assertThrows(ClienteMaxCuentasException.class, () -> new CuentaCorriente(7012, ines, 0));
	}
	
}
