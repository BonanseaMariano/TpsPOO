package partido;

import java.util.Random;

public class PuertaIngreso implements Runnable {
	private static final int TIEMPO_INGRESO = 100;
	private final static Random generator = new Random();

	private String nombre;
	private ColaIngreso colaIngreso;
	private int hinchas;

	public PuertaIngreso(String nombre, ColaIngreso colaIngreso) {
		this.nombre = nombre;
		this.colaIngreso = colaIngreso;
		hinchas = 0;
	}

	public void run() {

		try {
			Hincha hincha = colaIngreso.get();
			while (hincha != null) {
				Thread.sleep(generator.nextInt(TIEMPO_INGRESO));
				System.out.println("Ingreso a la cancha por la puerta " + nombre + " el Hincha: " + hincha.getId());
				hinchas++;						
				hincha = colaIngreso.get();
			}

		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	public String getNombre() {
		return nombre;
	}

	public int getHinchas() {
		return hinchas;
	}
	
	
}
