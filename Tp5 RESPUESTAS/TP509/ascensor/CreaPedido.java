package ascensor;

import java.util.List;
import java.util.Random;

public class CreaPedido implements Runnable {
	private static final int TIEMPO_FIJO = 5000;
	private static final int TIEMPO_VARIABLE = 5000;
	private final static Random generator = new Random();

	private int nroPisos;
	private int totalPedidos;
	private ColaPedido pedidos;
	private List<Piso> pisos;

	public CreaPedido(int nroPisos, int totalPedidos, ColaPedido pedidos, List<Piso> pisos) {
		this.nroPisos = nroPisos;
		this.totalPedidos = totalPedidos;
		this.pedidos = pedidos;
		this.pisos = pisos;
	}

	public void run() {
		int pisoOrigen;
		int pisoDestino;
		for (int count = 1; count <= totalPedidos; count++)
			try {
				if (generator.nextInt(2) == 0) {
					pisoOrigen = 0;
					pisoDestino = generator.nextInt(nroPisos - 1) + 1;
				} else {
					pisoOrigen = generator.nextInt(nroPisos - 1) + 1;
					pisoDestino = 0;
				}
				System.out.println("Pedido: " + count + " piso origen: "
						+ pisoOrigen + " piso destino: " + pisoDestino);
				pedidos.set(new Pedido(count, pisos.get(pisoOrigen), pisos
						.get(pisoDestino)));
				Thread.sleep(generator.nextInt(TIEMPO_VARIABLE) + TIEMPO_FIJO);

			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
	}
}
