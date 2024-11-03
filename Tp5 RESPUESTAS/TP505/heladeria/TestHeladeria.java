package heladeria;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestHeladeria {

	public static void main(String[] args) {
		simular(2, 6, 12);
	}

	public static void simular(int nroCajas, int nroAtencion, int clientes) {

		ExecutorService application = Executors.newCachedThreadPool();
		Buffer colaCaja = new Cola(clientes);
		Buffer colaAtencion = new Cola(clientes);
		List<Caja> cajas = new ArrayList<Caja>();
		List<Atencion> atenciones = new ArrayList<Atencion>();

		application.execute(new GeneradorCliente(colaCaja, clientes));

		Caja c;
		for (int i = 1; i <= nroCajas; i++) {
			c = new Caja("C" + i, colaCaja, colaAtencion);
			cajas.add(c);
			application.execute(c);
		}

		Atencion a;
		for (int i = 1; i <= nroAtencion; i++) {
			a = new Atencion("A" + i, colaAtencion);
			atenciones.add(a);
			application.execute(a);
		}
		application.shutdown();

		try {
			boolean tareasTerminaron = application.awaitTermination(120, TimeUnit.SECONDS);
			if (tareasTerminaron) {
				for (Atencion atencion : atenciones)
					System.out.println("Puesto atencion: " + atencion.getNombre() + " - atendio "
							+ atencion.getCantidad() + " clientes");

			}
		} catch (InterruptedException ex) {
			System.out.println("Hubo una interrupcion mientras esperaba a que terminaran las tareas.");
		}

	}
}
