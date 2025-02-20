package model;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends Shape {

    public double radius;
    public int numPoints;

    public Circle () {
        super();
        this.color=getColor();
        this.numPoints = 100;
        this.radius = 50;
    }
    @Override
    public String toString() {
        return " Circle";
    }
    public double area() {
        return 0;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    public void draw (GraphicsContext gc, double mouseX, double mouseY)
    {
        setCircuit(gc);
        double centerX = mouseX;
        double centerY = mouseY;
        //double centerX = super.x, centerY = super.y;

        double step = 2 * Math.PI / numPoints;
        for (int i = 0; i < numPoints; i++) {
            double angle = i * step;
            double x1 = centerX + radius * Math.cos(angle);
            double y1 = centerY + radius * Math.sin(angle);

            double nextAngle = (i + 1) * step;
            double x2 = centerX + radius * Math.cos(nextAngle);
            double y2 = centerY + radius * Math.sin(nextAngle);

            gc.strokeLine(x1, y1, x2, y2);
        }
        gc.closePath();
        gc.stroke();
    }

}