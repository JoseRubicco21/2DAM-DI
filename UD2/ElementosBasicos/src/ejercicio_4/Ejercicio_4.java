package ejercicio_4;

import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import ejercicio.Ejercicio;
import graphics_manager.DisplayManager;
import graphics_manager.log.AnsiColor;
import graphics_manager.log.Colorize;

public class Ejercicio_4 extends Ejercicio {
    
    private Window w;

    public Ejercicio_4(DisplayManager dp, Window w) {
        super(dp, w);
        this.w = w;
        w.setLocation(dp.getCenter().toPoint());
        w.setLayout(new BorderLayout());
        w.setVisible(true);
        this.main();
    }

    public void main() {
        // Create title label
        JLabel titleLabel = new JLabel("Información Personal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create form panel with GridBagLayout for better form layout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Name label and field
        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(nameLabel, gbc);
        
        JTextField nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // This makes it expand horizontally
        formPanel.add(nameField, gbc);
        
        // Last name label and field
        JLabel lastNameLabel = new JLabel("Apellido:");
        lastNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0; // Reset weight for label
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(lastNameLabel, gbc);
        
        JTextField lastNameField = new JTextField(20);
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        lastNameField.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // This makes it expand horizontally
        formPanel.add(lastNameField, gbc);
        
        // Submit button
        JButton submitButton = new JButton("Enviar");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(76, 175, 80)); // Green
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setPreferredSize(new Dimension(120, 35));
        
        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            
            // Clear placeholder text if present
            if (name.equals("Escribe tu nombre")) name = "";
            if (lastName.equals("Escribe tu apellido")) lastName = "";
            
            if (!name.isEmpty() && !lastName.isEmpty()) {
                System.out.println(Colorize.colorize("Hola, " + name + " " + lastName + "!", AnsiColor.GREEN));
            } else {
                System.out.println(Colorize.colorize("Por favor, rellena ambos campos.", AnsiColor.RED));
            }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.0; // Reset weight for button
        formPanel.add(submitButton, gbc);
        
        // Add components to window
        w.add(titleLabel, BorderLayout.NORTH);
        w.add(formPanel, BorderLayout.CENTER);
        
        // Set window background
        if (w instanceof JFrame) {
            ((JFrame) w).getContentPane().setBackground(new Color(250, 250, 250));
        }
    }
}
