package cine;

import java.util.Random;

public class CreaEspectador implements Runnable {
	private static final int TIEMPO_FIJO_BOLETERIA = 2000;
	private static final int TIEMPO_VARIABLE_BOLETERIA = 1000;
	private static final int MAX_ENTRADAS = 4;
	private final static Random generator = new Random();

	private ColaBoleteria colaBoleteria;
	private int totalExpectadores;

	public CreaEspectador(ColaBoleteria colaBoleteria, int totalExpectadores) {
		this.colaBoleteria = colaBoleteria;
		this.totalExpectadores = totalExpectadores;
	}

	public void run() {		
		for (int count = 1; count <= totalExpectadores; count++)
			try {
				Thread.sleep(generator.nextInt(TIEMPO_VARIABLE_BOLETERIA) + TIEMPO_FIJO_BOLETERIA);				
				colaBoleteria.set(new Espectador(count, generator.nextInt(MAX_ENTRADAS) + 1));
				System.out.println("Ingresa al cine -> Espectador: " + count);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
	}
}
