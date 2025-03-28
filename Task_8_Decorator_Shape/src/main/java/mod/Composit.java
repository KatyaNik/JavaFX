package mod;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Composit extends Shape {
    private List<Shape> shapes;

    public Composit() {
        super(0, 0);
        this.shapes = new ArrayList<>();
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public void draw(GraphicsContext gc) {
        for (Shape shape : shapes) {
            shape.draw(gc);
        }
    }

    @Override
    public void setPosition(double newX, double newY) {
        // Если хотите, можете перемещать CompositeShape как одно целое
        double deltaX = newX - x;
        double deltaY = newY - y;
        super.setPosition(newX, newY);

        for (Shape shape : shapes) {
            shape.setPosition(shape.getX() + deltaX, shape.getY() + deltaY);
        }
    }

    @Override
    public void drawBorder(GraphicsContext gc) {
        for (Shape shape : shapes) {
            shape.drawBorder(gc); // если фигуры могут иметь границу
        }
    }

    @Override
    public void drawText(GraphicsContext gc) {
        for (Shape shape : shapes) {
            shape.drawText(gc); // если фигуры могут иметь текст
        }
    }

    @Override
    public Object clone() {
        Composit compositeClone = new Composit();
        for (Shape shape : shapes) {
            compositeClone.add((Shape) shape.clone());
        }
        return compositeClone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CompositeShape contains:\n");
        for (Shape shape : shapes) {
            sb.append(shape.toString()).append("\n");
        }
        return sb.toString();
    }
}
