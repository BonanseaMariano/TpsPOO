package cine;

public class LocalidadVenta {

	private static final int TOTAL_ASIENTOS = 12;

	private int numeroEntrada;

	public LocalidadVenta() {
		this.numeroEntrada = 1;

	}

	public synchronized Entrada[] getEntradas(int cantidad) {		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (TOTAL_ASIENTOS - numeroEntrada < cantidad)
			return null;
		Entrada entradas[] = new Entrada[cantidad];
		for (int i = 0; i < cantidad; i++)
			entradas[i] = new Entrada(numeroEntrada++);				

		return entradas;
	}
}
