package ascensor;

public class Persona {
	private int id;
	private Piso pisoOrigen;
	private Piso pisoDestino;

	
	public Persona(int id, Piso pisoOrigen, Piso pisoDestino) {
		super();
		this.id = id;
		this.pisoOrigen = pisoOrigen;
		this.pisoDestino = pisoDestino;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Piso getPisoOrigen() {
		return pisoOrigen;
	}


	public void setPisoOrigen(Piso pisoOrigen) {
		this.pisoOrigen = pisoOrigen;
	}


	public Piso getPisoDestino() {
		return pisoDestino;
	}


	public void setPisoDestino(Piso pisoDestino) {
		this.pisoDestino = pisoDestino;
	}


	@Override
	public String toString() {
		return id + " [" + pisoOrigen.getNumero() + ", " + pisoDestino.getNumero() + "]";
	}

}
