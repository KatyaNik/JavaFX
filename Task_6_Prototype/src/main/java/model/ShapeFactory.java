package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

//public class ShapeFactory {
//
//    public Canvas createShape(String shapeName) {
//        Canvas canvas = new Canvas(100, 30);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        switch (shapeName) {
//            case "Line":
//                drawLine(gc);
//                break;
//            case "Circle":
//                drawCircle(gc);
//                break;
//            case "Triangle":
//                drawTriangle(gc);
//                break;
//            case "Rectangle":
//                drawRectangle(gc);
//                break;
//            case "Hexagon":
//                drawHexagon(gc);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown shape: " + shapeName);
//        }
//
//        return canvas;
//    }
//
//    private void drawLine(GraphicsContext gc) {
//        gc.setStroke(Color.BLACK);
//        gc.strokeLine(0, 15, 100, 15);
//    }
//
//    private void drawCircle(GraphicsContext gc) {
//        gc.setFill(Color.BLUE);
//        gc.fillOval(25, 0, 50, 30);
//    }
//
//    private void drawTriangle(GraphicsContext gc) {
//        gc.setFill(Color.GREEN);
//        gc.fillPolygon(new double[]{0, 50, 100}, new double[]{30, 0, 30}, 3);
//    }
//
//    private void drawRectangle(GraphicsContext gc) {
//        gc.setFill(Color.RED);
//        gc.fillRect(0, 0, 100, 30);
//    }
//
//    private void drawHexagon(GraphicsContext gc) {
//        gc.setFill(Color.ORANGE);
//        gc.fillPolygon(new double[]{25, 75, 100, 75, 25, 0},
//                new double[]{0, 0, 15, 30, 30, 15}, 6);
//    }
//}
public class ShapeFactory  {
//    public Shape createShape(String key){
//
//        HashMap<String, Shape> hashMap = new HashMap<String, Shape>();
//
//        hashMap.put("Line", new Line());
//        hashMap.put("Circle", new Circle());
//        hashMap.put("Triangle", new Triangle());
//        hashMap.put("Rectangle", new Rectangle());
//        hashMap.put("Hexagon", new Hexagon());
//
//        return hashMap.get(key);
//    }
private Map<Integer, BiFunction<Double, Double, Shape>> shapeMap;

    public ShapeFactory() {
        shapeMap = new HashMap<>();
        shapeMap.put(1, (x, y) -> new Line());
        shapeMap.put(2, (x, y) -> new Rectangle());
        shapeMap.put(3, (x, y) -> new Hexagon());
    }

    public Shape createShape(int num, double x, double y) {
        BiFunction<Double, Double, Shape> shapeCreator = shapeMap.get(num);
        if (shapeCreator != null) {
            return shapeCreator.apply(x, y);
        } else {
            return null;
        }
    }
}

