package models;

import java.awt.*;

public abstract class MifiguraDelimitada extends MiFigura {
    private boolean relleno;

    public MifiguraDelimitada() {
        super();
        relleno = false;
    }

    public MifiguraDelimitada(int x, int y, int x2, int y2, Color color, boolean relleno) {
        super(x, y, x2, y2, color);
        this.relleno = relleno;
    }

    public boolean getRelleno() {
        return relleno;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

}
