package cine;

import java.util.Random;

public class Acomodador implements Runnable {
	private static final int TIEMPO_INGRESO = 100;
	private final static Random generator = new Random();

	private ColaIngreso colaIngreso;

	public Acomodador(ColaIngreso colaIngreso) {
		this.colaIngreso = colaIngreso;
	}

	public void run() {

		try {
			Espectador espectador = colaIngreso.get();
			while (espectador != null) {
				Thread.sleep(generator.nextInt(TIEMPO_INGRESO));
				if (espectador.getCantidadEntradas() > 0)
					System.out.println("Ingreso a la sala -> Espectador: " + espectador.getId() + " entrada #"
							+ espectador.getEntrada().getNumero());
				else
					System.out.println("Ingreso a la sala -> Acompañante espectador: " + espectador.getId()
							+ " entrada #" + espectador.getEntrada().getNumero());
				espectador = colaIngreso.get();
			}

		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
}
