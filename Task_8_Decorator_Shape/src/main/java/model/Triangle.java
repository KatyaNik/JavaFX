package model;

import javafx.scene.layout.Pane;

public class Triangle extends ShapeDecorator{
    public Triangle(IShape decoratedShape, Pane pane) {
        super(decoratedShape);
        double[] points = {50, 0, 100, 100, 0, 100};
        new BaseShape(pane, points);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
