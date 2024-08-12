import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CrossLinesPanel extends JPanel {

    public CrossLinesPanel() {
        // Asegura que el panel se redibuje cuando cambie de tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        // Dibujar líneas cruzadas
        g.drawLine(0, height / 2, width, height / 2); // Línea horizontal
        g.drawLine(width / 2, 0, width / 2, height);  // Línea vertical
    }

    public static JFrame crearFrame() {
        JFrame frame = new JFrame();
        frame.add(new CrossLinesPanel());
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    public static void main(String[] args) {
        JFrame frame = crearFrame();
        frame.setVisible(true);
    }
}
