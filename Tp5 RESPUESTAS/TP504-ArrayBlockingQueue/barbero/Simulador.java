package barbero;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulador {
	private static final int SILLAS = 3;

	public static void main(String[] args) {

		ExecutorService application = Executors.newCachedThreadPool();
		Buffer sharedLocation = new Barberia(SILLAS);

		application.execute(new GeneraCliente(sharedLocation, SILLAS));
		application.execute(new Barbero(sharedLocation));

		application.shutdown();
	}
}