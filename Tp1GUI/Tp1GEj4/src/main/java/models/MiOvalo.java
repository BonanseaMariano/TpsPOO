package models;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Objects;

public class MiOvalo {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private Color color;
    private boolean relleno;

    public MiOvalo(int x, int y, int ancho, int alto, Color color, boolean relleno) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.color = color;
        this.relleno = relleno;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isRelleno() {
        return relleno;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MiOvalo miOvalo)) return false;
        return x == miOvalo.x && y == miOvalo.y && ancho == miOvalo.ancho && alto == miOvalo.alto && relleno == miOvalo.relleno && Objects.equals(color, miOvalo.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, ancho, alto, color, relleno);
    }

    @Override
    public String toString() {
        return "MiOvalo{" +
                "x=" + x +
                ", y=" + y +
                ", ancho=" + ancho +
                ", alto=" + alto +
                ", color=" + color +
                ", relleno=" + relleno +
                '}';
    }

    /**
     * Dibuja un óvalo en la pantalla según las propiedades definidas.
     *
     * @param g el objeto Graphics que se utilizará para dibujar el óvalo
     */
    public void dibujar(Graphics g) {
        g.setColor(color);
        if (relleno) {
            g.fillOval(x, y, ancho, alto);
        } else {
            g.drawOval(x, y, ancho, alto);
        }
    }
}
