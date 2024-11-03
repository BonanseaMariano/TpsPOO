package taxi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulacion {
	private final static Random generator = new Random();
	private static final int MAX_TAXIS = 5;
	private static final int TIEMPO_MINIMO = 1000;
	private static final int TIEMPO_MAXIMO = 8000;
	private static final int TIEMPO_FIJO = 1000;
	private static final int TIEMPO_VARIABLE = 1000;
	private static final int TOTAL_PASAJEROS = 25;

	public static void main(String[] args) {
		ExecutorService application = Executors.newCachedThreadPool();

		Parada parada = new Parada(MAX_TAXIS);

		List<Taxi> taxis = new ArrayList<Taxi>();
		for (int i = 0; i < MAX_TAXIS; i++)
			taxis.add(new Taxi("T" + (i + 1)));
		
		for (Taxi t : taxis)
			try {
				parada.set(t);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		int tiempo;
		for (int i = 0; i < TOTAL_PASAJEROS; i++) {
			try {
				tiempo = generator.nextInt(TIEMPO_MAXIMO - TIEMPO_MINIMO) + TIEMPO_MINIMO;
				application.execute(new Pasajero(i, tiempo, parada));
				Thread.sleep(generator.nextInt(TIEMPO_VARIABLE) + TIEMPO_FIJO);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		application.shutdown();

		try {
			boolean tareasTerminaron = application.awaitTermination(90, TimeUnit.SECONDS);
			if (tareasTerminaron) {
				for (Taxi t : taxis)
					System.out.println("Taxi: " + t.getId() + " realizó " + t.getViajes() + " viajes");
				System.out.println(Pasajero.getNoSube()+" pasajeros no subieron a un taxi");
			}
		} catch (InterruptedException ex) {
			System.out.println("Hubo una interrupcion mientras esperaba a que terminaran las tareas.");
		}
	}

}
