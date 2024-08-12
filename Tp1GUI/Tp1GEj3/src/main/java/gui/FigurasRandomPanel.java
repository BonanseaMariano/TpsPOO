package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

public class FigurasRandomPanel extends JPanel {

    FigurasRandomPanel() {

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //Limpia la pantalla
        Random rand = new Random();
        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < 10; i++) {
            // Generar un color aleatorio
            int r = rand.nextInt(256);
            int gColor = rand.nextInt(256);
            int b = rand.nextInt(256);
            Color randomColor = new Color(r, gColor, b);
            g.setColor(randomColor);

            // Generar dimensiones aleatorias limitadas a la mitad del ancho/alto del panel
            int shapeWidth = rand.nextInt(width / 2);
            int shapeHeight = rand.nextInt(height / 2);

            // Generar coordenadas aleatorias para la figura
            int x = rand.nextInt(width - shapeWidth);
            int y = rand.nextInt(height - shapeHeight);

            // Decidir si dibujar un rectángulo o un óvalo
            if (rand.nextBoolean()) {
                g.fillRect(x, y, shapeWidth, shapeHeight); // Dibujar un rectángulo relleno
            } else {
                g.fillOval(x, y, shapeWidth, shapeHeight); // Dibujar un óvalo relleno
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = crearFrame();
        frame.setVisible(true);
    }

    public static JFrame crearFrame() {
        JFrame frame = new JFrame("");
        frame.add(new FigurasRandomPanel());
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }
}
