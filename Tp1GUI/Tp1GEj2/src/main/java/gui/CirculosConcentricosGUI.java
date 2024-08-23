package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Graphics;

public class CirculosConcentricosGUI extends JFrame {
    private int numCirculos;
    private int diametroInicial;
    private static final int ANCHO_FRAME = 300;
    private static final int ALTO_FRAME = 300;

    public CirculosConcentricosGUI() {
        this.cargarDatos();
        this.add(crearPanel());
        this.setSize(ANCHO_FRAME, ALTO_FRAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void cargarDatos() {
        try {
            numCirculos = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad de círculos", "Circulos", JOptionPane.QUESTION_MESSAGE));
            diametroInicial = Integer.parseInt(JOptionPane.showInputDialog(null, "Diametro en píxeles", "Diametro", JOptionPane.INFORMATION_MESSAGE));
        } catch (NumberFormatException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            System.exit(1);
        }
    }

    private JPanel crearPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Limpia la pantalla antes de pintar
                int ancho = getWidth();
                int alto = getHeight();

                for (int i = 1; i <= numCirculos; i++) {
                    int diametro = diametroInicial * i;
                    int x = (ancho - diametro) / 2;
                    int y = (alto - diametro) / 2;
                    g.drawOval(x, y, diametro, diametro);
                }
            }
        };
    }

}
