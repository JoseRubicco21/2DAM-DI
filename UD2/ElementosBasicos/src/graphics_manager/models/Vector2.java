package graphics_manager.models;

import graphics_manager.exceptions.InvalidAxisException;

public class Vector2 {
    private int X;
    private int Y;
    
    public Vector2(int x, int y) {
        this.X = x;
        this.Y = y;
    }
    public int getX() {
        return X;
    }
    public void setX(int x) {
        X = x;
    }
    public int getY() {
        return Y;
    }
    public void setY(int y) {
        Y = y;
    }

    public Vector2 add(Vector2 other){
        this.setX(this.X + other.getX());
        this.setY(this.Y + other.getY());
        return this;
    }

    public Vector2 add(Axis axis, int value) throws InvalidAxisException {
        if(axis == Axis.X) return this.add(new Vector2(value, 0));
        if(axis == Axis.Y) return this.add(new Vector2(0, value));
        throw new InvalidAxisException("Invalid Axis. Axis must be X or Y.");
    }


    public Vector2 substract(Vector2 other){
        this.setX(this.X + other.getX());
        this.setY(this.Y + other.getY());
        return this;
    }

    public Vector2 substract(Axis axis, int value) throws InvalidAxisException {
        if(axis == Axis.X) return this.substract(new Vector2(value, 0));
        if(axis == Axis.Y) return this.substract(new Vector2(0, value));
        throw new InvalidAxisException("Invalid Axis. Axis must be X or Y.");
    }   

    public Vector2 multiply(Vector2 other){
        this.setX(this.X * other.getX());
        this.setY(this.Y * other.getY());
        return this;
    }

    public Vector2 multiply(Axis axis, int value) throws InvalidAxisException {
        if(axis == Axis.X) return this.multiply(new Vector2(value, 1));
        if(axis == Axis.Y) return this.multiply(new Vector2(1, value));
        throw new InvalidAxisException("Invalid Axis. Axis must be X or Y.");
    }


    public Vector2 divide(Vector2 other){
        this.setX(this.X / other.getX());
        this.setY(this.X / other.getY());
        return this;
    }


    @Override
    public String toString() {
        return String.format("X:%d | Y:%d", this.X, this.Y);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + X;
        result = prime * result + Y;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector2 other = (Vector2) obj;
        if (X != other.X)
            return false;
        if (Y != other.Y)
            return false;
        return true;
    }
    
    
    
}
