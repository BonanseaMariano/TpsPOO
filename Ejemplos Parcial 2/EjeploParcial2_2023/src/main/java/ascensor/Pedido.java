package ascensor;

import java.util.concurrent.ArrayBlockingQueue;

public class Pedido {

	private final ArrayBlockingQueue<Persona> cola;
	private final int MAX_COLA = 1000;

	public Pedido() {
		cola = new ArrayBlockingQueue<Persona>(MAX_COLA);
	}

	public void set(Persona persona) throws InterruptedException {
		cola.put(persona);
	}

	public Persona get() throws InterruptedException {
		return cola.take();
	}

	public int size() {
		return cola.size();
	}
}
