package gui;

import utils.ConversionUnidades;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ConversionUnidadesPanel extends JPanel {
    private JTextField fahrenheitField;
    private JLabel resultLabel;

    public ConversionUnidadesPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel fahrenheitLabel = new JLabel("Grados Fahrenheit:");
        fahrenheitField = new JTextField();
        fahrenheitField.setPreferredSize(new Dimension(100, 20)); // Ajustar el tamaño preferido
        JButton convertButton = new JButton("Calcular");
        resultLabel = new JLabel("Grados Celsius: ");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(fahrenheitLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(fahrenheitField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(resultLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(convertButton, gbc);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double fahrenheit = Double.parseDouble(fahrenheitField.getText());
                    double celsius = ConversionUnidades.fahrenheitToCelsius(fahrenheit);
                    resultLabel.setText("Grados Celsius: " + celsius);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        convertButton.setMnemonic(KeyEvent.VK_ENTER);
    }
}