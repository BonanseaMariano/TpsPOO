package models;

import java.awt.*;

public class MiOvalo {
    private int x, y, ancho, alto;
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
