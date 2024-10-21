package ascensor;

import java.util.List;
import java.util.Random;

public class CreaPersona implements Runnable {
	private static final int TIEMPO_FIJO = 5000;
	private static final int TIEMPO_VARIABLE = 5000;
	private final static Random generator = new Random();

	private int nroPisos;
	private int totalPersonas;
	private Pedido pedidos;
	private List<Piso> pisos;

	public CreaPersona(int nroPisos, int totalPersonas, Pedido pedidos,
			List<Piso> pisos) {
		this.nroPisos = nroPisos;
		this.totalPersonas = totalPersonas;
		this.pedidos = pedidos;
		this.pisos = pisos;
	}

	public void run() {
		int pisoOrigen;
		int pisoDestino;
		for (int count = 1; count <= totalPersonas; count++)
			try {
				pisoOrigen = generator.nextInt(nroPisos);
				pisoDestino = generator.nextInt(nroPisos);
				while (pisoOrigen == pisoDestino)
					pisoDestino = generator.nextInt(nroPisos);
				System.out.println("Persona: " + count + " está en el piso: "
						+ pisoOrigen + " y va al piso: " + pisoDestino);
				pedidos.set(new Persona(count, pisos.get(pisoOrigen), pisos
						.get(pisoDestino)));
				Thread.sleep(generator.nextInt(TIEMPO_VARIABLE) + TIEMPO_FIJO);

			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
	}
}
