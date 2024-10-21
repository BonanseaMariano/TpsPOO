package ascensor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulacion {

	private static final int MAX_PISOS = 6;
	private static final int MAX_ASCENSORES = 1;
	private static final int TOTAL_PERSONAS = 3;

	public static void main(String[] args) {
		ExecutorService application = Executors.newCachedThreadPool();

		List<Piso> pisos = new ArrayList<Piso>();
		List<Ascensor> ascensores = new ArrayList<Ascensor>();

		for (int i = 0; i < MAX_PISOS; i++)
			pisos.add(new Piso(i));

		Pedido pedidos = new Pedido();

		CreaPersona creaPersona = new CreaPersona(MAX_PISOS, TOTAL_PERSONAS,
				pedidos, pisos);

		for (int i = 0; i < MAX_ASCENSORES; i++)
			ascensores.add(new Ascensor(i + 1, pisos));

		Controlador controlador = new Controlador(pedidos, pisos, ascensores,
				TOTAL_PERSONAS);

		application.execute(creaPersona);
		application.execute(controlador);

		application.shutdown();
				
	}
}
