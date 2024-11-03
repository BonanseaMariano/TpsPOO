package ascensor;

import java.util.concurrent.ArrayBlockingQueue;

public class ColaPedido {

	private final ArrayBlockingQueue<Pedido> cola;
	private final int MAX_COLA = 1000;

	public ColaPedido() {
		cola = new ArrayBlockingQueue<Pedido>(MAX_COLA);
	}

	public void set(Pedido pedido) throws InterruptedException {
		cola.put(pedido);
	}

	public Pedido get() throws InterruptedException {
		return cola.take();
	}

	public int size() {
		return cola.size();
	}
}
