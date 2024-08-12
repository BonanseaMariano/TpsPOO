package models;

import java.awt.*;

public class MiRectangulo {
    private int x, y, ancho, alto;
    private Color color;
    private boolean relleno;

    public MiRectangulo(int x, int y, int ancho, int alto, Color color, boolean relleno) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.color = color;
        this.relleno = relleno;
    }

    /**
     * Dibuja un rectángulo en la pantalla según las propiedades definidas.
     *
     * @param g el objeto Graphics que se utilizará para dibujar el rectángulo
     */
    public void dibujar(Graphics g) {
        g.setColor(color);
        if (relleno) {
            g.fillRect(x, y, ancho, alto);
        } else {
            g.drawRect(x, y, ancho, alto);
        }
    }
}
