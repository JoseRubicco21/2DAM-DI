package ejercicio;


import java.awt.Window;

import graphics_manager.DisplayManager;
import graphics_manager.models.Vector2;

public abstract class Ejercicio {

    protected Vector2 dimensions;
    protected Vector2 displayCordinates;
    protected DisplayManager displayManager; 
    protected Window window;

    public Ejercicio (DisplayManager dp, Window w){
        this.displayManager = dp;
        this.window = w;
        this.dimensions = new Vector2(window.getX() | 0, window.getY() | 0);
        this.displayCordinates = dp.getCenter();
    }

    public abstract void main();

    
}