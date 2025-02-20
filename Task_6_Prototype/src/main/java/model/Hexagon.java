package model;

import javafx.scene.canvas.GraphicsContext;

public class Hexagon extends Shape {

    public Hexagon(){
        super();
        this.color=getColor();
    }
    public String toString() {
        return "Hexagon";
    }
    @Override
    public void draw(GraphicsContext gc, double x, double y) {

        this.setX(x);
        this.setY(y);

        setCircuit(gc);
        double radius = Math.min(x, y) * 0.8;
        gc.beginPath();
        for (int i = 0; i < 6; i++) {
            double angle = Math.PI / 2 + i * 2 * Math.PI / 6;
            double x1 = x + radius * Math.cos(angle);
            double y1 = y + radius * Math.sin(angle);
            if (i == 0) {
                gc.moveTo(x1, y1);
            } else {
                gc.lineTo(x1, y1);
            }
        }
        gc.closePath();
        gc.stroke();
    }
}
