package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Rectangle  extends ShapeDecorator {
    public Rectangle(Canvas canvas, Color colorStroke, Color colorFill, IShape decoratedShape, Pane pane) {
        super(decoratedShape);
        // Создаем прямоугольник с помощью полигонов
        double[] points = {0, 0, 100, 0, 100, 50, 0, 50};
        new BaseShape(colorStroke, colorFill, canvas, points);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
