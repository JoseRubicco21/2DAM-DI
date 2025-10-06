package ejercicio_3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ejercicio.Ejercicio;
import graphics_manager.DisplayManager;
import graphics_manager.log.AnsiColor;
import graphics_manager.log.Colorize;

public class Ejercicio3 extends Ejercicio {
    
    private Window w;
    
    public Ejercicio3(DisplayManager dp, Window w) {
        super(dp, w);
        this.w = w;
        w.setLocation(dp.getBottomLeftCorner().toPoint());
        w.setLayout(new BorderLayout());
        w.setVisible(true);
        this.main();
    }
    
    public void main() {
        // Create title label
        JLabel titleLabel = new JLabel("Introduzca su nombre", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 10, 20));
        
        // Create input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(200, 30));
        
        JButton submitButton = new JButton("Mostrar nombre");
        submitButton.setFont(new Font("Arial", Font.BOLD, 12));
        submitButton.setBackground(new Color(33, 150, 243)); // Blue
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setPreferredSize(new Dimension(140, 30));
        
        submitButton.addActionListener(e -> {
            String name = textField.getText().trim();
            if (!name.isEmpty()) {
                System.out.println(Colorize.colorize("Hello, " + name + "!", AnsiColor.BLUE));
            } else {
                System.out.println(Colorize.colorize("Please enter a name first!", AnsiColor.RED));
            }
        });
        
        // Add components to input panel
        inputPanel.add(textField);
        inputPanel.add(submitButton);
        
        // Add components to window
        w.add(titleLabel, BorderLayout.NORTH);
        w.add(inputPanel, BorderLayout.CENTER);
        
        // Set window background
        if (w instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) w).getContentPane().setBackground(new Color(250, 250, 250));
        }
    }
}
