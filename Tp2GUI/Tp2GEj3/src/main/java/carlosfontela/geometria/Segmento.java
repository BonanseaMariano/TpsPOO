package carlosfontela.geometria;

public class Segmento {

	private Punto extremo1;
	private Punto extremo2;

	public Segmento (Punto p1, Punto p2) {
		extremo1 = p1;
		extremo2 = p2;
	}

	public Punto extremo (int i) {
		if (i == 1)
			return extremo1;
		else return extremo2;
	}

	public void cambiarExtremo (int i, Punto p) {
		if (i == 1)
			extremo1 = p;
		else extremo2 = p;
	}

	public double getLongitud ( ) {
		return extremo1.distancia(extremo2);
	}

	// el ángulo que forma con el eje x (en radianes)
	public double getAnguloX ( ) {
			return Math.atan(
				(extremo2.getY() - extremo1.getY()) / (extremo2.getX() - extremo1.getX()) );
	}
}
