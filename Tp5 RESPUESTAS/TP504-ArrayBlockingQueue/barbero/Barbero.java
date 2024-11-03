package barbero;

public class Barbero implements Runnable {

	private final Buffer sharedLocation;

	public Barbero(Buffer shared) {
		sharedLocation = shared;
	}

	public void run() {
		Cliente cliente;
		try {
			while ((cliente = sharedLocation.get()) != null) {

				Thread.sleep(5000);
				System.out.println("Barbero atiende cliente: " + cliente.getId());

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Cierra la barberia");
	}

}
