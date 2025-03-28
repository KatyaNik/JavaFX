package com.example.decorator_shape;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import mod.Shape;
import model.*;

public class HelloController {
    @FXML
    public Canvas canvas; // Основной Canvas для рисования
    public Pane drawingArea;
    @FXML
    public CheckBox addStrokeCheckBox; // CheckBox для добавления контура
    public String currentShapeType = "NONE"; // Тип текущей фигуры
    public double[] currentShape; // Сохранённые координаты текущей фигуры
    @FXML
    public void initialize() {
        if (drawingArea != null) {
            // Создаем Canvas и добавляем его в Pane
            canvas = new Canvas(400, 400); // Размеры Canvas
            drawingArea.getChildren().add(canvas);
        }
    }
    // Метод для обработки события CheckBox
    @FXML
    public void BordrerCh(ActionEvent actionEvent) {

        // Если чекбокс выбран, добавляем контур
        if (addStrokeCheckBox.isSelected()) {
            checkCurrentShape(); // Рисуем фигуру с контуром
        } else {
            drawFigure( currentShapeType); // Рисуем фигуру без контура
        }


        /*if (addStrokeCheckBox.isSelected()) {
            checkCurrentShape(); // Добавляем контур, если чекбокс выбран
        }*/
    }
    public void drawFigure(String currentShapeType){
        switch (currentShapeType) {
            case "Прямоугольник":
                drawRectangle();
                break;

            case "Треугольник":
                drawTriangle();
                break;

            case "Линия":
                drawLine();
                break;

            default:
                break;
        }
    }
    // Метод для рисования треугольника
    @FXML
    protected void drawTriangle() {
        try {
            double[] coordinates = {50, 0, 100, 100, 0, 100};
            IShape baseShape = new BaseShape(Color.RED, Color.BLUE, canvas,50, 0, 100, 100, 0, 100);
            IShape triangle = new Triangle(canvas,Color.RED, Color.BLUE, baseShape, coordinates);
            triangle.draw();
            currentShape = coordinates.clone();
            currentShapeType = "Треугольник";
        } catch (Exception e) {
            System.err.println("Произошла ошибка при рисовании треугольника:");
            e.printStackTrace();
        }
    }

    // Метод для рисования прямоугольника
    @FXML
    protected void drawRectangle() {
        double[] coordinates = {0, 0, 100, 0, 100, 50, 0, 50};
        IShape baseShape = new BaseShape(Color.YELLOW, Color.BLUE, canvas,0, 0, 100, 0, 100, 50, 0, 50);
        IShape rectangle = new Rectangle(canvas,Color.GREEN, Color.GREEN, baseShape, drawingArea);
        rectangle.draw();

        // Сохраняем данные о фигуре
        currentShape = coordinates.clone();
        currentShapeType = "Прямоугольник";
    }

    // Метод для рисования линии
    @FXML
    protected void drawLine() {
        double[] coordinates = {0, 0, 200, 200};
        IShape baseShape = new BaseShape(Color.BLACK, Color.BLACK,  canvas, 0, 0, 200, 200);
        IShape line = new Line(baseShape,  drawingArea, coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
        line.draw();

        // Сохраняем данные о фигуре
        currentShape = coordinates.clone();
        currentShapeType = "Линия";
    }
    @FXML
    protected double[] getCoordinatesFromPane() {
        if (currentShape == null || currentShapeType.equals("NONE")) {
            System.out.println("Ничего не нарисовано.");
            return null;
        }

        System.out.println("Текущая фигура: " + currentShapeType);
        System.out.println("Координаты: " + java.util.Arrays.toString(currentShape));
        return currentShape;
    }
    // Метод для очистки Canvas
    @FXML
    protected void clearCanvas() {
        if (canvas == null) {
            System.err.println("Canvas не инициализирован!");
            return;
        }

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        currentShape = null; // Очищаем текущую фигуру
        currentShapeType = "NONE"; // Сбрасываем тип фигуры
    }

    // Метод для добавления контура
    @FXML
    protected void checkCurrentShape() {
        if (canvas == null) {
            System.err.println("Canvas не инициализирован!");
            return;
        }

        if (currentShape == null || currentShapeType.equals("NONE")) {
            System.out.println("Нет нарисованной фигуры для добавления контура.");
            return;
        }

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.RED); // Цвет контура
        gc.setLineWidth(4); // Толщина контура

        switch (currentShapeType) {
            case "Прямоугольник":
                // Разделяем координаты на массивы X и Y
                double[] rectXPoints = getXPoints(currentShape, 4);
                double[] rectYPoints = getYPoints(currentShape, 4);
                gc.strokePolygon(rectXPoints, rectYPoints, 4); // 4 точки для прямоугольника
                break;

            case "Треугольник":
                // Разделяем координаты на массивы X и Y
                double[] triXPoints = getXPoints(currentShape, 3);
                double[] triYPoints = getYPoints(currentShape, 3);
                gc.strokePolygon(triXPoints, triYPoints, 3); // 3 точки для треугольника
                break;

            case "Линия":
                // Рисуем контур линии
                gc.strokeLine(currentShape[0], currentShape[1], currentShape[2], currentShape[3]);
                break;

            default:
                System.out.println("Неизвестный тип фигуры.");
                break;
        }
    }

    // Вспомогательные методы для разделения координат
    private double[] getXPoints(double[] coordinates, int nPoints) {
        double[] xPoints = new double[nPoints];
        for (int i = 0; i < nPoints; i++) {
            xPoints[i] = coordinates[i * 2]; // Берем каждую четную координату (X)
        }
        return xPoints;
    }

    private double[] getYPoints(double[] coordinates, int nPoints) {
        double[] yPoints = new double[nPoints];
        for (int i = 0; i < nPoints; i++) {
            yPoints[i] = coordinates[i * 2 + 1]; // Берем каждую нечетную координату (Y)
        }
        return yPoints;
    }
}