package cine;

public class Espectador {

	private int id;
	private int cantidadEntradas;
	private Entrada entrada;

	public Espectador(int id, int cantidadEntradas) {
		super();
		this.id = id;
		this.cantidadEntradas = cantidadEntradas;
	}

	public Espectador(int id, Entrada entrada) {
		super();
		this.id = id;
		this.entrada = entrada;		
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

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}
		
}
