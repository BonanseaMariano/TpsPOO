package pruebas;

import carlosfontela.geometria.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DibujarFiguras {
    private static Figura[] figuras;
    private static FiguraCompuesta compuesta;
    private static Elipse elipse;
    private static Poligono poligono;
    private static Segmento segmento;

    private static final int ANCHO = 500;
    private static final int ALTO = 500;

    public static void main(String[] args) {
        cargarFiguras();
        cargarFrame(cargarPanel()).setVisible(true);
    }

    public static void cargarFiguras() {
        Random r = new Random();
        elipse = new Elipse(r.nextDouble(ALTO), r.nextDouble(ALTO), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), r.nextDouble(ALTO));
        elipse.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        elipse.setRelleno(r.nextBoolean());

        poligono = new Poligono(new Punto[]{new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO))});
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

        compuesta = new FiguraCompuesta(new Figura[]{c1, c2, c3});
        compuesta.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

        //Cargo arreglo de figuras
        figuras = new Figura[]{elipse, poligono, compuesta};
    }

    public static JPanel cargarPanel() {
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Figura f : figuras) {
                    f.dibujar(g);
                }
            }
        };

        panel.setSize(ALTO, ANCHO);
        panel.repaint();

        return panel;
    }

    public static JFrame cargarFrame(JPanel panel) {
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ALTO, ANCHO);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        return frame;
    }

}
