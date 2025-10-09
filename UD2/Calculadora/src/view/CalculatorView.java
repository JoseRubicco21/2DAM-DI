package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import components.CalculadoraComponentButton;
import controller.CalculadoraController;

public class CalculatorView extends JFrame {
    
    private JPanel historyPane;
    private JPanel displayPane;
    private JPanel controlsPane;
    private Map<String, CalculadoraComponentButton> buttons;
    private CalculadoraController controller;

    // Color constants
    private static final Color ACCENT_BLUE = new Color(0x1e90ff);
    private static final Color BACKGROUND_GRAY = new Color(0x2d2d2d);
    private static final Color WHITE_TEXT = Color.WHITE;

    public CalculatorView() {
        controller = new CalculadoraController();
        buttons = new HashMap<>();
        setupUI();
    }
    
    private void setupUI() {
        createButtons();
        styleButtons();
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Add the button panel
        addDisplayPanel();
        addButtonPanel();
        addHistoryPanel();
        // Set dark background for the frame
        getContentPane().setBackground(new Color(0x1a1a1a));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addHistoryPanel() {
        JPanel historyContainer = new JPanel(new BorderLayout());
        historyContainer.setBackground(new Color(0x1a1a1a));
        historyContainer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0x2d2d2d)),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        // Modern header with icon
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0x1a1a1a));
        
        JLabel historyIcon = new JLabel("⏱");
        historyIcon.setFont(new Font("Apple Color Emoji", Font.PLAIN, 16));
        
        JLabel historyTitle = new JLabel("Recent Calculations");
        historyTitle.setForeground(new Color(0xFFFFFF));
        historyTitle.setFont(new Font("SF Pro Display", Font.BOLD, 15));
        
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        titlePanel.setBackground(new Color(0x1a1a1a));
        titlePanel.add(historyIcon);
        titlePanel.add(historyTitle);
        
        // Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setForeground(ACCENT_BLUE);
        clearButton.setBackground(new Color(0x1a1a1a));
        clearButton.setFont(new Font("SF Pro Display", Font.BOLD, 13));
        clearButton.setBorderPainted(false);
        clearButton.setFocusPainted(false);
        clearButton.setContentAreaFilled(false);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(clearButton, BorderLayout.EAST);
        
        // Modern history list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("25 × 4 = 100");
        listModel.addElement("100 − 25 = 75");
        listModel.addElement("75 ÷ 3 = 25");
        
        JList<String> historyList = new JList<>(listModel);
        historyList.setBackground(new Color(0x1a1a1a));
        historyList.setForeground(Color.WHITE);
        historyList.setFont(new Font("SF Mono", Font.PLAIN, 14));
        historyList.setSelectionBackground(new Color(0x2d2d2d));
        historyList.setSelectionForeground(Color.WHITE);
        historyList.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        historyList.setFixedCellHeight(35);
        
        // Custom list cell renderer
        historyList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBackground(isSelected ? new Color(0x2d2d2d) : new Color(0x1a1a1a));
                panel.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
                
                String text = value.toString();
                String[] parts = text.split(" = ");
                
                JLabel expression = new JLabel(parts[0]);
                expression.setForeground(new Color(0x8E8E93));
                expression.setFont(new Font("SF Mono", Font.PLAIN, 13));
                
                JLabel result = new JLabel(parts.length > 1 ? parts[1] : "");
                result.setForeground(Color.WHITE);
                result.setFont(new Font("SF Mono", Font.BOLD, 15));
                result.setHorizontalAlignment(SwingConstants.RIGHT);
                
                panel.add(expression, BorderLayout.WEST);
                panel.add(result, BorderLayout.EAST);
                
                return panel;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(historyList);
        scrollPane.setBackground(new Color(0x1a1a1a));
        scrollPane.getViewport().setBackground(new Color(0x1a1a1a));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(300, 110));
        
        styleScrollBar(scrollPane);
        
        historyContainer.add(headerPanel, BorderLayout.NORTH);
        historyContainer.add(scrollPane, BorderLayout.CENTER);
        
        add(historyContainer, BorderLayout.NORTH);
    }

    private void addHistoryEntry(JPanel historyContent, String expression, String result) {
        JPanel entryPanel = new JPanel(new BorderLayout());
        entryPanel.setBackground(new Color(0x1a1a1a));
        entryPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        entryPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        
        // Expression label (left side)
        JLabel exprLabel = new JLabel(expression);
        exprLabel.setForeground(new Color(0x8E8E93)); // Subtle gray
        exprLabel.setFont(new Font("SF Mono", Font.PLAIN, 14));
        
        // Result label (right side)
        JLabel resultLabel = new JLabel(result);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("SF Mono", Font.BOLD, 16));
        resultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        entryPanel.add(exprLabel, BorderLayout.WEST);
        entryPanel.add(resultLabel, BorderLayout.EAST);
        
        // Add subtle separator
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0x2d2d2d));
        separator.setBackground(new Color(0x2d2d2d));
        
        historyContent.add(entryPanel);
        historyContent.add(Box.createRigidArea(new Dimension(0, 2)));
        historyContent.add(separator);
        historyContent.add(Box.createRigidArea(new Dimension(0, 2)));
    }

    private void styleScrollBar(JScrollPane scrollPane) {
        // Custom scrollbar styling
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x3d3d3d);
                this.trackColor = new Color(0x1a1a1a);
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            
            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
            
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(thumbColor);
                g2.fillRoundRect(thumbBounds.x + 2, thumbBounds.y + 2, 
                               thumbBounds.width - 4, thumbBounds.height - 4, 8, 8);
                g2.dispose();
            }
        });
        
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
    }

    private void addDisplayPanel(){
        displayPane = new JPanel();
        displayPane.setBackground(new Color(0x1a1a1a));
        displayPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        displayPane.setLayout(new BorderLayout());
        JTextField displayField = new JTextField();
        displayField.setText("0");
        displayField.setEditable(false);
        displayField.setBackground(new Color(0x1a1a1a));
        displayField.setForeground(WHITE_TEXT);
        displayField.setFont(new Font("Arial", Font.PLAIN, 24));
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        displayPane.add(displayField, BorderLayout.CENTER);
        add(displayPane, BorderLayout.NORTH);
    }

    
    private void addButtonPanel() {
        // Use GridBagLayout to make equals button span 2 columns
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(0x1a1a1a));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        // Row 0: C, (, ), ^
        addButtonToGrid(buttonPanel, "C", gbc, 0, 0, 1, 1);
        addButtonToGrid(buttonPanel, "(", gbc, 0, 1, 1, 1);
        addButtonToGrid(buttonPanel, ")", gbc, 0, 2, 1, 1);
        addButtonToGrid(buttonPanel, "^", gbc, 0, 3, 1, 1);
        
        // Row 1: 7, 8, 9, /
        addButtonToGrid(buttonPanel, "7", gbc, 1, 0, 1, 1);
        addButtonToGrid(buttonPanel, "8", gbc, 1, 1, 1, 1);
        addButtonToGrid(buttonPanel, "9", gbc, 1, 2, 1, 1);
        addButtonToGrid(buttonPanel, "/", gbc, 1, 3, 1, 1);
        
        // Row 2: 4, 5, 6, *
        addButtonToGrid(buttonPanel, "4", gbc, 2, 0, 1, 1);
        addButtonToGrid(buttonPanel, "5", gbc, 2, 1, 1, 1);
        addButtonToGrid(buttonPanel, "6", gbc, 2, 2, 1, 1);
        addButtonToGrid(buttonPanel, "*", gbc, 2, 3, 1, 1);
        
        // Row 3: 1, 2, 3, -
        addButtonToGrid(buttonPanel, "1", gbc, 3, 0, 1, 1);
        addButtonToGrid(buttonPanel, "2", gbc, 3, 1, 1, 1);
        addButtonToGrid(buttonPanel, "3", gbc, 3, 2, 1, 1);
        addButtonToGrid(buttonPanel, "-", gbc, 3, 3, 1, 1);
        
        // Row 4: 0, = (spans 2 columns), +
        addButtonToGrid(buttonPanel, "0", gbc, 4, 0, 1, 1);
        addButtonToGrid(buttonPanel, "=", gbc, 4, 1, 2, 1); // spans 2 columns
        addButtonToGrid(buttonPanel, "+", gbc, 4, 3, 1, 1);
        
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    private void addButtonToGrid(JPanel panel, String buttonLabel, GridBagConstraints gbc, 
                                int row, int col, int width, int height) {
        if (buttons.containsKey(buttonLabel)) {
            gbc.gridx = col;
            gbc.gridy = row;
            gbc.gridwidth = width;
            gbc.gridheight = height;
            panel.add(buttons.get(buttonLabel), gbc);
        }
    }
    
    private void styleButtons() {
        for (Map.Entry<String, CalculadoraComponentButton> entry : buttons.entrySet()) {
            String label = entry.getKey();
            CalculadoraComponentButton button = entry.getValue();
            
            System.out.println("Styling button: " + label); // Debug
            
            if (label.equals("=")) {
                System.out.println("Found equals button, styling it"); // Debug
                styleEqualsButton(button);
                
                // Force styling after creation
                button.setOpaque(true); // Make sure background is visible
                button.repaint();
                
            } else if (isOperatorButton(label)) {
                styleOperatorButton(button);
            } else if (label.equals("C")) {
                styleClearButton(button);
            } else {
                styleNumberButton(button);
            }
        }
    }
    
    private boolean isOperatorButton(String label) {
        return label.equals("/") || label.equals("*") || label.equals("-") || 
               label.equals("+") || label.equals("^") || label.equals("(") || 
               label.equals(")");
    }
    
    private void styleOperatorButton(CalculadoraComponentButton button) {
        button.setBackground(BACKGROUND_GRAY);
        button.setForeground(ACCENT_BLUE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
    }
    
    private void styleEqualsButton(CalculadoraComponentButton button) {
        System.out.println("Actually styling equals button"); // Debug
        button.setBackground(ACCENT_BLUE);
        button.setForeground(WHITE_TEXT);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setOpaque(true); // Force background to show
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }
    
    private void styleClearButton(CalculadoraComponentButton button) {
        button.setBackground(BACKGROUND_GRAY);
        button.setForeground(ACCENT_BLUE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
    }
    
    private void styleNumberButton(CalculadoraComponentButton button) {
        button.setBackground(BACKGROUND_GRAY);
        button.setForeground(WHITE_TEXT);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
    }
    
    private CalculadoraComponentButton createButton(String text) {
        CalculadoraComponentButton button = new CalculadoraComponentButton(text);
        button.setPreferredSize(new Dimension(60, 50));
        return button;
    }

    private void createButtons() {
        String[] buttonLabels = {
            "C", "(", ")", "^",
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-",
            "0", "=", "+"
        };
        
        for (String label : buttonLabels) {
            buttons.put(label, createButton(label));
        }
    }
}
