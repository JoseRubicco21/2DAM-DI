package ejercicio_6;

import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import ejercicio.Ejercicio;
import graphics_manager.DisplayManager;
import graphics_manager.log.AnsiColor;

public class Ejercicio_6 extends Ejercicio {

    private Window w;

    public Ejercicio_6(DisplayManager dp, Window w) {
        super(dp, w);
        this.w = w;
        w.setLocation(dp.getCenter().toPoint());
        w.setLayout(new BorderLayout());
        w.setVisible(true);
        this.main();
    }

    @Override
    public void main() {
        // Create title label
        JLabel titleLabel = new JLabel("Selecciona un Color", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create combo box panel
        JPanel comboPanel = new JPanel();
        
        JComboBox<String> option = new JComboBox<>();
        option.addItem("Rojo");
        option.addItem("Azul");
        option.addItem("Verde");
        option.addItem("Amarillo");
        option.addItem("Morado");
        
        option.setFont(new Font("Arial", Font.PLAIN, 14));
        option.setPreferredSize(new Dimension(200, 30));
        
        comboPanel.add(option);
        comboPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        option.addActionListener(e -> {
            String selectedColor = (String) option.getSelectedItem();
            AnsiColor color = getAnsiColor(selectedColor);
            System.out.println("Color seleccionado: " + color + selectedColor + AnsiColor.RESET);
        });
        
        // Add components to window
        w.add(titleLabel, BorderLayout.NORTH);
        w.add(comboPanel, BorderLayout.CENTER);
        
        // Set window background
        if (w instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) w).getContentPane().setBackground(new Color(250, 250, 250));
        }
    }
    
    private AnsiColor getAnsiColor(String colorName) {
        switch (colorName.toLowerCase()) {
            case "rojo": return AnsiColor.RED;
            case "azul": return AnsiColor.BLUE;
            case "verde": return AnsiColor.GREEN;
            case "amarillo": return AnsiColor.YELLOW;
            case "morado": return AnsiColor.PURPLE;
            default: return AnsiColor.RESET;
        }
    }
}
