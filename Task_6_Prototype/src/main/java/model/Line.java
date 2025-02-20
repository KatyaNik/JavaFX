package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class Line extends Shape {


    public Line() {
        super();
        this.color=getColor();
    }


    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        this.setCircuit(gc);
        Random random = new Random();

        gc.strokeLine(x,y,random.nextInt(20, 100) ,random.nextInt(20, 100));
    }

}
