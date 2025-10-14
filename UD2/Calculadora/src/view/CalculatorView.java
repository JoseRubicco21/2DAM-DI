package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent; // ADD THIS IMPORT
import java.util.HashMap;
import java.util.Map;
import components.CalculadoraComponentButton;
import controller.CalculadoraController;

public class CalculatorView extends JFrame {
    
    private JPanel historyPane;
    private JPanel displayPane;
    private JTextField displayField;
    private JPanel controlsPane;
    private Map<String, CalculadoraComponentButton> buttons;
    private CalculadoraController controller;

    // Color constants
    private static final Color ACCENT_BLUE = new Color(0xB65fCF);
    private static final Color BACKGROUND_GRAY = new Color(0x2d2d2d);
    private static final Color WHITE_TEXT = Color.WHITE;

    private DefaultListModel<String> historyListModel;
    private JList<String> historyList;
    
    // Add flag to track if we just completed an operation
    private boolean justCompletedOperation = false;

    public CalculatorView() {
        controller = new CalculadoraController();
        buttons = new HashMap<>();
        setupUI();
    }
    
    public JTextField getDisplayField() {
        return displayField;
    }

    public void setDisplayField(JTextField displayField) {
        this.displayField = displayField;
    }

    private void setupUI() {
        createButtons();
        styleButtons();
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Add panels in correct order
        addHistoryPanel();
        addDisplayPanel(); 
        addButtonPanel();
        
        // Set dark background for the frame
        getContentPane().setBackground(new Color(0x1a1a1a));
        linkButtonsToActions();
        
        // Set up keyboard listeners
        setupKeyboardListeners();
        
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
        historyTitle.setFont(new Font("Arial", Font.BOLD, 15));
        
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        titlePanel.setBackground(new Color(0x1a1a1a));
        titlePanel.add(historyIcon);
        titlePanel.add(historyTitle);
        
        // Clear button with functionality
        JButton clearButton = new JButton("Clear");
        clearButton.setForeground(ACCENT_BLUE);
        clearButton.setBackground(new Color(0x1a1a1a));
        clearButton.setFont(new Font("Arial", Font.BOLD, 13));
        clearButton.setBorderPainted(false);
        clearButton.setFocusPainted(false);
        clearButton.setContentAreaFilled(false);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add action to clear button
        clearButton.addActionListener(e -> {
            controller.clearHistory();
            historyListModel.clear();
        });
        
        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(clearButton, BorderLayout.EAST);
        
        // Create history list model and list as instance variables
        historyListModel = new DefaultListModel<>();
        historyList = new JList<>(historyListModel);
        historyList.setBackground(new Color(0x1a1a1a));
        historyList.setForeground(Color.WHITE);
        historyList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        historyList.setSelectionBackground(new Color(0x2d2d2d));
        historyList.setSelectionForeground(Color.WHITE);
        historyList.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        historyList.setFixedCellHeight(35);
        
        // Add click listener to reuse history entries
        historyList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && historyList.getSelectedValue() != null) {
                String selectedEntry = historyList.getSelectedValue();
                // Extract just the result part
                String[] parts = selectedEntry.split(" = ");
                if (parts.length > 1) {
                    displayField.setText(parts[1]);
                    justCompletedOperation = false; // Reset flag when selecting from history
                }
            }
        });
        
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
                expression.setFont(new Font("Monospaced", Font.PLAIN, 13));
                
                JLabel result = new JLabel(parts.length > 1 ? parts[1] : "");
                result.setForeground(Color.WHITE);
                result.setFont(new Font("Monospaced", Font.BOLD, 15));
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
        
        // Create a wrapper panel to hold both history and display
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(historyContainer, BorderLayout.NORTH);
        
        add(topPanel, BorderLayout.NORTH);
    }

    private void styleScrollBar(JScrollPane scrollPane) {
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

    // FIXED addDisplayPanel method
    private void addDisplayPanel(){
        displayPane = new JPanel();
        displayPane.setBackground(new Color(0x1a1a1a));
        displayPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        displayPane.setLayout(new BorderLayout());
        
        // Assign to instance variable - Start with empty display
        displayField = new JTextField();
        displayField.setText("");
        displayField.setEditable(false);
        displayField.setBackground(new Color(0x1a1a1a));
        displayField.setForeground(WHITE_TEXT);
        displayField.setFont(new Font("Arial", Font.PLAIN, 32));
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        displayPane.add(displayField, BorderLayout.CENTER);
        
        // FIXED: Safer approach to add to top panel
        Component northComponent = ((BorderLayout) getContentPane().getLayout()).getLayoutComponent(BorderLayout.NORTH);
        if (northComponent instanceof JPanel) {
            ((JPanel) northComponent).add(displayPane, BorderLayout.SOUTH);
        } else {
            // Fallback: create a new container
            JPanel container = new JPanel(new BorderLayout());
            if (northComponent != null) {
                container.add(northComponent, BorderLayout.NORTH);
            }
            container.add(displayPane, BorderLayout.SOUTH);
            add(container, BorderLayout.NORTH);
        }
    }
    
    private void addButtonPanel() {
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
            
            if (label.equals("=")) {
                styleEqualsButton(button);
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
        button.setBackground(ACCENT_BLUE);
        button.setForeground(WHITE_TEXT);
        button.setFont(new Font("Arial", Font.BOLD, 18));
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

    private void appendTextToDisplayField(String s){
        // If we just completed an operation, start fresh with new input
        if (justCompletedOperation) {
            displayField.setText(s);
            justCompletedOperation = false;
        } else if (displayField.getText().isEmpty()) {
            displayField.setText(s); // Start fresh if display is empty
        } else {
            displayField.setText(displayField.getText() + s);
        }
    }
    
    private void clearDisplayField() {
        displayField.setText(""); // Changed from "0" to empty string
        justCompletedOperation = false; // Reset flag when clearing
    }
    
    // MODIFIED: After operation, clear display and set flag
    private void linkButtonsToActions(){
        buttons.entrySet().forEach(entry -> {
            String k = entry.getKey();
            entry.getValue().addActionListener(l -> {
                if (k.equals("C")) {
                    clearDisplayField();
                } else if (k.equals("=")) {
                    String expression = displayField.getText();
                    if (!expression.isEmpty()) { // Only calculate if there's something in display
                        try {
                            double result = controller.parseOperation(expression);
                            // Truncate to integer (remove decimal part)
                            int intResult = (int) result;
                            
                            // Add to history display
                            addToHistoryDisplay(expression, intResult);
                            
                            // Clear the display after operation
                            displayField.setText("");
                            justCompletedOperation = true; // Set flag to indicate operation completed
                            
                        } catch (Exception e) {
                            displayField.setText("Error");
                            justCompletedOperation = true; // Set flag even for errors
                        }
                    }
                } else {
                    appendTextToDisplayField(k);
                }
            });
        });
    }
    
    // Add method to update history display
    private void addToHistoryDisplay(String expression, int result) {
        String historyEntry = expression + " = " + result;
        historyListModel.insertElementAt(historyEntry, 0); // Add to top
        
        // Limit history display to 10 entries
        if (historyListModel.size() > 10) {
            historyListModel.removeElementAt(historyListModel.size() - 1);
        }
    }

    // Optional: Add method to load existing history on startup
    private void loadExistingHistory() {
        java.util.List<model.HistoryEntry> history = controller.getHistory();
        for (model.HistoryEntry entry : history) {
            String historyEntry = entry.getExpression() + " = " + (int)entry.getResult();
            historyListModel.insertElementAt(historyEntry, 0);
        }
    }

    // Add this method to your CalculatorView class
    private void setupKeyboardListeners() {
        JRootPane rootPane = getRootPane();
        
        // Number keys (0-9)
        for (int i = 0; i <= 9; i++) {
            final String digit = String.valueOf(i);
            
            // Regular number keys - use VK codes for better compatibility
            rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke("pressed " + digit), "digit_" + digit);
            
            // Numpad keys
            rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke("pressed NUMPAD" + digit), "digit_" + digit);
            
            rootPane.getActionMap().put("digit_" + digit, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    highlightButton(digit);
                    appendTextToDisplayField(digit);
                }
            });
        }
        
        // FIXED: Operator keys with correct KeyStroke syntax
        // Plus key
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed PLUS"), "op_plus");
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed ADD"), "op_plus");
        rootPane.getActionMap().put("op_plus", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightButton("+");
                appendTextToDisplayField("+");
            }
        });
        
        // Minus key
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed MINUS"), "op_minus");
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed SUBTRACT"), "op_minus");
        rootPane.getActionMap().put("op_minus", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightButton("-");
                appendTextToDisplayField("-");
            }
        });
        
        // Multiply key
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed ASTERISK"), "op_multiply");
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed MULTIPLY"), "op_multiply");
        rootPane.getActionMap().put("op_multiply", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightButton("*");
                appendTextToDisplayField("*");
            }
        });
        
        // Divide key
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed SLASH"), "op_divide");
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed DIVIDE"), "op_divide");
        rootPane.getActionMap().put("op_divide", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightButton("/");
                appendTextToDisplayField("/");
            }
        });
        
        // Parentheses
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed LEFT_PARENTHESIS"), "op_leftparen");
        rootPane.getActionMap().put("op_leftparen", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightButton("(");
                appendTextToDisplayField("(");
            }
        });
        
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed RIGHT_PARENTHESIS"), "op_rightparen");
        rootPane.getActionMap().put("op_rightparen", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightButton(")");
                appendTextToDisplayField(")");
            }
        });
        
        // Enter key for equals
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed ENTER"), "equals");
        rootPane.getActionMap().put("equals", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightButton("=");
                String expression = displayField.getText();
                if (!expression.isEmpty()) {
                    try {
                        double result = controller.parseOperation(expression);
                        int intResult = (int) result;
                        addToHistoryDisplay(expression, intResult);
                        displayField.setText("");
                        justCompletedOperation = true;
                    } catch (Exception ex) {
                        displayField.setText("Error");
                        justCompletedOperation = true;
                    }
                }
            }
        });
        
        // Clear keys
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed ESCAPE"), "clear");
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed DELETE"), "clear");
        rootPane.getActionMap().put("clear", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightButton("C");
                clearDisplayField();
            }
        });
        
        // Backspace
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("pressed BACK_SPACE"), "backspace");
        rootPane.getActionMap().put("backspace", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = displayField.getText();
                if (currentText.length() > 1) {
                    displayField.setText(currentText.substring(0, currentText.length() - 1));
                } else {
                    displayField.setText("");
                }
                justCompletedOperation = false;
            }
        });
    }

    private void highlightButton(String buttonLabel) {
        if (buttons.containsKey(buttonLabel)) {
            CalculadoraComponentButton button = buttons.get(buttonLabel);
            
            // Temporarily change button appearance
            Color originalBg = button.getBackground();
            button.setBackground(button.getBackground().brighter());
            button.repaint();
            
            // Restore original appearance after delay
            Timer restoreTimer = new Timer(150, e -> {
                button.setBackground(originalBg);
                button.repaint();
            });
            restoreTimer.setRepeats(false);
            restoreTimer.start();
        }
    }
}
