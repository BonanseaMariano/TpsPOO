package ascensor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulacion {

	private static final int MAX_PISOS = 6;
	private static final int MAX_ASCENSORES = 2;
	private static final int TOTAL_PEDIDOS = 3;

	public static void main(String[] args) {
		ExecutorService application = Executors.newCachedThreadPool();

		List<Piso> pisos = new ArrayList<Piso>();
		List<Ascensor> ascensores = new ArrayList<Ascensor>();

		for (int i = 0; i < MAX_PISOS; i++)
			pisos.add(new Piso(i));

		ColaPedido pedidos = new ColaPedido();

		CreaPedido creaPedido = new CreaPedido(MAX_PISOS, TOTAL_PEDIDOS,
				pedidos, pisos);

		for (int i = 0; i < MAX_ASCENSORES; i++)
			ascensores.add(new Ascensor(i + 1, pisos));

		Controlador controlador = new Controlador(pedidos, pisos, ascensores,
				TOTAL_PEDIDOS);

		application.execute(creaPedido);
		application.execute(controlador);

		application.shutdown();

		try {
			boolean tareasTerminaron = application.awaitTermination(2000000, TimeUnit.SECONDS);
			if (tareasTerminaron) {
				for (int i = 0; i < MAX_ASCENSORES; i++)
					System.out.println("Ascensor: " +ascensores.get(i).getId() + " realizó " + ascensores.get(i).getCantViajes()  + " viajes");
				
				System.out.println("Pedidos descartados: "+controlador.getPedidosDescartados());
			}
		} catch (InterruptedException ex) {
			System.out.println("Hubo una interrupcion mientras esperaba a que terminaran las tareas.");
		}
		
	}
}
