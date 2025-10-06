import java.awt.DisplayMode;
import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import ejercicio.Ejercicio;
import ejercicio_1.Ejercicio1;
import ejercicio_2.Ejercicio2;
import graphics_manager.DisplayManager;
import graphics_manager.models.Vector2;

public class App {
    public static void main(String[] args) throws Exception {
     
        JFrame MainFrame = new JFrame("Ejercicios - Selector");
        DisplayManager dp = new DisplayManager(MainFrame);
        MainFrame.setSize(400, 200);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dp.centerWindow();
        
        // Create title label
        JLabel titleLabel = new JLabel("🎯 Selector de Ejercicios", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        titleLabel.setForeground(new Color(51, 102, 153));
        
        // Create center panel for dropdown and button
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        
        // Create dropdown with exercise options
        String[] exercises = {
            "Selecciona un ejercicio...",
            "Ejercicio 1 - Botón Simple",
            "Ejercicio 2 - Confirmación",
            "Ejercicio 3 - Input Nombre",
            "Ejercicio 4 - Formulario",
            "Ejercicio 5 - Input Edad",
            "Ejercicio 6 - ComboBox Colores",
            "Ejercicio 7 - Login",
            "Ejercicio 8 - Radio Buttons",
            "Ejercicio 9 - CheckBoxes Pizza",
            "Ejercicio 10 - Menús"
        };
        
        JComboBox<String> exerciseCombo = new JComboBox<>(exercises);
        exerciseCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        exerciseCombo.setPreferredSize(new Dimension(250, 30));
        
        // Create launch button
        JButton launchButton = new JButton("🚀 Ejecutar");
        launchButton.setFont(new Font("Arial", Font.BOLD, 14));
        launchButton.setBackground(new Color(76, 175, 80)); // Green
        launchButton.setForeground(Color.WHITE);
        launchButton.setFocusPainted(false);
        launchButton.setPreferredSize(new Dimension(100, 30));
        launchButton.setEnabled(false); // Disabled initially
        
        // Enable/disable button based on selection
        exerciseCombo.addActionListener(e -> {
            launchButton.setEnabled(exerciseCombo.getSelectedIndex() > 0);
        });
        
        // Launch button action
        launchButton.addActionListener(e -> {
            int selectedIndex = exerciseCombo.getSelectedIndex();
            
            if (selectedIndex > 0) {
                Class<? extends Ejercicio> exerciseClass = getExerciseClass(selectedIndex);
                if (exerciseClass != null) {
                    launchExercise(exerciseClass, dp);
                }
            }
        });
        
        // Add components to center panel
        centerPanel.add(exerciseCombo);
        centerPanel.add(launchButton);
        
        // Add components to main frame
        MainFrame.add(titleLabel, BorderLayout.NORTH);
        MainFrame.add(centerPanel, BorderLayout.CENTER);
        
        // Set background color
        MainFrame.getContentPane().setBackground(new Color(250, 250, 250));
        
        MainFrame.setVisible(true);
    }
    
    private static Class<? extends Ejercicio> getExerciseClass(int index) {
        switch (index) {
            case 1: return Ejercicio1.class;
            case 2: return Ejercicio2.class;
            case 3: return ejercicio_3.Ejercicio3.class;
            case 4: return ejercicio_4.Ejercicio_4.class;
            case 5: return ejercicio_5.Ejercicio_5.class;
            case 6: return ejercicio_6.Ejercicio_6.class;
            case 7: return ejercicio_7.Ejercicio_7.class;
            case 8: return ejercicio_8.Ejercicio_8.class;
            case 9: return ejercicio_9.Ejercicio_9.class;
            case 10: return ejercicio_10.Ejercicio_10.class;
            default: return null;
        }
    }

    private static void launchExercise(Class<? extends Ejercicio> exClass, DisplayManager dp) {
        JFrame w = new JFrame("Ejercicio");
        w.setSize(300, 300);
        try {
            exClass.getConstructor(DisplayManager.class, Window.class).newInstance(dp, w);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
