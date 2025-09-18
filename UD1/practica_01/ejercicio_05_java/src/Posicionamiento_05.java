import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Posicionamiento_05 {
    private ArrayList<JPanel> paneles;
    private JFrame ventana;

    public Posicionamiento_05(JFrame vFrame) {
        paneles = new ArrayList<>();
        ventana = vFrame;
    }

    public ArrayList<JPanel> getPaneles() {
        return paneles;
    }

    public void setPaneles(ArrayList<JPanel> paneles) {
        this.paneles = paneles;
    }

    public JFrame getVentana() {
        return ventana;
    }

    public void setVentana(JFrame ventana) {
        this.ventana = ventana;
    }
    
    public void generatePaneles(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            JPanel panel = new JPanel();
            panel.setBounds(0,0, 0, 0);
            panel.setBackground(Color.RED);
            paneles.add(panel);
            ventana.add(panel);
        }
    }
    
}
