// Poligono.java
package carlosfontela.geometria;

import java.awt.*;

public class Poligono extends Figura {

    private Punto[] contorno;
    private boolean relleno;
    private Color color;

    public Poligono(Punto[] contorno) {
        if (contorno.length < 3)
            // veremos el significado de lo que sigue en un cap�tulo posterior:
            throw new IllegalArgumentException();
        this.contorno = contorno;
        this.relleno = false;
        this.color = Color.black;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    static protected double areaTrapecio(Punto p1, Punto p2) {
        return (p1.getY() + p2.getY()) * (p2.getX() - p1.getX()) / 2;
    }

    public int numeroLados() {
        return contorno.length;
    }

    public double area() {
        double superficie =
                areaTrapecio(contorno[numeroLados() - 1], contorno[0]);
        for (int i = 0; i < numeroLados() - 1; i++)
            superficie += areaTrapecio(contorno[i], contorno[i + 1]);
        return superficie;
    }

    public double perimetro() {
        double longitud = contorno[numeroLados() - 1].distancia(contorno[0]);
        for (int i = 0; i < numeroLados() - 1; i++)
            longitud += contorno[i].distancia(contorno[i + 1]);
        return longitud;
    }

    private Segmento lado(int i) {
        if (i < numeroLados() - 1)
            return new Segmento(contorno[i], contorno[i + 1]);
        else return new Segmento(contorno[i], contorno[0]);
    }

    // verifica si es un pol�gono con todos sus lados iguales
    public boolean regular() {
        for (int i = 0; i < contorno.length - 1; i++) {
            if (lado(i).getLongitud() != lado(i + 1).getLongitud())
                return false;
        }
        return true;
    }

    public String tipo() {
        if (numeroLados() == 3 && regular())
            return "triangulo equilatero";
        if (numeroLados() == 4 && regular())
            return "cuadrado";
        String nombre = null;
        switch (numeroLados()) {
            case 3:
                nombre = "triangulo";
                break;
            case 4:
                nombre = "cuadrilatero";
                break;
            case 5:
                nombre = "pentagono";
                break;
            case 6:
                nombre = "hexagono";
                break;
            case 7:
                nombre = "heptagono";
                break;
            case 8:
                nombre = "octogono";
                break;
            case 9:
                nombre = "nonogono";
                break;
            case 10:
                nombre = "decagono";
                break;
            case 12:
                nombre = "dodecagono";
                break;
            case 20:
                nombre = "icosogono";
                break;
            default:
                nombre = "sin nombre";
        }
        if (regular())
            nombre += " regular";
        return nombre;
    }

    public void trasladar(double deltaX, double deltaY) {
        for (int i = 0; i < contorno.length; i++)
            contorno[i].trasladar(deltaX, deltaY);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        //Arreglo de coordenadas x
        int[] x = new int[contorno.length];
        //Arreglo de coordenadas y
        int[] y = new int[contorno.length];

        //Se separan las coordenadas x e y
        for (int i = 0; i < contorno.length; i++) {
            x[i] = (int) contorno[i].getX();
            y[i] = (int) contorno[i].getY();
        }

        if (relleno)
            g.fillPolygon(x, y, contorno.length);
        else
            g.drawPolygon(x, y, contorno.length);
    }

    @Override
    public Color getColor() {
        return color;
    }
}
