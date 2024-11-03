package cine;

import java.util.Random;

public class Boleteria implements Runnable {
	private static final int TIEMPO_FIJO_ATENCION = 1000;
	private static final int TIEMPO_VARIABLE_ATENCION = 2000;
	private final static Random generator = new Random();

	private ColaBoleteria colaBoleteria;
	private ColaIngreso colaIngreso;
	private int id;
	private int entradasVendidas;
	private LocalidadVenta localidadVenta;

	public Boleteria(int id, ColaBoleteria colaBoleteria, ColaIngreso colaIngreso, LocalidadVenta localidadVenta) {
		this.id = id;
		this.colaBoleteria = colaBoleteria;
		this.colaIngreso = colaIngreso;		
		this.entradasVendidas = 0;
		this.localidadVenta = localidadVenta;
	}

	public void run() {

		try {
			Espectador espectadorNuevo;
			Espectador espectador = colaBoleteria.get();
			Entrada entradas[];
			while (espectador != null) {
				entradas = localidadVenta.getEntradas(espectador.getCantidadEntradas());
				if (entradas == null)
					System.out.println("Boletería " + id + " -> Espectador: " + espectador.getId() + " no pudo comprar "
							+ espectador.getCantidadEntradas()
							+ (espectador.getCantidadEntradas() == 1 ? " entrada" : " entradas")
							+ ". No hay esa cantidad de entradas disponibles");
				else {
					espectador.setEntrada(entradas[0]);
					Thread.sleep(generator.nextInt(TIEMPO_VARIABLE_ATENCION) + TIEMPO_FIJO_ATENCION);
					colaIngreso.set(espectador);
					entradasVendidas++;
					for (int i = 1; i < espectador.getCantidadEntradas(); i++) {
						espectadorNuevo = new Espectador(espectador.getId(), entradas[i]);
						colaIngreso.set(espectadorNuevo);
						entradasVendidas++;
					}
					System.out.println("Boletería " + id + " -> Espectador: " + espectador.getId() + " compra "
							+ espectador.getCantidadEntradas()
							+ (espectador.getCantidadEntradas() == 1 ? " entrada" : " entradas"));
				}
				espectador = colaBoleteria.get();
			}

		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
	
	public int getId() {
		return id;
	}

	public int getEntradasVendidas() {
		return entradasVendidas;
	}

}
