package partido;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ColaIngreso {

	private final ArrayBlockingQueue<Hincha> cola;	
	private final int MAX_EXPECTADORES = 1000;
	private final int MAX_TIEMPO = 10000;
		
	public ColaIngreso() {	
		cola = new ArrayBlockingQueue<Hincha>(MAX_EXPECTADORES);
	}
	
	public void set(Hincha pedido) throws InterruptedException {
		cola.put(pedido);
	}

	public Hincha get() throws InterruptedException {
		return cola.poll(MAX_TIEMPO, TimeUnit.MILLISECONDS);
	}

}
