package com.example.decorator_shape;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.*;

public class HelloController {
    @FXML
    private Pane drawingArea;
    public ColorPicker colorPickerStroke;
    public ColorPicker colorPickerFill;

    // Метод для создания треугольника
    @FXML
    protected void drawTriangle() {
        IShape baseShape = new BaseShape(colorPickerStroke.getValue(), colorPickerFill.getValue(), drawingArea, 50, 0, 100, 100, 0, 100);
        IShape triangle = new Triangle(colorPickerStroke.getValue(), colorPickerFill.getValue(), baseShape, drawingArea);
        triangle.draw();
    }

    // Метод для создания прямоугольника
    @FXML
    protected void drawRectangle() {
        IShape baseShape = new BaseShape(colorPickerStroke.getValue(), colorPickerFill.getValue(),drawingArea, 0, 0, 100, 0, 100, 50, 0, 50);

        IShape rectangle = new Rectangle(colorPickerStroke.getValue(), colorPickerFill.getValue(),baseShape, drawingArea);
        rectangle.draw();
    }

    // Метод для создания линии
    @FXML
    protected void drawLine() {
        IShape baseShape = new BaseShape(colorPickerStroke.getValue(), colorPickerFill.getValue(),drawingArea, 0, 0, 200, 200);
        IShape line = new Line(baseShape, drawingArea, 0, 0, 200, 200);
        line.draw();
    }
}