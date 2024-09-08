package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanelDibujo extends JPanel {
    private Map<String, Figura> figuras;
    private String figuraActual;
    private JLabel lblFigura, lblPaso;
    private int paso;

    public PanelDibujo(Map<String, Figura> figuras) {
        this.figuras = figuras;
        this.figuras.put("Todas las figuras", new FiguraCompuesta(figuras.values().toArray(new Figura[figuras.size()])));
        setLayout(new BorderLayout());
        this.add(cargarBotones(), BorderLayout.SOUTH);
        this.add(cargarHeader(), BorderLayout.NORTH);
    }

    /**
     * Crea un panel de encabezado con etiquetas que muestran la figura seleccionada y el paso actual.
     *
     * @return un JPanel que contiene el encabezado con etiquetas para la figura seleccionada y el paso.
     */
    private JPanel cargarHeader() {
        // Crear un nuevo JPanel con BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Inicializar etiquetas para mostrar la figura seleccionada y el paso
        lblFigura = new JLabel("Figura seleccionada:");
        lblPaso = new JLabel("Paso: 0");

        // Establecer la fuente para las etiquetas
        lblFigura.setFont(new Font("Arial", Font.BOLD, 20));
        lblPaso.setFont(new Font("Arial", Font.BOLD, 20));

        // Establecer el color de fondo y la opacidad para las etiquetas
        lblFigura.setBackground(Color.WHITE);
        lblFigura.setOpaque(true);
        lblPaso.setBackground(Color.WHITE);
        lblPaso.setOpaque(true);

        // Añadir etiquetas al panel con posiciones de diseño apropiadas
        panel.add(lblFigura, BorderLayout.WEST);
        panel.add(lblPaso, BorderLayout.EAST);

        // Establecer la opacidad y el borde del panel
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

        // Devolver el panel construido
        return panel;
    }

    /**
     * Crea un panel con botones que permiten seleccionar una figura, cambiar su color,
     * y moverla en diferentes direcciones. También permite establecer el paso de movimiento.
     *
     * @return un JPanel que contiene los botones de control.
     */
    private JPanel cargarBotones() {
        JButton b1 = new JButton("Figura");
        JButton b2 = new JButton("Color");
        JButton b3 = new JButton("Paso");
        JButton b4 = new JButton("Izquierda");
        JButton b5 = new JButton("Arriba");
        JButton b6 = new JButton("Abajo");
        JButton b7 = new JButton("Derecha");

        // Figura
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String op = (String) JOptionPane.showInputDialog(null, "Seleccione una figura:", "Figura", JOptionPane.PLAIN_MESSAGE, null, figuras.keySet().toArray(), 0);
                if (op != null) {
                    figuraActual = op;
                    lblFigura.setText("Figura seleccionada: " + op);
                } else {
                    figuraActual = null;
                    lblFigura.setText("Figura seleccionada:");
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

        // Color
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (figuraActual != null) {
                    figuras.get(figuraActual).setColor(JColorChooser.showDialog(null, "Elige un color para " + figuraActual, figuras.get(figuraActual).getColor()));
                    repaint();
                }
            }
        });

        // Paso
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    paso = Integer.parseInt(JOptionPane.showInputDialog(null, "Paso en pixel", "Paso", JOptionPane.QUESTION_MESSAGE));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El paso debe ser un numero entero", "Error", JOptionPane.ERROR_MESSAGE);
                }
                lblPaso.setText("Paso: " + paso);
            }
        });

        // Izquierda
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (figuraActual != null) {
                    figuras.get(figuraActual).trasladar(-paso, 0);
                    repaint();
                }
            }
        });

        // Arriba
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (figuraActual != null) {
                    figuras.get(figuraActual).trasladar(0, -paso);
                    repaint();
                }
            }
        });

        // Abajo
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (figuraActual != null) {
                    figuras.get(figuraActual).trasladar(0, paso);
                    repaint();
                }
            }
        });

        // Derecha
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (figuraActual != null) {
                    figuras.get(figuraActual).trasladar(paso, 0);
                    repaint();
                }
            }
        });

        // Para que se pueda mover la figura presionando alt+flechas direccionales
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
        for (Figura f : figuras.values()) {
            f.dibujar(g);
        }
    }

}
