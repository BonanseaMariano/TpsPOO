package cine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulacion {
	private static final int MAX_ESPECTADORES = 6;
	private static final int MAX_BOLETERIAS = 2;

	public static void main(String[] args) {
		ExecutorService aplicacion = Executors.newCachedThreadPool();

		ColaBoleteria colaBoleteria = new ColaBoleteria();
		ColaIngreso colaIngreso = new ColaIngreso();
		LocalidadVenta numerador = new LocalidadVenta();

		Boleteria boleterias[] = new Boleteria[MAX_BOLETERIAS];
		for (int i = 0; i < MAX_BOLETERIAS; i++)
			boleterias[i] = new Boleteria(i + 1, colaBoleteria, colaIngreso, numerador);

		aplicacion.execute(new CreaEspectador(colaBoleteria, MAX_ESPECTADORES));
		for (int i = 0; i < MAX_BOLETERIAS; i++)
			aplicacion.execute(boleterias[i]);
		aplicacion.execute(new Acomodador(colaIngreso));

		aplicacion.shutdown();

		try {
			boolean tareasTerminaron = aplicacion.awaitTermination(45, TimeUnit.SECONDS);
			if (tareasTerminaron) {
				for (int i = 0; i < MAX_BOLETERIAS; i++)
					System.out.println("Boletería: " + (boleterias[i].getId()) + " vendió "
							+ boleterias[i].getEntradasVendidas() + " entradas");
			} else
				System.out.println("Hay tareas en ejecución");
		} catch (InterruptedException ex) {
			System.out.println("Hubo una interrupcion mientras esperaba a que terminaran las tareas.");
		}
	}

}
