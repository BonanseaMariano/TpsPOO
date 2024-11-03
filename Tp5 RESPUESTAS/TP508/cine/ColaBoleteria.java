package cine;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ColaBoleteria {

	private final ArrayBlockingQueue<Espectador> cola;	
	private final int MAX_EXPECTADORES = 1000;
	private final int MAX_TIEMPO = 10000;
		
	public ColaBoleteria() {	
		cola = new ArrayBlockingQueue<Espectador>(MAX_EXPECTADORES);
	}
	
	public void set(Espectador pedido) throws InterruptedException {
		cola.put(pedido);
	}

	public Espectador get() throws InterruptedException {
		return cola.poll(MAX_TIEMPO, TimeUnit.MILLISECONDS);
	}

}
