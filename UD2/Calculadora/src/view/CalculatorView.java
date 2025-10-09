package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import components.CalculadoraComponentButton;
import controller.CalculadoraController;

public class CalculatorView extends JFrame {
    
    private JPanel historyPane;
    private JPanel displayPane;
    private JPanel controlsPane;
    private Map<String, CalculadoraComponentButton> buttons;
    private CalculadoraController controller;


    public CalculatorView() {
        controller = new CalculadoraController();
        setupUI();
    }
    
    private void setupUI() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
    
        
        // Set dark background for the frame
        getContentPane().setBackground(new Color(0x1a1a1a));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void setupButtons(){
    }
    
    private void createButton(String text) {
        CalculadoraComponentButton button = new CalculadoraComponentButton(text);
        controlsPane.add(button);
        buttons.add(button);
    }

    private void createButtons(){
        
        
    }


}
