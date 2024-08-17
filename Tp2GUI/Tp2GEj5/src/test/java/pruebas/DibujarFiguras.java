package pruebas;

import carlosfontela.geometria.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DibujarFiguras {
    private static final int ANCHO = 800;
    private static final int ALTO = 800;

    public static void main(String[] args) {
        JFrame frame = cargarFrame();
        frame.add(cargarFiguras(), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static JPanel cargarFiguras() {
        Random r = new Random();
        Elipse elipse = new Elipse(r.nextDouble(ALTO), r.nextDouble(ALTO), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), r.nextDouble(ALTO));
        elipse.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        elipse.setRelleno(r.nextBoolean());

        Poligono poligono = new Poligono(new Punto[]{new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO))});
        poligono.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        poligono.setRelleno(r.nextBoolean());


        Boolean rc = r.nextBoolean();
        Elipse c1 = new Elipse(r.nextDouble(ALTO), r.nextDouble(ALTO), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), r.nextDouble(ALTO));
        Poligono c2 = new Poligono(new Punto[]{new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO))});
        Poligono c3 = new Poligono(new Punto[]{new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO))});

        //Relleno y color compuesta
        c1.setRelleno(rc);
        c2.setRelleno(rc);
        c3.setRelleno(rc);

        FiguraCompuesta compuesta = new FiguraCompuesta(new Figura[]{c1, c2, c3});
        compuesta.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        PanelDibujo panel = new PanelDibujo(elipse, poligono, compuesta);
        panel.setSize(ANCHO, ALTO);
        return panel;
    }

    public static JFrame cargarFrame() {
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ALTO, ANCHO);
        frame.setLocationRelativeTo(null);
        return frame;
    }

}
