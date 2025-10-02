package ejercicio_1;
import java.awt.Window;
import javax.swing.JButton;
import ejercicio.Ejercicio;
import graphics_manager.DisplayManager;
import graphics_manager.log.AnsiColor;

public class Ejercicio1 extends Ejercicio{

    private Window w;
    
    public Ejercicio1(DisplayManager dp, Window w){
        super(dp, w);
        this.w = w;
        w.setLocation(dp.getBottomLeftCorner().toPoint());
        w.setVisible(true);
        this.main();
    }

    public void main(){
        JButton btn = new JButton();
        btn.setText("Print message");
        btn.addActionListener(e -> System.out.println(
            AnsiColor.PURPLE + 
            "I'm going fucking insane please help." + 
            AnsiColor.RESET)); 
        w.add(btn);
    }
    
}