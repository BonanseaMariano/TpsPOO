// Figura.java
package carlosfontela.geometria;

import java.awt.*;

public abstract class Figura {
    public abstract double area();

    public abstract double perimetro();

    public abstract String tipo();

    public abstract void trasladar(double deltaX, double deltaY);

    public abstract void dibujar(Graphics g);

    public abstract void setColor(Color color);
}
