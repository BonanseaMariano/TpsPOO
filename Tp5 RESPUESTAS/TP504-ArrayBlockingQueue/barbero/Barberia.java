package barbero;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Barberia implements Buffer {
	private final ArrayBlockingQueue<Cliente> buffer;

	public Barberia(int sillas) {
		buffer = new ArrayBlockingQueue<Cliente>(sillas);		
	}

	public void set(Cliente producto) throws InterruptedException {
		buffer.put(producto);		
	}

	public Cliente get() throws InterruptedException {
		return buffer.poll(5000, TimeUnit.MILLISECONDS);		
	}

	@Override
	public synchronized int size() {		
		return buffer.size();
	}
		
}
