package ejercicio_1;

import java.awt.Window;
import javax.swing.JFrame;
import ejercicio.Ejercicio;
import graphics_manager.DisplayManager;
import graphics_manager.models.Vector2;

import java.awt.Point;
public class Ejercicio1 extends Ejercicio{

  
    public Ejercicio1(DisplayManager dp, Window w){
        super(dp, w);
        w.setLocation(dp.getCenter().getX(), dp.getCenter().getY());
        w.setVisible(true);
    }

    public void main(){

    }
    
}