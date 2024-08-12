package models;

import java.awt.*;

public class MiLinea {
    protected int x1, y1, x2, y2;
    protected Color color;

    public MiLinea(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
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
