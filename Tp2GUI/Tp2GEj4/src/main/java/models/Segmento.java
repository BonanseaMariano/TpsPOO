package models;

import java.util.Objects;

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

	// el �ngulo que forma con el eje x (en radianes)
	public double getAnguloX ( ) {
			return Math.atan(
				(extremo2.getY() - extremo1.getY()) / (extremo2.getX() - extremo1.getX()) );
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Segmento segmento)) return false;
        return Objects.equals(extremo1, segmento.extremo1) && Objects.equals(extremo2, segmento.extremo2);
	}

	@Override
	public String toString() {
		return "Segmento{" +
				"extremo1=" + extremo1 +
				", extremo2=" + extremo2 +
				'}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(extremo1, extremo2);
	}
}
