package disco;

import java.util.Random;

public class Write implements Runnable {

	private ReadWriteLock rwl;
	private final int MIN = 3000;
	private final int MAX = 5000;
	private int nro;

	public Write(ReadWriteLock rwl, int nro) {
		super();
		this.rwl = rwl;
		this.nro = nro;
	}

	@Override
	public void run() {
		Random random = new Random();
		try {
			rwl.lockWrite();
			System.out.println("Comienza escritura: " + nro);
			Thread.sleep(random.nextInt(MAX - MIN + 1) + MIN);
			System.out.println("Finaliza escritura: " + nro);
			rwl.unlockWrite();
		} catch (InterruptedException excepcion) {

		}
	}

}
