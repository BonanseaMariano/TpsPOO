// Elipse.java
package carlosfontela.geometria;

import java.awt.*;

public class Elipse extends Figura {

    private double radioMayor;
    private double radioMenor;
    private Punto centro;
    private double anguloRadioMayor;
    private boolean relleno;
    private Color color;

    public Elipse(double radioMayor, double radioMenor, Punto centro, double anguloRadioMayor) {
        this.radioMayor = radioMayor;
        this.radioMenor = radioMenor;
        this.centro = centro;
        this.anguloRadioMayor = anguloRadioMayor;
        this.relleno = false;
        this.color = Color.black;
    }

    public Elipse(double radioMayor, double radioMenor) {
        this.radioMayor = radioMayor;
        this.radioMenor = radioMenor;
        this.centro = new Punto(0, 0);
        this.anguloRadioMayor = 0;
        this.relleno = false;
        this.color = Color.black;
    }

    public double getRadioMayor() {
        return radioMayor;
    }

    public double getRadioMenor() {
        return radioMenor;
    }

    public Punto getCentro() {
        return centro;
    }

    public double getAnguloRadioMayor() {
        return anguloRadioMayor;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    public double area() {
        return Math.PI * getRadioMayor() * getRadioMenor();
    }

    private static double E1(double k) {
        // TODO: tabla de integral elíptica
        return 0;
    }

    public double perimetro() {
        // caso del círculo:
        if (getRadioMayor() == getRadioMenor())
            return (Math.PI * 2 * getRadioMayor());
        // caso general:
        double k = Math.sqrt(Math.pow(getRadioMayor(), 2) -
                Math.pow(getRadioMenor(), 2)) / Math.pow(getRadioMayor(), 2);
        return (4 * getRadioMayor() * E1(k));
    }

    public String tipo() {
        if (getRadioMayor() == getRadioMenor())
            return "círculo";
        else return "elipse";
    }

    public void trasladar(double deltaX, double deltaY) {
        centro.trasladar(deltaX, deltaY);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        if (relleno)
            g.fillOval((int) centro.getX() - (int) (getRadioMenor() / 2),
                    (int) centro.getY() - (int) (getRadioMenor() / 2),
                    (int) getRadioMenor(), (int) getRadioMenor());
        else
            g.drawOval((int) centro.getX() - (int) (getRadioMenor() / 2),
                    (int) centro.getY() - (int) (getRadioMenor() / 2),
                    (int) getRadioMenor(), (int) getRadioMenor());
    }

}
