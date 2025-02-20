package model;

import javafx.scene.layout.Pane;

public class Rectangle  extends ShapeDecorator {
    public Rectangle(IShape decoratedShape, Pane pane) {
        super(decoratedShape);
        // Создаем прямоугольник с помощью полигонов
        double[] points = {0, 0, 100, 0, 100, 50, 0, 50};
        new BaseShape(pane, points);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
