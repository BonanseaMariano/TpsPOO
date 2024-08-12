package gui;

import javax.swing.*;
import java.awt.*;

public class CirculosConcentricosPanel extends JPanel {
    private int numCircles;
    private int initialDiameter;

    public CirculosConcentricosPanel(int numCircles, int initialDiameter) {
        this.numCircles = numCircles;
        this.initialDiameter = initialDiameter;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Limpia la pantalla antes de pintar
        int width = getWidth();
        int height = getHeight();

        for (int i = 1; i <= numCircles; i++) {
            int diameter = initialDiameter * i;
            int x = (width - diameter) / 2;
            int y = (height - diameter) / 2;
            g.drawOval(x, y, diameter, diameter);
        }
    }

    public static void main(String[] args) {
        int circulos = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad de círculos", "Circulos", JOptionPane.QUESTION_MESSAGE));
        int diametro = Integer.parseInt(JOptionPane.showInputDialog(null, "Diametro en píxeles", "Diametro", JOptionPane.INFORMATION_MESSAGE));
        JFrame frame = crearFrame(circulos, diametro);
        frame.setVisible(true);
    }

    /**
     * Crea un nuevo marco (JFrame) con un panel de círculos concéntricos.
     *
     * @param circulos número de círculos a dibujar en el panel
     * @param diametro diámetro inicial del primer círculo
     * @return el marco creado con el panel de círculos concéntricos
     */
    public static JFrame crearFrame(int circulos, int diametro) {
        JFrame frame = new JFrame("");
        frame.add(new CirculosConcentricosPanel(circulos, diametro));
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        return frame;
    }
}
