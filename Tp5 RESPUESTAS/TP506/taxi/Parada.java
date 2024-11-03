package taxi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Parada implements Buffer {

	private final ArrayBlockingQueue<Taxi> cola;	
	private final int MAX_TIEMPO = 1000;
		
	public Parada(int nroTaxis) {	
		cola = new ArrayBlockingQueue<Taxi>(nroTaxis);
	}

	@Override
	public void set(Taxi taxi) throws InterruptedException {
		cola.put(taxi);
	}

	@Override
	public Taxi get() throws InterruptedException {
		return cola.poll(MAX_TIEMPO, TimeUnit.MILLISECONDS);
	}

}
