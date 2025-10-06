package ejercicio_2;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import ejercicio.Ejercicio;

import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import graphics_manager.DisplayManager;
import graphics_manager.log.AnsiColor;

public class Ejercicio2 extends Ejercicio{
    private Window w;
    
    public Ejercicio2(DisplayManager dp, Window w){
        super(dp, w);
        this.w = w;
        w.setLocation(dp.getBottomLeftCorner().toPoint());
        w.setVisible(true);
        this.main();
    }

    public void main(){
        // Set layout for the window
        w.setLayout(new BorderLayout());
        
        // Create a title label
        JLabel titleLabel = new JLabel("¿Confirmar acción?", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create button panel with FlowLayout for horizontal alignment
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        // Accept button with styling
        JButton AcceptBtn = new JButton("Aceptar");
        AcceptBtn.setPreferredSize(new Dimension(100, 40));
        AcceptBtn.setBackground(new Color(76, 175, 80)); // Green
        AcceptBtn.setForeground(Color.WHITE);
        AcceptBtn.setFocusPainted(false);
        AcceptBtn.setFont(new Font("Arial", Font.BOLD, 12));
        AcceptBtn.addActionListener(e -> System.out.println(
            AnsiColor.GREEN + 
            "✓ Aceptar" + 
            AnsiColor.RESET
        )); 
        
        // Cancel button with styling
        JButton CancelarBtn = new JButton("Cancelar");
        CancelarBtn.setPreferredSize(new Dimension(100, 40));
        CancelarBtn.setBackground(new Color(244, 67, 54)); // Red
        CancelarBtn.setForeground(Color.WHITE);
        CancelarBtn.setFocusPainted(false);
        CancelarBtn.setFont(new Font("Arial", Font.BOLD, 12));
        CancelarBtn.addActionListener(e -> System.out.println(
            AnsiColor.RED + 
            "✗ Cancelar" +
            AnsiColor.RESET
        ));
        
        // Add buttons to panel
        buttonPanel.add(AcceptBtn);
        buttonPanel.add(CancelarBtn);
        
        // Add components to window
        w.add(titleLabel, BorderLayout.NORTH);
        w.add(buttonPanel, BorderLayout.CENTER);
        
        // Set window background
        if (w instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) w).getContentPane().setBackground(new Color(250, 250, 250));
        }
    }
}
