package partido;

import java.util.Random;

public class CreaHincha implements Runnable {
	private static final int TIEMPO_FIJO_BOLETERIA = 1000;
	private static final int TIEMPO_VARIABLE_BOLETERIA = 1000;
	private static final int MAX_ENTRADAS = 6;
	private final static Random generator = new Random();

	private ColaBoleteria colaBoleteria;
	private int totalExpectadores;

	public CreaHincha(ColaBoleteria colaBoleteria, int totalExpectadores) {
		this.colaBoleteria = colaBoleteria;
		this.totalExpectadores = totalExpectadores;
	}

	public void run() {
		boolean local;
		for (int count = 1; count <= totalExpectadores; count++)
			try {
				Thread.sleep(generator.nextInt(TIEMPO_VARIABLE_BOLETERIA) + TIEMPO_FIJO_BOLETERIA);
				local = (generator.nextInt(2) == 0 ? true : false);
				colaBoleteria.set(new Hincha(count, local, generator.nextInt(MAX_ENTRADAS) + 1));
				System.out.println(
						"Ingresa a sacar entrada -> Hincha: " + count + (local == true ? " Local" : " Visitante"));
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
	}
}
