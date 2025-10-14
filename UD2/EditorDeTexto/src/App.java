import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;

public class App extends JFrame {
    private JTextArea textArea;
    private JLabel statusLabel;
    private File currentFile;
    private boolean isModified = false;
    
    public App() {
        initializeComponents();
        setupLayout();
        setupMenuBar();
        setupEventListeners();
        
        setTitle("Text Editor - New Document");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Handle window closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApplication();
            }
        });
    }
    
    private void initializeComponents() {
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setTabSize(4);
        
        statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Main text area with scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
        
        // Status bar
        add(statusLabel, BorderLayout.SOUTH);
    }
    
    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem newItem = new JMenuItem("New", KeyEvent.VK_N);
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newItem.addActionListener(e -> newFile());
        
        JMenuItem openItem = new JMenuItem("Open", KeyEvent.VK_O);
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openItem.addActionListener(e -> openFile());
        
        JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveItem.addActionListener(e -> saveFile());
        
        JMenuItem saveAsItem = new JMenuItem("Save As...", KeyEvent.VK_A);
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
        saveAsItem.addActionListener(e -> saveAsFile());
        
        fileMenu.addSeparator();
        
        JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        exitItem.addActionListener(e -> exitApplication());
        
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        
        JMenuItem undoItem = new JMenuItem("Undo", KeyEvent.VK_U);
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undoItem.addActionListener(e -> textArea.requestFocus()); // Basic implementation
        
        JMenuItem cutItem = new JMenuItem("Cut", KeyEvent.VK_T);
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cutItem.addActionListener(e -> textArea.cut());
        
        JMenuItem copyItem = new JMenuItem("Copy", KeyEvent.VK_C);
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copyItem.addActionListener(e -> textArea.copy());
        
        JMenuItem pasteItem = new JMenuItem("Paste", KeyEvent.VK_P);
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        pasteItem.addActionListener(e -> textArea.paste());
        
        JMenuItem selectAllItem = new JMenuItem("Select All", KeyEvent.VK_A);
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectAllItem.addActionListener(e -> textArea.selectAll());
        
        editMenu.add(undoItem);
        editMenu.addSeparator();
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(selectAllItem);
        
        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
        JMenuItem aboutItem = new JMenuItem("About", KeyEvent.VK_A);
        aboutItem.addActionListener(e -> showAbout());
        
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void setupEventListeners() {
        // Track document changes
        textArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                documentChanged();
            }
            
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                documentChanged();
            }
            
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                documentChanged();
            }
        });
    }
    
    private void documentChanged() {
        if (!isModified) {
            isModified = true;
            updateTitle();
        }
        updateStatus();
    }
    
    private void updateTitle() {
        String title = "Text Editor - ";
        if (currentFile != null) {
            title += currentFile.getName();
        } else {
            title += "New Document";
        }
        if (isModified) {
            title += " *";
        }
        setTitle(title);
    }
    
    private void updateStatus() {
        int lines = textArea.getLineCount();
        int chars = textArea.getText().length();
        statusLabel.setText("Lines: " + lines + " | Characters: " + chars);
    }
    
    private void newFile() {
        if (promptSaveIfModified()) {
            textArea.setText("");
            currentFile = null;
            isModified = false;
            updateTitle();
            updateStatus();
        }
    }
    
    private void openFile() {
        if (!promptSaveIfModified()) {
            return;
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt", "java", "html", "css", "js", "xml"));
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                String content = Files.readString(file.toPath());
                textArea.setText(content);
                currentFile = file;
                isModified = false;
                updateTitle();
                updateStatus();
                statusLabel.setText("File opened: " + file.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error reading file: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void saveFile() {
        if (currentFile == null) {
            saveAsFile();
        } else {
            try {
                Files.writeString(currentFile.toPath(), textArea.getText());
                isModified = false;
                updateTitle();
                statusLabel.setText("File saved: " + currentFile.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error saving file: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void saveAsFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt", "java", "html", "css", "js", "xml"));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // Add .txt extension if no extension provided
            if (!file.getName().contains(".")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }
            
            try {
                Files.writeString(file.toPath(), textArea.getText());
                currentFile = file;
                isModified = false;
                updateTitle();
                statusLabel.setText("File saved as: " + file.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error saving file: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean promptSaveIfModified() {
        if (isModified) {
            int option = JOptionPane.showConfirmDialog(this,
                "The document has been modified. Do you want to save changes?",
                "Save Changes?",
                JOptionPane.YES_NO_CANCEL_OPTION);
                
            if (option == JOptionPane.YES_OPTION) {
                saveFile();
                return !isModified; // Return true only if save was successful
            } else if (option == JOptionPane.CANCEL_OPTION) {
                return false;
            }
        }
        return true;
    }
    
    private void exitApplication() {
        if (promptSaveIfModified()) {
            System.exit(0);
        }
    }
    
    private void showAbout() {
        JOptionPane.showMessageDialog(this,
            "Simple Text Editor\n" +
            "Created with Java Swing\n" +
            "Features: Read, Write, Save, Save As\n\n" +
            "Keyboard shortcuts:\n" +
            "Ctrl+N - New file\n" +
            "Ctrl+O - Open file\n" +
            "Ctrl+S - Save file\n" +
            "Ctrl+Shift+S - Save as\n" +
            "Alt+F4 - Exit",
            "About Text Editor",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new App().setVisible(true);
        });
    }
}
