package ascensor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controlador implements Runnable {

	private Pedido pedidos;
	private List<Piso> pisos;
	private List<Ascensor> ascensores;
	private int totalPersonas;
	private ExecutorService application = Executors.newCachedThreadPool();

	public Controlador(Pedido pedidos, List<Piso> pisos,
			List<Ascensor> ascensores, int totalPersonas) {
		this.pedidos = pedidos;
		this.pisos = pisos;
		this.ascensores = ascensores;
		this.totalPersonas = totalPersonas;
	}

	public void run() {
		Persona persona;
		int nroAscensor;
		try {
			for (int count = 1; count <= totalPersonas; count++) {
				persona = pedidos.get();
				pisos.get(persona.getPisoOrigen().getNumero()).set(persona);
				if (!estaPedido(persona.getPisoOrigen().getNumero())) {
					nroAscensor = ascensorLibre();
					if (nroAscensor != -1) {
						ascensores.get(nroAscensor).setTablero(
								persona.getPisoOrigen().getNumero(), true);
						application.execute(ascensores.get(nroAscensor));
					} else
						ascensores.get(0).setTablero(
								persona.getPisoOrigen().getNumero(), true);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		application.shutdown();
	}

	private boolean estaPedido(int piso) {
		for (Ascensor asc : ascensores)
			if (asc.isOcupado() && asc.getTablero(piso))
				return true;
		return false;
	}

	private int ascensorLibre() {
		for (int i = 0; i < ascensores.size(); i++)
			if (!ascensores.get(i).isOcupado())
				return i;
		return -1;
	}

}
