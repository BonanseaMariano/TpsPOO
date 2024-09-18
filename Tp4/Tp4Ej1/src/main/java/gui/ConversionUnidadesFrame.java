package gui;

import javax.swing.*;

public class ConversionUnidadesFrame extends JFrame {
    public ConversionUnidadesFrame() {
        setTitle("Conversión de Temperatura");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new ConversionUnidadesPanel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConversionUnidadesFrame().setVisible(true);
            }
        });
    }
}