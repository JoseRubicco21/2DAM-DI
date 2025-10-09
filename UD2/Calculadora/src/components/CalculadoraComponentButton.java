package components;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.MouseAdapter;

public class CalculadoraComponentButton extends JButton{
    
 private static final Color BACKGROUND_COLOR = new Color(0x2d2d2d);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color HOVER_COLOR = new Color(0x3d3d3d);
    private static final Color PRESSED_COLOR = new Color(0x1d1d1d);
    private static final int BORDER_RADIUS = 15;
    
    private boolean isHovered = false;
    private boolean isPressed = false;
    
    public CalculadoraComponentButton(String text) {
        super(text);
        setupButton();
    }
    
    private void setupButton() {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(TEXT_COLOR);
        setFont(new Font("Arial", Font.BOLD, 14));
        
        // Add mouse listeners for hover effects
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Enable antialiasing for smooth rounded corners
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Determine background color based on state
        Color bgColor = BACKGROUND_COLOR;
        if (isPressed) {
            bgColor = PRESSED_COLOR;
        } else if (isHovered) {
            bgColor = HOVER_COLOR;
        }
        
        // Create rounded rectangle
        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(
            0, 0, getWidth(), getHeight(), BORDER_RADIUS, BORDER_RADIUS
        );
        
        // Fill background
        g2d.setColor(bgColor);
        g2d.fill(roundedRect);
        
        // Draw text
        g2d.setColor(getForeground());
        g2d.setFont(getFont());
        
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() + textHeight) / 2 - 2;
        
        g2d.drawString(getText(), x, y);
        
        g2d.dispose();
    }
    
    @Override
    public Dimension getPreferredSize() {
        FontMetrics fm = getFontMetrics(getFont());
        int width = fm.stringWidth(getText()) + 30; // Add padding
        int height = fm.getHeight() + 15; // Add padding
        return new Dimension(width, height);
    }
}
