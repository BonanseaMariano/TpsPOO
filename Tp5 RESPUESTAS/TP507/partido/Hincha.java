package partido;

public class Hincha {

	private int id;
	private int cantidadEntradas;
	private boolean local;

	public Hincha(int id, boolean local, int cantidadEntradas) {
		super();
		this.id = id;
		this.local = local;
		this.cantidadEntradas = cantidadEntradas;		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCantidadEntradas() {
		return cantidadEntradas;
	}

	public void setCantidadEntradas(int cantidadEntradas) {
		this.cantidadEntradas = cantidadEntradas;
	}

	public boolean isLocal() {
		return local;
	}

	public void setLocal(boolean local) {
		this.local = local;
	}
				
}
