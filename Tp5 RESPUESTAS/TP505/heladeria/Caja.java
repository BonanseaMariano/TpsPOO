package heladeria;

import java.util.Random;

public class Caja implements Runnable {
	private final static Random generator = new Random();
	private final Buffer colaCaja;
	private final Buffer colaAtencion;
	private final String nombre;

	public Caja(String nombre, Buffer colaCaja, Buffer colaAtencion) {
		this.nombre = nombre;
		this.colaCaja = colaCaja;
		this.colaAtencion = colaAtencion;
	}

	public void run() {
		Cliente cliente;
		try {
			while ((cliente = colaCaja.get()) != null) {				
				System.out.println("Cliente: " + cliente.getId() + " Caja: " + nombre);
				Thread.sleep(generator.nextInt(1000) + 1000);								
				colaAtencion.set(cliente);
			}
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}

	}
}
