package carlosfontela.geometria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelDibujo extends JPanel {
    private Figura[] figuras;
    private Integer figuraActual;
    private JLabel lblFigura, lblPaso;
    private int paso;

    public PanelDibujo(Elipse elipse, Poligono poligono, FiguraCompuesta compuesta) {
        figuras = new Figura[]{elipse, poligono, compuesta};
        setLayout(new BorderLayout());
        this.add(cargarBotones(), BorderLayout.SOUTH);
        this.add(cargarHeader(), BorderLayout.NORTH);

    }

    private JPanel cargarHeader() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        lblFigura = new JLabel("Figura seleccionada:");
        lblPaso = new JLabel("Paso: 0");
        lblFigura.setFont(new Font("Arial", Font.BOLD, 20));
        lblPaso.setFont(new Font("Arial", Font.BOLD, 20));

        lblFigura.setBackground(Color.WHITE);
        lblFigura.setOpaque(true);
        lblPaso.setBackground(Color.WHITE);
        lblPaso.setOpaque(true);

        panel.add(lblFigura, BorderLayout.WEST);
        panel.add(lblPaso, BorderLayout.EAST);

        panel.setOpaque(false);

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        return panel;
    }

    private JPanel cargarBotones() {
        JButton b1 = new JButton("Figura");
        JButton b2 = new JButton("Color");
        JButton b3 = new JButton("Paso");
        JButton b4 = new JButton("Izquierda");
        JButton b5 = new JButton("Arriba");
        JButton b6 = new JButton("Abajo");
        JButton b7 = new JButton("Derecha");

        //Figura
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String op = (String) JOptionPane.showInputDialog(null, "Seleccione una figura:", "Figura", JOptionPane.PLAIN_MESSAGE, null, new String[]{"Ninguna", "Todas las Figuras", "Elipse", "Poligono", "Compuesta"}, 0);
                switch (op) {
                    case "Todas las Figuras":
                        lblFigura.setText("Figura seleccionada: Todas las figuras");
                        figuraActual = -1;
                        break;
                    case "Elipse":
                        lblFigura.setText("Figura seleccionada: Elipse");
                        figuraActual = 0;
                        break;
                    case "Poligono":
                        lblFigura.setText("Figura seleccionada: Poligono");
                        figuraActual = 1;
                        break;
                    case "Compuesta":
                        lblFigura.setText("Figura seleccionada: Compuesta");
                        figuraActual = 2;
                        break;
                    case null:
                        break;
                    default:
                        lblFigura.setText("Figura seleccionada:");
                        figuraActual = null;
                        break;
                }

                if (figuraActual != null) {
                    b2.setEnabled(true);
                    b4.setEnabled(true);
                    b5.setEnabled(true);
                    b6.setEnabled(true);
                    b7.setEnabled(true);
                } else {
                    b2.setEnabled(false);
                    b4.setEnabled(false);
                    b5.setEnabled(false);
                    b6.setEnabled(false);
                    b7.setEnabled(false);
                }

            }
        });

        //Color
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (figuraActual != null) {
                    if (figuraActual != -1)
                        figuras[figuraActual].setColor(JColorChooser.showDialog(null, "Elige un color para la figura", figuras[figuraActual].getColor()));
                    else {
                        Color color = JColorChooser.showDialog(null, "Elige un color para todas las figuras", Color.black);
                        for (Figura f : figuras)
                            f.setColor(color);
                    }
                    repaint();
                }
            }
        });

        //Paso
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    paso = Integer.parseInt(JOptionPane.showInputDialog(null, "Paso en pixel", "Paso", JOptionPane.QUESTION_MESSAGE));
                } catch (NumberFormatException ex) {
                }
                lblPaso.setText("Paso: " + paso);
            }
        });

        //Izquierda
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (figuraActual != null) {
                    if (figuraActual != -1)
                        figuras[figuraActual].trasladar(-paso, 0);
                    else {
                        for (Figura f : figuras)
                            f.trasladar(-paso, 0);
                    }
                    repaint();
                }
            }
        });

        //Arriba
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (figuraActual != null) {
                    if (figuraActual != -1)
                        figuras[figuraActual].trasladar(0, -paso);
                    else {
                        for (Figura f : figuras)
                            f.trasladar(0, -paso);
                    }
                    repaint();
                }
            }
        });

        //Abajo
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (figuraActual != null) {
                    if (figuraActual != -1)
                        figuras[figuraActual].trasladar(0, paso);
                    else {
                        for (Figura f : figuras)
                            f.trasladar(0, paso);
                    }
                    repaint();
                }
            }
        });

        //Derecha
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (figuraActual != null) {
                    if (figuraActual != -1)
                        figuras[figuraActual].trasladar(paso, 0);
                    else {
                        for (Figura f : figuras)
                            f.trasladar(paso, 0);
                    }
                    repaint();
                }
            }
        });

        //Para que se pueda mover la figura presionando alt+flechas direccionales
        b4.setMnemonic(KeyEvent.VK_LEFT);
        b5.setMnemonic(KeyEvent.VK_UP);
        b6.setMnemonic(KeyEvent.VK_DOWN);
        b7.setMnemonic(KeyEvent.VK_RIGHT);

        b2.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);

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
