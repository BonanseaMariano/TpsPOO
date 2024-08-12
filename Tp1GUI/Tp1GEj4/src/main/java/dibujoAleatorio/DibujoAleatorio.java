package dibujoAleatorio;

import gui.PanelDibujo;

import javax.swing.*;
import java.awt.*;

public class DibujoAleatorio {
    public static void main(String[] args) {
        JFrame frame = crearFrame();
        frame.setVisible(true);
    }

    public static JFrame crearFrame() {
        JFrame frame = new JFrame();

        JLabel barraEstado = new JLabel();

        frame.setSize(500, 500); //Setear el tamaño del frame
        frame.setLayout(new BorderLayout());

        frame.add(new PanelDibujo(barraEstado), BorderLayout.CENTER); //Centrar el dibujo
        barraEstado.setFont(new Font("Arial", Font.BOLD, 15)); //Establecer la fuente
        frame.add(barraEstado, BorderLayout.SOUTH); //Poner la barra de estado abajo


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Para cerrar el frame
        frame.setLocationRelativeTo(null); //Centrar el frame

        return frame;
    }
}
