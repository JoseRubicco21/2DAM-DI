package graphics_manager;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

import graphics_manager.models.Vector2;

public class DisplayManager {
    
    private final int ZERO = 0;

    private  Vector2 displayResolution;
    private  Vector2 TopLeftCorner;
    private  Vector2 TopRightCorner;
    private  Vector2 BottomLeftCorner;
    private  Vector2 BottomRightCorner;
    private  Vector2 Center;
    private  Window window;
    
    public DisplayManager(Window parent){
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        this.displayResolution = new Vector2(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
        this.window = parent;
        this.calculateDisplayValues(parent);
    }
    


    private void calculateDisplayValues(Window window){
        this.setCenter(new Vector2(this.displayResolution.getX()/2, this.displayResolution.getY()/2));
        
        this.setTopLeftCorner(new Vector2(this.ZERO, this.ZERO));
        
        this.setBottomLeftCorner(new Vector2(this.ZERO, this.displayResolution.getY() - window.getHeight()));

        this.setTopRightCorner(new Vector2(this.displayResolution.getX() - window.getWidth(), this.ZERO));

        this.setBottomRightCorner(new Vector2(this.displayResolution.getX() - this.window.getWidth(), this.displayResolution.getY() - this.window.getHeight()));        
    }

    public Vector2 getDisplayResolution() {
        return displayResolution;
    }

    public void setDisplayResolution(Vector2 displayResolution) {
        this.displayResolution = displayResolution;
    }

    public Vector2 getCenter() {
        return Center;
    }

    public void setCenter(Vector2 center) {
        Center = center;
    }

    public Vector2 getTopLeftCorner() {
        return TopLeftCorner;
    }
    public void setTopLeftCorner(Vector2 topLeftCorner) {
        TopLeftCorner = topLeftCorner;
    }
    public Vector2 getTopRightCorner() {
        return TopRightCorner;
    }
    public void setTopRightCorner(Vector2 topRightCorner) {
        TopRightCorner = topRightCorner;
    }
    public Vector2 getBottomLeftCorner() {
        return BottomLeftCorner;
    }
    public void setBottomLeftCorner(Vector2 bottomLeftCorner) {
        BottomLeftCorner = bottomLeftCorner;
    }
    public Vector2 getBottomRightCorner() {
        return BottomRightCorner;
    }
    public void setBottomRightCorner(Vector2 bottomRightCorner) {
        BottomRightCorner = bottomRightCorner;
    }

    


}
