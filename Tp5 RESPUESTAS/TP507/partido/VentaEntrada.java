package partido;

public class VentaEntrada {

	private static final int TOTAL_LOCAL = 30;
	private static final int TOTAL_VISITANTE = 30;

	private int entradasLocales;
	private int entradasVisitantes;

	public VentaEntrada() {
		this.entradasLocales = 0;
		this.entradasVisitantes = 0;
	}

	public synchronized boolean getEntradasLocales(int cantidad) {	
		if (TOTAL_LOCAL - entradasLocales >= cantidad) {
			entradasLocales += cantidad;
			return true;
		}
		return false;
	}

	public synchronized boolean getEntradasVisitantes(int cantidad) {	
		if (TOTAL_VISITANTE - entradasVisitantes >= cantidad) {
			entradasVisitantes += cantidad;
			return true;
		}
		return false;
	}
}
