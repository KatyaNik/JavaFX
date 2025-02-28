package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class BaseShape implements IShape {
    private Polygon polygon;
    public Canvas canvas;
    public GraphicsContext gc;
    public BaseShape(Color colorFill, Color colorStroke, Pane pane, double... points) {
        //this.polygon = new Polygon(points);
        //pane.getChildren().add(polygon);



        canvas = new Canvas(pane.getWidth(), pane.getHeight());
        pane.getChildren().add(canvas);
        // Получаем контекст рисования для панели
        gc = canvas.getGraphicsContext2D();
        // Устанавливаем цвет заливки и контура
        gc.setFill(colorFill); // Цвет заливки
        gc.setStroke(colorStroke); // Цвет контура
        // Нарисуем фигуру (в данном случае многоугольник)
        this.drawPolygon(points);

    }
    protected void drawPolygon(double... points) {
        if (points.length >= 6 && points.length % 2 == 0) {
            this.gc.beginPath();

            // Начальная точка
            this.gc.moveTo(points[0], points[1]);

            // Остальные точки
            for (int i = 2; i < points.length; i += 2) {
                this.gc.lineTo(points[i], points[i+1]);
            }

            // Замыкаем контур
            this.gc.closePath();

            // Заливаем и обводим контур
            this.gc.fill();
            this.gc.stroke();
        }
    }
    @Override
    public void draw() {}
}
