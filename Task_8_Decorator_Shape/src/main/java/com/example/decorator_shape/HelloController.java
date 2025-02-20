package com.example.decorator_shape;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.BaseShape;
import model.IShape;
import model.Triangle;
import model.Line;
import model.Rectangle;

public class HelloController {
    @FXML
    private Pane drawingArea;

    // Метод для создания треугольника
    @FXML
    protected void drawTriangle() {
        IShape baseShape = new BaseShape(drawingArea, 50, 0, 100, 100, 0, 100);
        IShape triangle = new Triangle(baseShape, drawingArea);
        triangle.draw();
    }

    // Метод для создания прямоугольника
    @FXML
    protected void drawRectangle() {
        IShape baseShape = new BaseShape(drawingArea, 0, 0, 100, 0, 100, 50, 0, 50);
        IShape rectangle = new Rectangle(baseShape, drawingArea);
        rectangle.draw();
    }

    // Метод для создания линии
    @FXML
    protected void drawLine() {
        IShape baseShape = new BaseShape(drawingArea, 0, 0, 200, 200);
        IShape line = new Line(baseShape, drawingArea, 0, 0, 200, 200);
        line.draw();
    }
}