package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListCell;
public class ShapeCell extends ListCell<Shape> {
    @Override
    public void updateItem(Shape item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        } else {
            // Создание нового Canvas для отрисовки Shape
            Canvas canvas = new Canvas(60, 60); // Размер ячейки
            GraphicsContext gr = canvas.getGraphicsContext2D();

            // Клонируем фигуру для отрисовки
            Shape item1 = (Shape) item.clone(); // Предполагается, что у Shape есть метод clone()
            item1.draw(gr, 10, 10); // Отрисовка фигуры на канвасе

            // Устанавливаем канвас как графическое представление ячейки
            setGraphic(canvas);
        }
    }
}
