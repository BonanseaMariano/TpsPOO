package partido;

import java.util.Random;

public class Boleteria implements Runnable {
	private static final int TIEMPO_FIJO_ATENCION = 1000;
	private static final int TIEMPO_VARIABLE_ATENCION = 1000;
	private final static Random generator = new Random();

	private ColaBoleteria colaBoleteria;
	private ColaIngreso colaLocal;
	private ColaIngreso colaVisitante;
	private int id;
	private VentaEntrada ventaEntrada;

	public Boleteria(int id, ColaBoleteria colaBoleteria, ColaIngreso colaLocal, ColaIngreso colaVisitante,
			VentaEntrada ventaEntrada) {
		this.id = id;
		this.colaBoleteria = colaBoleteria;
		this.colaLocal = colaLocal;
		this.colaVisitante = colaVisitante;
		this.ventaEntrada = ventaEntrada;
	}

	public void run() {

		try {			
			Hincha hincha = colaBoleteria.get();			
			while (hincha != null) {
				Thread.sleep(generator.nextInt(TIEMPO_VARIABLE_ATENCION) + TIEMPO_FIJO_ATENCION);
				if (hincha.isLocal())
					if (ventaEntrada.getEntradasLocales(hincha.getCantidadEntradas())) {
						colaLocal.set(hincha);
						for (int i = 1; i < hincha.getCantidadEntradas(); i++)
							colaLocal.set(new Hincha(hincha.getId() * 1000 + i, hincha.isLocal(), 0));
						System.out.println("Boletería " + id + " -> Hincha: " + hincha.getId() + " compró "
								+ hincha.getCantidadEntradas()
								+ (hincha.getCantidadEntradas() == 1 ? " entrada" : " entradas"));
					} else
						System.out.println("Boletería " + id + " -> Hincha: " + hincha.getId() + " no pudo comprar "
								+ hincha.getCantidadEntradas()
								+ (hincha.getCantidadEntradas() == 1 ? " entrada" : " entradas")
								+ ". No hay esa cantidad de entradas disponibles");
				else {

					if (ventaEntrada.getEntradasVisitantes(hincha.getCantidadEntradas())) {
						colaVisitante.set(hincha);
						for (int i = 1; i < hincha.getCantidadEntradas(); i++)
							colaVisitante.set(new Hincha(hincha.getId() * 10 + i, hincha.isLocal(), 0));
						System.out.println("Boletería " + id + " -> Hincha: " + hincha.getId() + " compró "
								+ hincha.getCantidadEntradas()
								+ (hincha.getCantidadEntradas() == 1 ? " entrada" : " entradas"));					
					} else
						System.out.println("Boletería " + id + " -> Hincha: " + hincha.getId() + " no pudo comprar "
								+ hincha.getCantidadEntradas()
								+ (hincha.getCantidadEntradas() == 1 ? " entrada" : " entradas")
								+ ". No hay esa cantidad de entradas disponibles");
				}
				hincha = colaBoleteria.get();
			}

		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

}
