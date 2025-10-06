package ejercicio_5;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;

import ejercicio.Ejercicio;
import graphics_manager.DisplayManager;

public class Ejercicio_5 extends Ejercicio {

    private Window w;

    public Ejercicio_5(DisplayManager dp, Window w) {
        super(dp, w);
        this.w = w;
        w.setLocation(dp.getCenter().toPoint());
        w.setLayout(new BorderLayout());
        w.setVisible(true);
        this.main();
    }

    @Override
    public void main() {
        JButton btn = new JButton("Pedir Edad");
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(33, 150, 243)); // Blue
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(150, 40));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btn.addActionListener(e -> {
            String ageInput = JOptionPane.showInputDialog(
                w,
                "Por favor, introduce tu edad:",
                "Solicitud de Edad",
                JOptionPane.QUESTION_MESSAGE
            );

            JOptionPane.showMessageDialog(btn, ageInput);

        });

        w.add(btn, BorderLayout.CENTER);
    }
}
