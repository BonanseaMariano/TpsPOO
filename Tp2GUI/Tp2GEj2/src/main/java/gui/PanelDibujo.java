package gui;

import models.MiFigura;
import models.MiLinea;
import models.MiOvalo;
import models.MiRectangulo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PanelDibujo extends JPanel {
    private MiFigura[] figuras;

    public PanelDibujo(JLabel barraEstado, int cantidadFiguras) {
        figuras = new MiFigura[cantidadFiguras];

        Random rand = new Random();

        // Distribuir las figuras aleatoriamente entre líneas, óvalos y rectángulos
        int numLineas = rand.nextInt(cantidadFiguras / 3) + 1; // mínimo 1 línea
        int numOvalos = rand.nextInt(cantidadFiguras / 3) + 1; // mínimo 1 óvalo
        int numRectangulos = cantidadFiguras - numLineas - numOvalos;

        // Ajustar los números para que no superen la cantidad total de figuras
        if (numRectangulos < 0) {
            numRectangulos = 0;
            numOvalos -= (numRectangulos - 0);
            if (numOvalos < 0) {
                numOvalos = 0;
                numLineas -= (numOvalos - 0);
            }
        }

        // Cargo lineas al arreglo de figuras
        for (int i = 0; i < numLineas; i++) {
            figuras[i] = new MiLinea(rand.nextInt(300), rand.nextInt(300), rand.nextInt(300), rand.nextInt(300), new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        }

        // Cargo ovalos al arreglo de figuras
        for (int i = numLineas; i < numLineas + numOvalos; i++) {
            figuras[i] = new MiOvalo(rand.nextInt(300), rand.nextInt(300), rand.nextInt(300), rand.nextInt(300), new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)), rand.nextBoolean());
        }

        // Cargo rectangulos al arreglo de figuras
        for (int i = numLineas + numOvalos; i < cantidadFiguras; i++) {
            figuras[i] = new MiRectangulo(rand.nextInt(300), rand.nextInt(300), rand.nextInt(300), rand.nextInt(300), new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)), rand.nextBoolean());
        }

        // Actualizar la barra de estado
        barraEstado.setText("Líneas: " + numLineas + ", Ovalos: " + numOvalos + ", Rectángulos: " + numRectangulos);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //Limpia la pantalla

        // Dibujar todas las figuras
        for (MiFigura figura : figuras) {
            figura.dibujar(g);
        }

    }
}
