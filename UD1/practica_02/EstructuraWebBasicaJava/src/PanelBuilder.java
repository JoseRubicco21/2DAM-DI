import javax.swing.JPanel;
import java.awt.Label;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class PanelBuilder {

    public ArrayList<JPanel> panels = new ArrayList<>();

    public JPanel createPanel(int x, int y, int width, int height, Color color, String labelText) {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        panel.setBackground(color);
        Label l = new Label(labelText, Label.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(l);
        panels.add(panel);
        return panel;
    }
}
