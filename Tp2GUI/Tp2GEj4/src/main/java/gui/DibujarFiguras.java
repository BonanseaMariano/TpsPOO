package gui;

import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class DibujarFiguras {
    private static final int ANCHO = 800;
    private static final int ALTO = 800;

    public static void main(String[] args) {
        cargarFrame().setVisible(true);
    }

    public static JPanel cargarFiguras() {
        Random r = new Random();

        int numElipses = 0;
        int numPoligonos = 0;
        int numCompuestas = 0;

        try {
            numElipses = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de elipses:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Número inválido para elipses. Se usará 0.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            numPoligonos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de polígonos:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Número inválido para polígonos. Se usará 0.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            numCompuestas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de figuras compuestas:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Número inválido para figuras compuestas. Se usará 0.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        Map<String, Figura> figuras = new LinkedHashMap<>();

        for (int i = 0; i < numElipses; i++) {
            Elipse elipse = new Elipse(r.nextDouble(ALTO), r.nextDouble(ALTO), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), r.nextDouble(ALTO));
            elipse.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            elipse.setRelleno(r.nextBoolean());
            figuras.put("Elipse" + (i + 1), elipse);
        }

        for (int i = 0; i < numPoligonos; i++) {
            Poligono poligono = new Poligono(new Punto[]{new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO))});
            poligono.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            poligono.setRelleno(r.nextBoolean());
            figuras.put("Poligono" + (i + 1), poligono);
        }

        for (int i = 0; i < numCompuestas; i++) {
            Boolean rc = r.nextBoolean();
            Elipse c1 = new Elipse(r.nextDouble(ALTO), r.nextDouble(ALTO), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), r.nextDouble(ALTO));
            Poligono c2 = new Poligono(new Punto[]{new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO))});
            Poligono c3 = new Poligono(new Punto[]{new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO)), new Punto(r.nextDouble(ALTO), r.nextDouble(ALTO))});

            c1.setRelleno(rc);
            c2.setRelleno(rc);
            c3.setRelleno(rc);

            FiguraCompuesta compuesta = new FiguraCompuesta(new Figura[]{c1, c2, c3});
            compuesta.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            figuras.put("Compuesta" + (i + 1), compuesta);
        }

        PanelDibujo panel = new PanelDibujo(figuras);
        panel.setSize(ANCHO, ALTO);
        return panel;
    }

    public static JFrame cargarFrame() {
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ALTO, ANCHO);
        frame.setLocationRelativeTo(null);
        frame.add(cargarFiguras(), BorderLayout.CENTER);
        return frame;
    }
}