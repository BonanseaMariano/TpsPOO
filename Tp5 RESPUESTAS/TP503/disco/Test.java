package disco;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) {

		ReadWriteLock rwl = new ReadWriteLock();
		Read lec[] = new Read[10];
		Write esc[] = new Write[2];

		for (int i = 0; i < lec.length; i++)
			lec[i] = new Read(rwl, i + 1);

		for (int i = 0; i < esc.length; i++)
			esc[i] = new Write(rwl, i + 1);

		ExecutorService ejecutorSubprocesos = Executors.newCachedThreadPool();

		for (int i = 0; i < lec.length; i++)
			ejecutorSubprocesos.execute(lec[i]);

		for (int i = 0; i < esc.length; i++)
			ejecutorSubprocesos.execute(esc[i]);

		ejecutorSubprocesos.shutdown();

	}

}
