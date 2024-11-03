package barbero;

import java.util.Random;

public class GeneraCliente implements Runnable {	
	private final Buffer sharedLocation;
	private int sillas;
	private final static int MAX_CLIENTES = 10;

	public GeneraCliente(Buffer shared, int sillas) {		
		this.sillas = sillas;
		sharedLocation = shared;
	}

	public void run() {
		Cliente cliente;
		Random generator = new Random();
		for (int count = 1; count <= MAX_CLIENTES; count++) {
			try {
				Thread.sleep(generator.nextInt(1000) + 2000);
				cliente = new Cliente(count);
				if (sharedLocation.size() == sillas)
					System.out.println("Cliente: " + count + " no tiene silla. Se retira.");
				else {
					sharedLocation.set(cliente);
					System.out.println("Cliente: " + count + " ingresa a la barbería");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("No ingresan más clientes");
	}
}