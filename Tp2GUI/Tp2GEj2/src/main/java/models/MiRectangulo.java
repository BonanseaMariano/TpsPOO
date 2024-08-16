package models;

import java.awt.*;

public class MiRectangulo extends MifiguraDelimitada {

    public MiRectangulo() {
        super();
    }

    public MiRectangulo(int x, int y, int x2, int y2, Color color, boolean relleno) {
        super(x, y, x2, y2, color, relleno);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(getColor());
        if (getRelleno()) {
            g.fillRect(getX1(), getY1(), getX2(), getY2());
        } else {
            g.drawRect(getX1(), getY1(), getX2(), getY2());
        }
    }
}
