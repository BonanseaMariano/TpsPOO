package partido;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulacion {
	private static final int MAX_HINCHAS = 20;
	private static final int MAX_BOLETERIAS = 3;

	public static void main(String[] args) {
		ExecutorService aplicacion = Executors.newCachedThreadPool();

		ColaBoleteria colaBoleteria = new ColaBoleteria();
		ColaIngreso colaLocal = new ColaIngreso();
		ColaIngreso colaVisitante = new ColaIngreso();
		VentaEntrada ventaEntrada = new VentaEntrada();

		aplicacion.execute(new CreaHincha(colaBoleteria, MAX_HINCHAS));

		for (int i = 0; i < MAX_BOLETERIAS; i++)
			aplicacion
					.execute(new Boleteria(i + 1, colaBoleteria, colaLocal, colaVisitante, ventaEntrada));

		PuertaIngreso puertaIngresoLocal = new PuertaIngreso("Local", colaLocal);
		PuertaIngreso puertaIngresoVisitante = new PuertaIngreso("Visitante", colaVisitante);
		aplicacion.execute(puertaIngresoLocal);
		aplicacion.execute(puertaIngresoVisitante);

		aplicacion.shutdown();

		try {
			boolean tareasTerminaron = aplicacion.awaitTermination(60, TimeUnit.SECONDS);
			if (tareasTerminaron) {
				System.out.println("Puerta Ingreso " + puertaIngresoLocal.getNombre() + ": ingresaron "
						+ puertaIngresoLocal.getHinchas() + " hinchas");
				System.out.println("Puerta Ingreso " + puertaIngresoVisitante.getNombre() + ": ingresaron "
						+ puertaIngresoVisitante.getHinchas() + " hinchas");
			} else
				System.out.println("Hay tareas en ejecución");
		} catch (InterruptedException ex) {
			System.out.println("Hubo una interrupcion mientras esperaba a que terminaran las tareas.");
		}
	}

}
