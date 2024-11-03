package taxi;

public class Pasajero implements Runnable {
	private int id;
	private int tiempo;
	private Parada parada;
	private static int noSube;

	public Pasajero(int id, int tiempo, Parada parada) {
		super();
		this.id = id;
		this.tiempo = tiempo;
		this.parada = parada;
	}

	public void run() {
		try {
			Taxi taxi = parada.get();
			if (taxi == null) {
				System.out.println("El pasajero: " + id + " no sube a un taxi");
				noSube++;
				return;
			}
			System.out.println(
					"El pasajero: " + id + " sube al taxi: " + taxi.getId() + " tiempo total recorrido: " + tiempo * 2);
			// tiempo que le lleva dejar al pasajero y volver a la parada
			Thread.sleep(tiempo * 2);
			taxi.setViajes(taxi.getViajes()+1);
			parada.set(taxi);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int getNoSube() {
		return noSube;
	}

}
