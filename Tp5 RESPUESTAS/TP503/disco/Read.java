package disco;

import java.util.Random;

public class Read implements Runnable {

	private ReadWriteLock rwl;
	private final int MIN = 3000;
	private final int MAX = 5000;
	private int nro;

	public Read(ReadWriteLock rwl, int nro) {
		super();
		this.rwl = rwl;
		this.nro = nro;
	}

	@Override
	public void run() {
		Random random = new Random();
		try {
			rwl.lockRead();
			System.out.println("Comienza lectura: " + nro);
			Thread.sleep(random.nextInt(MAX - MIN + 1) + MIN);
			System.out.println("Finaliza lectura: " + nro);
			rwl.unlockRead();
		} catch (InterruptedException excepcion) {

		}
	}

}
