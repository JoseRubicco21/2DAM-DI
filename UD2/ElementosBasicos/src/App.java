
import java.awt.DisplayMode;
import java.awt.Window;

import javax.swing.JFrame;

import ejercicio_1.Ejercicio1;
import graphics_manager.DisplayManager;
import graphics_manager.models.Vector2;

public class App {
    public static void main(String[] args) throws Exception {
        Vector2 dim = new Vector2(300, 300);
        JFrame w = new JFrame();
        w.setSize(dim.getX(), dim.getY());
        DisplayManager dp = new DisplayManager(w);
        Ejercicio1 ex = new Ejercicio1(dp, w);
        
    }
}
