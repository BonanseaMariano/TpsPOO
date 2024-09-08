// FiguraCompuesta.java
package models;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class FiguraCompuesta extends Figura {

    private Figura[] componentes;
    private Color color;

    public FiguraCompuesta(Figura[] componentes) {
        this.componentes = componentes;
        this.color = Color.black;
    }

    public double area() {
        // TODO: hacer bien este m�todo
        // esta es una simplificaci�n que supone que no hay superposici�n entre componentes
        double superficie = 0;
        for (int i = 0; i < componentes.length; i++)
            superficie += componentes[i].area();
        return superficie;
    }

    public double perimetro() {
        // TODO: hacer bien este m�todo
        // esta es una simplificaci�n que supone que no hay superposici�n entre componentes
        double perimetro = 0;
        for (int i = 0; i < componentes.length; i++)
            perimetro += componentes[i].perimetro();
        return perimetro;
    }

    public String tipo() {
        return "figura compuesta";
    }

    public void trasladar(double deltaX, double deltaY) {
        for (int i = 0; i < componentes.length; i++)
            componentes[i].trasladar(deltaX, deltaY);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        for (int i = 0; i < componentes.length; i++)
            componentes[i].dibujar(g);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        for (Figura f : componentes)
            f.setColor(color);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FiguraCompuesta that)) return false;
        return Objects.deepEquals(componentes, that.componentes) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(componentes), color);
    }

    @Override
    public String toString() {
        return "FiguraCompuesta{" +
                "componentes=" + Arrays.toString(componentes) +
                ", color=" + color +
                '}';
    }
}
