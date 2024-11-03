package heladeria;

import java.util.Random;

public class GeneradorCliente implements Runnable {
	private final static Random generator = new Random();
	private final Buffer colaCaja;
	private final int clientes;	

	public GeneradorCliente(Buffer colaCaja, int clientes) {
		this.colaCaja = colaCaja;
		this.clientes = clientes;
	}

	public void run() {
		Cliente cliente;
		for (int count = 1; count <= clientes; count++) {
			try {
				Thread.sleep(generator.nextInt(2000) + 1000);
				cliente = new Cliente(count);
				cliente.setIngreso(System.currentTimeMillis());
				colaCaja.set(cliente);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
		System.out.println("Fin Ingreso Clientes");
	}
}
