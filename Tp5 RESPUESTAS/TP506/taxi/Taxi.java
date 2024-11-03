package taxi;

public class Taxi {

	private String id;
	private int viajes;

	public Taxi(String id) {		
		this.id = id;
	}

	public int getViajes() {
		return viajes;
	}

	public void setViajes(int viajes) {
		this.viajes = viajes;
	}

	public String getId() {
		return id;
	}
		
}
