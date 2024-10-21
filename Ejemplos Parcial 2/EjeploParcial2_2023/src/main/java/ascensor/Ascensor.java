package ascensor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ascensor implements Runnable {

	private static final int LUGARES = 6;
	private static final int TIEMPO_VIAJE = 1000;

	private int id;
	private List<Piso> pisos;
	private List<Persona> personas;
	private boolean ocupado;
	private boolean sube;
	private int pisoActual;
	private boolean tablero[];
	private int nroPisos;

	public Ascensor(int id, List<Piso> pisos) {
		this.id = id;
		this.pisos = pisos;
		this.personas = new ArrayList<Persona>();
		nroPisos = pisos.size();
		this.tablero = new boolean[nroPisos];
		ocupado = false;
		sube = true;
		pisoActual = 0;
	}

	public void run() {
		Persona persona;
		boolean hayPersona;
		ocupado = true;
		int piso;
		try {
			System.out.println("Arranca ascensor " + id);
			piso = pisoActual;
			while (piso != -1) {
				Iterator<Persona> p = personas.iterator();
				// bajan personas del ascensor
				while (p.hasNext()) {
					persona = p.next();
					if (persona.getPisoDestino().getNumero() == piso) {
						System.out.println("Ascensor " + id
								+ " - Baja persona: " + persona);
						p.remove();
					}
				}
				// suben personas al asecensor
				hayPersona = true;
				while (personas.size() < LUGARES && hayPersona) {
					persona = pisos.get(piso).get();
					if (persona == null) {
						hayPersona = false;
					} else {
						System.out.println("Ascensor " + id
								+ " - Sube persona: " + persona);
						personas.add(persona);
						setTablero(persona.getPisoDestino().getNumero(), true);
					}
				}
				setTablero(pisoActual, false);
				piso = proximoPiso();
				if (piso != -1) {
					for (int i = pisoActual; i <= piso; i++) {
						System.out.println("Piso: " + i);
						Thread.sleep(TIEMPO_VIAJE);
					}
					for (int i = pisoActual; i >= piso; i--) {
						System.out.println("Piso: " + i);
						Thread.sleep(TIEMPO_VIAJE);
					}

					pisoActual = piso;
				}
			}
			ocupado = false;
			System.out.println("Para ascensor " + id);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	public synchronized boolean isOcupado() {
		return ocupado;
	}

	public synchronized boolean getTablero(int piso) {
		return tablero[piso];
	}

	public synchronized void setTablero(int piso, boolean estado) {
		tablero[piso] = estado;
	}

	private int proximoPiso() {
		if (sube) {
			for (int i = pisoActual; i < nroPisos; i++)
				if (getTablero(i)) {
					sube = true;
					return i;
				}
			for (int i = pisoActual; i >= 0; i--)
				if (getTablero(i)) {
					sube = false;
					return i;
				}
		} else {
			for (int i = pisoActual; i >= 0; i--)
				if (getTablero(i)) {
					sube = false;
					return i;
				}
			for (int i = pisoActual; i < nroPisos; i++)
				if (getTablero(i)) {
					sube = true;
					return i;
				}
		}
		return -1;
	}
}
