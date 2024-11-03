package ascensor;

import java.util.List;

public class Ascensor implements Runnable {

	private static final int TIEMPO_VIAJE = 1000;

	private int id;
	private List<Piso> pisos;
	private boolean ocupado;
	private int pisoActual;
	private int pisoDestino;
	private int cantViajes;

	public Ascensor(int id, List<Piso> pisos) {
		this.id = id;
		this.pisos = pisos;
		ocupado = false;
		pisoActual = 0;
		cantViajes = 0;
	}

	public void run() {

		Pedido pedido;
		try {
			ocupado = true;
			System.out.println("Arranca ascensor #" + id + " en piso "
					+ pisoActual);

			mover(pisoActual, pisoDestino);
			pisoActual = pisoDestino;

			System.out.println("Sube al ascensor #" + id);
			pedido = pisos.get(pisoActual).get();
			pisoDestino = pedido.getPisoDestino().getNumero();

			mover(pisoActual, pisoDestino);
			pisoActual = pisoDestino;
			System.out.println("Baja del ascensor #" + id);

			System.out
					.println("Para ascensor #" + id + " en piso " + pisoActual);
			ocupado = false;
			cantViajes++;
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	public synchronized boolean isOcupado() {
		return ocupado;
	}

	public synchronized void setPisoDestino(int pisoDestino) {
		this.pisoDestino = pisoDestino;
	}

	public synchronized int getPisoActual() {
		return pisoActual;
	}

	public int getId() {
		return id;
	}

	public int getCantViajes() {
		return cantViajes;
	}

	private void mover(int origen, int destino) {
		if (origen == destino)
			return;
		try {
			if (origen < destino)
				for (int i = origen; i <= destino; i++) {
					System.out.println("Ascensor #" + id + " Piso: " + i);
					Thread.sleep(TIEMPO_VIAJE);
				}
			else
				for (int i = origen; i >= destino; i--) {
					System.out.println("Ascensor #" + id + " Piso: " + i);
					Thread.sleep(TIEMPO_VIAJE);
				}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
