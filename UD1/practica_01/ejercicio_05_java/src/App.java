import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class App {
    public static void main(String[] args) throws Exception {

        Posicionamiento_05 app = new Posicionamiento_05(new JFrame("Ejercicio_05"));

        final int WIDTH = 400;
        final int HEIGHT = 400;
        final int INSETS = 5;   

        JFrame ventana = app.getVentana();
        ventana.setSize(WIDTH, HEIGHT);
        ventana.setLayout(new GridBagLayout());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSETS, INSETS, INSETS, INSETS);   
        app.generatePaneles(7);
        ventana.setVisible(true);

        // This is an abomination. There are better ways to do this instead of hardcoding it.
        ArrayList<JPanel> paneles = app.getPaneles();

        // Grid is 8x8:
        // +---+---+---+---+---+---+---+---+
        // Panel 0 : 6x4 at 0x0
        // +---+---+---+---+---+---+---+---+
        // Panel 1:  2x3 at 6x0
        // +---+---+---+---+---+---+---+---+
        // Panel 2:  2x1 at 6x3
        // +---+---+---+---+---+---+---+---+
        // Panel 3:  2x1 at 0x4
        // +---+---+---+---+---+---+---+---+
        // Panel 4:  2x3 at 3x4
        // +---+---+---+---+---+---+---+---+
        // Panel 5:  3x2 at 0x6
        // +---+---+---+---+---+---+---+---+
        // Panel 6:  3x2 at 5x6
        // +---+---+---+---+---+---+---+---+
        

        // Panel 0: TURQUOISE (T)
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 4; gbc.gridheight = 4;
        gbc.weightx = 1; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        paneles.get(0).setBackground(PColors.TURQUOISE.color);
        ventana.add(paneles.get(0), gbc);

        // Panel 1: RED (R)
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2; gbc.gridheight = 1;
        paneles.get(1).setBackground(PColors.RED.color);
        ventana.add(paneles.get(1), gbc);

        // Panel 2: ORANGE (O)
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2; gbc.gridheight = 3;
        paneles.get(2).setBackground(PColors.ORANGE.color);
        ventana.add(paneles.get(2), gbc);

        // Panel 3: PINK (P)
        gbc.gridx = 2; gbc.gridy = 4;
        gbc.gridwidth = 2; gbc.gridheight = 4;
        paneles.get(3).setBackground(PColors.PINK.color);
        ventana.add(paneles.get(3), gbc);

        // Panel 4: GREEN (G)
        gbc.gridx = 4; gbc.gridy = 0;
        gbc.gridwidth = 2; gbc.gridheight = 3;
        paneles.get(4).setBackground(PColors.GREEN.color);
        ventana.add(paneles.get(4), gbc);

        // Panel 5: BLUE (B)
        gbc.gridx = 4; gbc.gridy = 3;
        gbc.gridwidth = 2; gbc.gridheight = 4;
        paneles.get(5).setBackground(PColors.BLUE.color);
        ventana.add(paneles.get(5), gbc);

        // Panel 6: BEIGE (X)
        gbc.gridx = 4; gbc.gridy = 7;
        gbc.gridwidth = 2; gbc.gridheight = 1;
        paneles.get(6).setBackground(PColors.BEIGE.color);
        ventana.add(paneles.get(6), gbc);

        ventana.revalidate();
        ventana.repaint();
    }
}
