package models;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Objects;

public class MiLinea {
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected Color color;

    public MiLinea(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MiLinea miLinea)) return false;
        return x1 == miLinea.x1 && y1 == miLinea.y1 && x2 == miLinea.x2 && y2 == miLinea.y2 && Objects.equals(color, miLinea.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, color);
    }

    @Override
    public String toString() {
        return "MiLinea{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", color=" + color +
                '}';
    }

    /**
     * Dibuja una linea en la pantalla según las propiedades definidas.
     *
     * @param g el objeto Graphics que se utilizará para dibujar la linea
     */
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}
