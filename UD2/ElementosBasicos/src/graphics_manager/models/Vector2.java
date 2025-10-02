package graphics_manager.models;

import java.awt.Point;

public class Vector2 extends Point {

    public Vector2(int x, int y) {
        super(x, y);
    }
    
    public Vector2 add(Vector2 v){
        return new Vector2(this.x + v.x, this.y + v.y);
    }

    public Vector2 subtract(Vector2 v){
        return new Vector2(this.x - v.x, this.y - v.y);
    }

    public Vector2 multiply(int scalar){
        return new Vector2(this.x * scalar, this.y * scalar);
    }

    public Vector2 divide(int scalar){
        if(scalar == 0) throw new ArithmeticException("Division by zero");
        return new Vector2(this.x / scalar, this.y / scalar);
    }

    @Override
    public String toString() {
        return "Vector2 [x=" + x + ", y=" + y + "]";
    }

    // Add this to Vector2 class
    public Point toPoint() {
        return new Point((int) getX(), (int) getY());
    }

}
