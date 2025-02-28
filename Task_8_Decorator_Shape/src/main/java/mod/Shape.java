package mod;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements Cloneable{
    protected double x;
    protected double y;
    protected Color color;

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract void draw(GraphicsContext gc);

    public void setPosition(double newX, double newY) {
        this.x=newX;
        this.y=newY;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();   }
        return clone;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    abstract public void drawBorder(GraphicsContext gc);
    abstract public void drawText(GraphicsContext gc);
}
