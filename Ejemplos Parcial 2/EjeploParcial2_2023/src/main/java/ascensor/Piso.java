package ascensor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Piso {

	private int numero;
	private final ArrayBlockingQueue<Persona> cola;
	private final int MAX_TIEMPO = 1000;
	private final int MAX_COLA = 100;

	public Piso(int numero) {
		this.numero = numero;
		cola = new ArrayBlockingQueue<Persona>(MAX_COLA);
	}

	public void set(Persona persona) throws InterruptedException {
		cola.put(persona);
	}

	public Persona get() throws InterruptedException {
		return cola.poll(MAX_TIEMPO, TimeUnit.MILLISECONDS);
	}

	public int size() {
		return cola.size();
	}

	public int getNumero() {
		return numero;
	}

	
	
	
}
