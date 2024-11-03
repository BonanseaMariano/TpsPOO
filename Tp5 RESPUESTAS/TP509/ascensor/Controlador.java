package ascensor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controlador implements Runnable {

	private ColaPedido pedidos;
	private List<Piso> pisos;
	private List<Ascensor> ascensores;
	private int totalPedidos;
	private int pedidosDescartados;
	private ExecutorService application = Executors.newCachedThreadPool();

	public Controlador(ColaPedido pedidos, List<Piso> pisos,
			List<Ascensor> ascensores, int totalPedidos) {
		this.pedidos = pedidos;
		this.pisos = pisos;
		this.ascensores = ascensores;
		this.totalPedidos = totalPedidos;
		this.pedidosDescartados = 0;
	}

	public void run() {
		Pedido pedido;
		int nroAscensor;
		try {
			for (int count = 1; count <= totalPedidos; count++) {
				pedido = pedidos.get();
				nroAscensor = ascensorLibre(pedido.getPisoOrigen().getNumero());
				if (nroAscensor != -1) {
					pisos.get(pedido.getPisoOrigen().getNumero()).set(pedido);
					ascensores.get(nroAscensor).setPisoDestino(
							pedido.getPisoOrigen().getNumero());
					application.execute(ascensores.get(nroAscensor));
				} else {
					System.out.println("Pedido descartado: " + pedido.getId());
					pedidosDescartados++;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		application.shutdown();
	}

	private int ascensorLibre(int pisoActual) {
		int ascensor = -1;
		int masCerca = pisos.size();
		for (int i = 0; i < ascensores.size(); i++)
			if (!ascensores.get(i).isOcupado())
				if (Math.abs(pisoActual - ascensores.get(i).getPisoActual()) < masCerca) {
					masCerca = Math.abs(pisoActual
							- ascensores.get(i).getPisoActual());
					ascensor = i;
				}
		return ascensor;
	}

	public int getPedidosDescartados() {
		return pedidosDescartados;
	}

	
}
