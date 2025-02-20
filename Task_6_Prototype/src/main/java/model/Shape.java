package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

abstract public class Shape implements Cloneable {

    protected double x;
    protected double y;
    protected Color color;

    public Shape() {
        this.x = 0;
        this.y = 0;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setCircuit(GraphicsContext gc) {

        gc.setStroke(color);


        Random rand = new Random();
        int i = rand.nextInt(0, 10);

        if(i < 5)
            gc.setStroke(color);
        else
            gc.setStroke(color);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract void draw(GraphicsContext gc, double x, double y);

    public void setPosition(double newX, double newY) {
        this.x=newX;
        this.y=newY;
    }

    @Override
    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();   }

        return clone;
    }
}
