package ejercicio_8;

import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import ejercicio.Ejercicio;
import graphics_manager.DisplayManager;

public class Ejercicio_8 extends Ejercicio {

    private Window w;

    public Ejercicio_8(DisplayManager dp, Window w) {
        super(dp, w);
        this.w = w;
        w.setLocation(dp.getBottomRightCorner().toPoint());
        w.setLayout(new BorderLayout());
        w.setVisible(true);
        this.main();
    }

    @Override
    public void main() {
        // Create title label
        JLabel titleLabel = new JLabel("Selecciona Método de Pago", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create radio button panel
        JPanel radioPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        radioPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Create radio buttons
        JRadioButton paypalRadioButton = new JRadioButton("PayPal");
        JRadioButton tarjetaCreditoRadioButton = new JRadioButton("Tarjeta de Crédito");
        JRadioButton transferenciaBancariaRadioButton = new JRadioButton("Transferencia Bancaria");
        
        // Style radio buttons
        Font radioFont = new Font("Arial", Font.PLAIN, 14);
        paypalRadioButton.setFont(radioFont);
        tarjetaCreditoRadioButton.setFont(radioFont);
        transferenciaBancariaRadioButton.setFont(radioFont);
        
        // Create button group to ensure only one can be selected
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(paypalRadioButton);
        paymentGroup.add(tarjetaCreditoRadioButton);
        paymentGroup.add(transferenciaBancariaRadioButton);
        
        // Add radio buttons to panel
        radioPanel.add(paypalRadioButton);
        radioPanel.add(tarjetaCreditoRadioButton);
        radioPanel.add(transferenciaBancariaRadioButton);
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        
        JButton submitButton = new JButton("Confirmar Pago");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(76, 175, 80)); // Green
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setPreferredSize(new Dimension(150, 35));
        
        submitButton.addActionListener(e -> {
            if (paypalRadioButton.isSelected()) {
                System.out.println("Método de pago seleccionado: PayPal");
            } else if (tarjetaCreditoRadioButton.isSelected()) {
                System.out.println("Método de pago seleccionado: Tarjeta de Crédito");
            } else if (transferenciaBancariaRadioButton.isSelected()) {
                System.out.println("Método de pago seleccionado: Transferencia Bancaria");
            } else {
                System.out.println("No se ha seleccionado ningún método de pago.");
            }
        });
        
        buttonPanel.add(submitButton);
        
        // Add components to window
        w.add(titleLabel, BorderLayout.NORTH);
        w.add(radioPanel, BorderLayout.CENTER);
        w.add(buttonPanel, BorderLayout.SOUTH);
        
        // Set window background
        if (w instanceof JFrame) {
            ((JFrame) w).getContentPane().setBackground(new Color(250, 250, 250));
        }
    }
}
