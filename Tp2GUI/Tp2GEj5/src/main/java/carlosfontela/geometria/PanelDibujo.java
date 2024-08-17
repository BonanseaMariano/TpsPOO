package carlosfontela.geometria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDibujo extends JPanel {
    private Figura[] figuras;
    private Integer figuraActual;

    public PanelDibujo(Elipse elipse, Poligono poligono, FiguraCompuesta compuesta) {
        figuras = new Figura[]{elipse, poligono, compuesta};
        setLayout(new BorderLayout());
        this.add(cargarBotones(), BorderLayout.SOUTH);
    }

    private JPanel cargarBotones() {
        JButton b1 = new JButton("Figura");
        JButton b2 = new JButton("Color");
        JButton b3 = new JButton("Paso");
        JButton b4 = new JButton("Izquierda");
        JButton b5 = new JButton("Arriba");
        JButton b6 = new JButton("Abajo");
        JButton b7 = new JButton("Derecha");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                figuraActual = JOptionPane.showOptionDialog(null, "Seleccione una figura", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Todas", "Elipse", "Poligono", "Compuesta"}, null);
                System.out.println(figuraActual);
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton[] buttons = new JButton[]{b1, b2, b3, b4, b5, b6, b7};
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 7, 5, 0));
        for (JButton button : buttons) {
            panel.add(button);
        }

        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        return panel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Figura f : figuras) {
            f.dibujar(g);
        }
    }
}
