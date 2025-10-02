
import java.awt.DisplayMode;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFrame;

import ejercicio.Ejercicio;
import ejercicio_1.Ejercicio1;
import graphics_manager.DisplayManager;
import graphics_manager.models.Vector2;

public class App {
    public static void main(String[] args) throws Exception {
     
        JFrame MainFrame = new JFrame();
        DisplayManager dp = new DisplayManager(MainFrame);
        MainFrame.setSize(500, 500);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setVisible(true);

        JButton ex1Button = new JButton("Ejercicio 1");
        ex1Button.setBounds(50, 50, 150, 50);
        MainFrame.add(ex1Button);
        ex1Button.addActionListener(e -> {
            launchExercise(Ejercicio1.class, dp);
        });

    }

    private static void launchExercise(Class<? extends Ejercicio> exClass, DisplayManager dp) {
        JFrame w = new JFrame("Ejercicio");
        w.setSize(300, 300);
        try {
            exClass.getConstructor(DisplayManager.class, Window.class).newInstance(dp, w).main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
