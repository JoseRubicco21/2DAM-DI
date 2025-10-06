package ejercicio_10;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;

import ejercicio.Ejercicio;
import graphics_manager.DisplayManager;
import graphics_manager.log.AnsiColor;

public class Ejercicio_10 extends Ejercicio {
    private Window w;

    public Ejercicio_10(DisplayManager dp, Window w) {
        super(dp, w);
        this.w = w;
        w.setLocation(dp.getCenter().toPoint());
        w.setLayout(new BorderLayout());
        w.setVisible(true);
        this.main();
    }

    @Override
    public void main() {
        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(245, 245, 245));
        menuBar.setBorder(BorderFactory.createRaisedBevelBorder());
        
        // Create Archivo menu with styling
        JMenu menuArchivo = new JMenu("📁 Archivo");
        menuArchivo.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Create submenu items for Archivo
        JMenuItem abrir = new JMenuItem("📂 Abrir");
        abrir.setFont(new Font("Arial", Font.PLAIN, 12));
        abrir.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        abrir.addActionListener(e -> {
            System.out.println(AnsiColor.BLUE + "📂 Abriendo archivo..." + AnsiColor.RESET);
        });
        
        JMenuItem guardar = new JMenuItem("💾 Guardar");
        guardar.setFont(new Font("Arial", Font.PLAIN, 12));
        guardar.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        guardar.addActionListener(e -> {
            System.out.println(AnsiColor.GREEN + "💾 Guardando archivo..." + AnsiColor.RESET);
        });
    
        
        // Add separators for better organization
        menuArchivo.add(abrir);
        menuArchivo.addSeparator();
        menuArchivo.add(guardar);
        menuArchivo.addSeparator();

        
        // Create Edición menu with styling
        JMenu menuEdicion = new JMenu("✏️ Edición");
        menuEdicion.setFont(new Font("Arial", Font.BOLD, 14));
        
 
        // Add menus to menu bar
        menuBar.add(menuArchivo);
        menuBar.add(menuEdicion);
        
        // Set menu bar to window
        if (w instanceof JFrame) {
            ((JFrame) w).setJMenuBar(menuBar);
        }
        
        // Set window background
        if (w instanceof JFrame) {
            ((JFrame) w).getContentPane().setBackground(new Color(250, 250, 250));
        }
    }
}
