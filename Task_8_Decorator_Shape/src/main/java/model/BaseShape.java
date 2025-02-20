package model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class BaseShape implements IShape {
    private Polygon polygon;

    public BaseShape(Pane pane, double... points) {
        this.polygon = new Polygon(points);
        pane.getChildren().add(polygon);
    }

    @Override
    public void draw() {}
}
