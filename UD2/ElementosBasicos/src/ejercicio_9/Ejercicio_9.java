package ejercicio_9;

import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import ejercicio.Ejercicio;
import graphics_manager.DisplayManager;

public class Ejercicio_9 extends Ejercicio{

    private Window w;

    public Ejercicio_9(DisplayManager dp, Window w) {
        super(dp, w);
        this.w = w;
        w.setLocation(dp.getTopLeftCorner().toPoint());
        w.setLayout(new BorderLayout());
        w.setVisible(true);
        this.main();
    }

    @Override
    public void main() {
        // Create title label
        JLabel titleLabel = new JLabel("🍕 Personaliza tu Pizza", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        titleLabel.setForeground(new Color(220, 20, 60)); // Crimson
        
        // Create checkbox panel
        JPanel checkboxPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        checkboxPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        // Create checkboxes with better styling
        JCheckBox cb1 = new JCheckBox("🧀 Queso Extra");
        JCheckBox cb2 = new JCheckBox("🍖 Pepperoni");
        JCheckBox cb3 = new JCheckBox("🫒 Aceitunas");

        
        // Style checkboxes
        Font checkboxFont = new Font("Arial", Font.PLAIN, 14);
        cb1.setFont(checkboxFont);
        cb2.setFont(checkboxFont);
        cb3.setFont(checkboxFont);
        
        // Set background color for checkboxes
        Color bgColor = new Color(250, 250, 250);
        cb1.setBackground(bgColor);
        cb2.setBackground(bgColor);
        cb3.setBackground(bgColor);
        
        // Add checkboxes to panel
        checkboxPanel.add(cb1);
        checkboxPanel.add(cb2);
        checkboxPanel.add(cb3);

        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        
        JButton btn = new JButton("🛒 Ordenar Pizza");
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(255, 140, 0)); // Dark orange
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(180, 40));
        btn.setBorder(BorderFactory.createRaisedBevelBorder());
        
        btn.addActionListener(e -> {
            List<String> toppings = new ArrayList<>();
            
            if(cb1.isSelected()) toppings.add("Queso Extra");
            if(cb2.isSelected()) toppings.add("Pepperoni");
            if(cb3.isSelected()) toppings.add("Aceitunas");
            
            if(toppings.isEmpty()) {
                System.out.println("🍕 Has ordenado una pizza básica (sin ingredientes extra)");
            } else {
                System.out.println("🍕 Has ordenado una pizza con: " + String.join(", ", toppings));
                System.out.println("💰 Total de ingredientes extras: " + toppings.size());
            }
        });
        
        buttonPanel.add(btn);
        
        // Add components to window
        w.add(titleLabel, BorderLayout.NORTH);
        w.add(checkboxPanel, BorderLayout.CENTER);
        w.add(buttonPanel, BorderLayout.SOUTH);
        
        // Set window background
        if (w instanceof JFrame) {
            ((JFrame) w).getContentPane().setBackground(bgColor);
        }
    }
}
