package gui;

import models.MiLinea;
import models.MiOvalo;
import models.MiRectangulo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PanelDibujo extends JPanel {
    private MiLinea[] lineas;
    private MiOvalo[] ovalos;
    private MiRectangulo[] rectangulos;

    public PanelDibujo(JLabel barraEstado) {
        Random rand = new Random();

        // Crear de 5 a 10 líneas
        int numLineas = 5 + rand.nextInt(6);
        lineas = new MiLinea[numLineas];
        for (int i = 0; i < numLineas; i++) {
            int x1 = rand.nextInt(400);
            int y1 = rand.nextInt(400);
            int x2 = rand.nextInt(400);
            int y2 = rand.nextInt(400);
            Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            lineas[i] = new MiLinea(x1, y1, x2, y2, color);
        }

        // Crear de 5 a 10 óvalos
        int numOvalos = 5 + rand.nextInt(6);
        ovalos = new MiOvalo[numOvalos];
        for (int i = 0; i < numOvalos; i++) {
            int x = rand.nextInt(300);
            int y = rand.nextInt(300);
            int ancho = rand.nextInt(100) + 20;
            int alto = rand.nextInt(100) + 20;
            Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            boolean relleno = rand.nextBoolean();
            ovalos[i] = new MiOvalo(x, y, ancho, alto, color, relleno);
        }

        // Crear de 5 a 10 rectángulos
        int numRectangulos = 5 + rand.nextInt(6);
        rectangulos = new MiRectangulo[numRectangulos];
        for (int i = 0; i < numRectangulos; i++) {
            int x = rand.nextInt(300);
            int y = rand.nextInt(300);
            int ancho = rand.nextInt(100) + 20;
            int alto = rand.nextInt(100) + 20;
            Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            boolean relleno = rand.nextBoolean();
            rectangulos[i] = new MiRectangulo(x, y, ancho, alto, color, relleno);
        }

        // Actualizar la barra de estado
        barraEstado.setText("Líneas: " + numLineas + ", Ovalos: " + numOvalos + ", Rectángulos: " + numRectangulos);
    }

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
}
