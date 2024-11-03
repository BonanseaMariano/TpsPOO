package heladeria;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Cola implements Buffer {
	private final ArrayBlockingQueue<Cliente> buffer;

	public Cola(int cantidad) {
		buffer = new ArrayBlockingQueue<Cliente>(cantidad);
	}

	public void set(Cliente value) throws InterruptedException {
		buffer.put(value);
	}

	public Cliente get() throws InterruptedException {
		return buffer.poll(20000, TimeUnit.MILLISECONDS);
	}

}
