package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BaseShape implements IShape {
    private Canvas canvas;
    private GraphicsContext gc;

    public BaseShape(Color colorFill, Color colorStroke, Canvas canvas, double... points) {
        if (canvas == null) {
            throw new IllegalArgumentException("Canvas не может быть null");
        }
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();

        // Устанавливаем цвет заливки и контура
        gc.setFill(colorFill); // Цвет заливки
        gc.setStroke(colorStroke); // Цвет контура

        // Рисуем фигуру
        drawPolygon(points);
    }

    protected void drawPolygon(double... points) {
        if (points.length >= 6 && points.length % 2 == 0) {
            gc.beginPath();

            // Начальная точка
            gc.moveTo(points[0], points[1]);

            // Остальные точки
            for (int i = 2; i < points.length; i += 2) {
                gc.lineTo(points[i], points[i + 1]);
            }

            // Замыкаем контур
            gc.closePath();

            // Заливаем и обводим контур
            gc.fill();
            gc.stroke();
        }
    }

    @Override
    public void draw() {
        // Реализация интерфейса (если нужно)
    }
}