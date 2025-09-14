import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
    public static void main(String[] args) throws Exception {

        Posicionamiento_05 app = new Posicionamiento_05(new JFrame("Ejercicio_05"));

        JFrame ventana = app.getVentana();
        ventana.setSize(400, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null); // Add this for absolute positioning
        app.generatePaneles(8);
        ventana.setVisible(true);

        // This is an abomination. There are better ways to do this instead of hardcoding it.
        ArrayList<JPanel> paneles = app.getPaneles();
        paneles.get(0).setBounds(10, 10, 210, 100);
        paneles.get(0).setBackground(new Color(76, 235, 169));

        paneles.get(1).setBounds(10, 120, 100, 50);
        paneles.get(1).setBackground(Color.red);
        
        paneles.get(2).setBounds(10, 180, 100, 120);
        paneles.get(2).setBackground(new Color(255, 109, 0));

        paneles.get(3).setBounds(120, 120, 100, 180);
        paneles.get(3).setBackground(new Color(240, 0, 255));

        paneles.get(4).setBounds(230, 10, 100, 70);
        paneles.get(4).setBackground(new Color(0,128,0));

        paneles.get(5).setBounds(230, 90, 100, 150);
        paneles.get(5).setBackground(new Color(1, 80, 159));

        paneles.get(6).setBounds(230, 250, 100, 50);
        paneles.get(6).setBackground(new Color(202, 158, 97));
        
    }
}
