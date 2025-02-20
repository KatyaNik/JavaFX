package model;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle() {
        super();
        this.color=getColor();
        this.width = 0;
        this.height = 0;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    @Override
    public String toString() {
        return " Rectangle";
    }

    @Override
    public void draw(GraphicsContext gc,  double mouseX, double mouseY) {
        setCircuit(gc);
        gc.setLineWidth(2); // Устанавливаем толщину линии, если нужно

        // Вычисляем координаты всех четырех углов прямоугольника
        double x1 = mouseX;
        double y1 = mouseY;

        double x2 = x + width;
        double y2 = y + height;

        gc.beginPath();
        gc.moveTo(x1, y1); // Верхний левый угол
        gc.lineTo(x2, y1); // Верхний правый угол
        gc.lineTo(x2, y2); // Нижний правый угол
        gc.lineTo(x1, y2); // Нижний левый угол
        gc.closePath(); // Замыкаем путь
        gc.stroke(); // Рисуем линию
    }

}
