package heladeria;

import java.util.Random;

public class Atencion implements Runnable {
	private final static Random generator = new Random();
	private final Buffer colaAtencion;
	private final String nombre;
	private int cantidad;

	public Atencion(String nombre, Buffer colaAtencion) {
		this.nombre = nombre;
		this.colaAtencion = colaAtencion;
		this.cantidad = 0;
	}

	public void run() {
		Cliente cliente;
		long tiempo;
		try {
			while ((cliente = colaAtencion.get()) != null) {
				System.out.println("Cliente: " + cliente.getId() + " Atencion: " + nombre);
				Thread.sleep(generator.nextInt(2000) + 2000);
				tiempo = System.currentTimeMillis() - cliente.getIngreso();
				System.out.println("Cliente: " + cliente.getId() + " Tiempo: " + tiempo);
				cantidad++;
			}
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
		System.out.println("Fin atencion: "+nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	
}
