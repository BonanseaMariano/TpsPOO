package gui;

import models.MiLinea;
import models.MiOvalo;
import models.MiRectangulo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Dibujo extends JFrame {
    private MiLinea[] lineas;
    private MiOvalo[] ovalos;
    private MiRectangulo[] rectangulos;
    private JLabel barraEstado;
    private static final int ANCHO_PANTALLA = 500;
    private static final int ALTO_PANTALLA = 500;
    private static final int MIN_FIGURAS = 5;
    private static final int MAX_FIGURAS = 10;

    public Dibujo() {
        this.barraEstado = new JLabel();
        this.setSize(ANCHO_PANTALLA, ALTO_PANTALLA);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(barraEstado, BorderLayout.SOUTH);
        cargarFiguras();
        this.add(cargarPanel(), BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void cargarFiguras() {
        Random rand = new Random();

        // Crear de 5 a 10 líneas
        int numLineas = rand.nextInt(MIN_FIGURAS, MAX_FIGURAS + 1);
        lineas = new MiLinea[numLineas];
        for (int i = 0; i < numLineas; i++) {
            int x1 = rand.nextInt(ANCHO_PANTALLA);
            int y1 = rand.nextInt(ALTO_PANTALLA);
            int x2 = rand.nextInt(ANCHO_PANTALLA);
            int y2 = rand.nextInt(ALTO_PANTALLA);
            Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            lineas[i] = new MiLinea(x1, y1, x2, y2, color);
        }

        // Crear de 5 a 10 óvalos
        int numOvalos = rand.nextInt(MIN_FIGURAS, MAX_FIGURAS + 1);
        ovalos = new MiOvalo[numOvalos];
        for (int i = 0; i < numOvalos; i++) {
            int x = rand.nextInt(ANCHO_PANTALLA);
            int y = rand.nextInt(ALTO_PANTALLA);
            int ancho = rand.nextInt(ANCHO_PANTALLA);
            int alto = rand.nextInt(ALTO_PANTALLA);
            Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            boolean relleno = rand.nextBoolean();
            ovalos[i] = new MiOvalo(x, y, ancho, alto, color, relleno);
        }

        // Crear de 5 a 10 rectángulos
        int numRectangulos = rand.nextInt(MIN_FIGURAS, MAX_FIGURAS + 1);
        rectangulos = new MiRectangulo[numRectangulos];
        for (int i = 0; i < numRectangulos; i++) {
            int x = rand.nextInt(ANCHO_PANTALLA);
            int y = rand.nextInt(ALTO_PANTALLA);
            int ancho = rand.nextInt(ANCHO_PANTALLA);
            int alto = rand.nextInt(ALTO_PANTALLA);
            Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            boolean relleno = rand.nextBoolean();
            rectangulos[i] = new MiRectangulo(x, y, ancho, alto, color, relleno);
        }

        // Actualizar la barra de estado
        barraEstado.setText("Líneas: " + numLineas + ", Ovalos: " + numOvalos + ", Rectángulos: " + numRectangulos);
    }

    private JPanel cargarPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); //Limpia la pantalla

                // Dibujar todas las líneas
                for (MiLinea linea : lineas) {
                    linea.dibujar(g);
                }

                // Dibujar todos los óvalos
                for (MiOvalo ovalo : ovalos) {
                    ovalo.dibujar(g);
                }

                // Dibujar todos los rectángulos
                for (MiRectangulo rectangulo : rectangulos) {
                    rectangulo.dibujar(g);
                }
            }
        };
    }


}
