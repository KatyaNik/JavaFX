package model;

import javafx.scene.layout.Pane;

public class Line extends ShapeDecorator{
    public Line(IShape decoratedShape, Pane pane, double startX, double startY, double endX, double endY) {
        super(decoratedShape);
        // Создание линии
        javafx.scene.shape.Line line = new javafx.scene.shape.Line(startX, startY, endX, endY);
        pane.getChildren().add(line);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
