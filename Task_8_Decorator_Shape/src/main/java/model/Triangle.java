package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Triangle extends ShapeDecorator{
    public Triangle(Canvas canvas, Color colorFill, Color colorStroke, IShape decoratedShape, double... points) {
        super(decoratedShape);
        new BaseShape( colorFill, colorStroke, canvas, points);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
