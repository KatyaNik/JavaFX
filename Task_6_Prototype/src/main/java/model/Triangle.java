package model;

import javafx.scene.canvas.GraphicsContext;

public class Triangle extends Shape{

    public Triangle ()
    {
        super();
        this.color=getColor();
    }
    @Override
    public String toString() {
        return "Triangle";
    }
    public double area(){
        return 0;
    }

    public void draw (GraphicsContext gc, double x, double y)
    {
        setCircuit(gc);
        gc.strokeLine(10, 10, 190, 190);
        gc.strokeLine(10, 10, 190, 10);
        gc.strokeLine(190, 10, 190, 190);

    }
}
