package models;

import java.awt.*;

public class MiOvalo extends MiFigura {
    private boolean relleno;

    public MiOvalo() {
        super();
        relleno = false;
    }

    public MiOvalo(int x, int y, int x2, int y2, Color color, boolean relleno) {
        super(x, y, x2, y2, color);
        this.relleno = relleno;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(getColor());
        if (relleno) {
            g.fillOval(getX1(), getY1(), getX2(), getY2());
        } else {
            g.drawOval(getX1(), getY1(), getX2(), getY2());
        }
    }
}
