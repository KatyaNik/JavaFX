package model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Triangle extends ShapeDecorator{
    public Triangle(Color colorFill, Color colorStroke, IShape decoratedShape, Pane pane) {
        super(decoratedShape);
        double[] points = {50, 0, 100, 100, 0, 100};
        new BaseShape(colorFill, colorStroke, pane, points);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
