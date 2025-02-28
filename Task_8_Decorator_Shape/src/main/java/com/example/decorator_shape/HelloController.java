package com.example.decorator_shape;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.*;

public class HelloController {
    @FXML
    private Pane drawingArea;



    @FXML
    protected void drawTriangle() {
        try {
            IShape baseShape = new BaseShape(Color.RED, Color.BLUE, drawingArea, 50, 0, 100, 100, 0, 100);
            IShape triangle = new Triangle(Color.RED, Color.BLUE, baseShape, drawingArea);
            triangle.draw();
        } catch (Exception e) {
            System.err.println("Произошла ошибка при рисовании треугольника:");
            e.printStackTrace();
        }
    }

    // Метод для создания прямоугольника
    @FXML
    protected void drawRectangle() {
        IShape baseShape = new BaseShape(Color.YELLOW, Color.BLUE,drawingArea, 0, 0, 100, 0, 100, 50, 0, 50);

        IShape rectangle = new Rectangle(Color.BLACK, Color.BLUE,baseShape, drawingArea);
        rectangle.draw();
    }

    // Метод для создания линии
    @FXML
    protected void drawLine() {
        IShape baseShape = new BaseShape(Color.BLACK, Color.BLUE,drawingArea, 0, 0, 200, 200);
        IShape line = new Line(baseShape, drawingArea, 0, 0, 200, 200);
        line.draw();
    }
}