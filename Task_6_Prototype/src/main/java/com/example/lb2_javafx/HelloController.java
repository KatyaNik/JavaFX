package com.example.lb2_javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.*;
import java.net.URL;
import java.util.ResourceBundle;

import model.Shape;

public class HelloController implements Initializable  {
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker color;
    @FXML
    private TextField textF;
    @FXML
    public ListView <Shape> listView; // создать listView в XML


    ShapeFactory shapeFactory = new ShapeFactory();
    Shape shape = null;
    GraphicsContext gc;
    private boolean isDragging = false;
    private double dragOffsetX = 0;
    private double dragOffsetY = 0;

    private ObservableList<Shape> items;

    @Override
    public void initialize(URL location, ResourceBundle resources){
//        gc = canvas.getGraphicsContext2D();
//
//        items = FXCollections.observableArrayList();
//        items.add(shapeFactory.createShape("Line"));
//        //items.add(shapeFactory.createShape("Line"));
//        items.add(shapeFactory.createShape("Circle"));
//        items.add(shapeFactory.createShape("Triangle"));
//        items.add(shapeFactory.createShape("Rectangle"));
//        items.add(shapeFactory.createShape("Hexagon"));
//
//        listView.setItems(items);
//        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//
        gc = canvas.getGraphicsContext2D();
        items = FXCollections.observableArrayList();

        int[] shapeTypes = {1, 2, 3}; // 1 - Line, 2 - Rectangle, 3 - Hexagon

        for (int shapeType : shapeTypes) {
            items.add(shapeFactory.createShape(shapeType, 20, 20));
        }

        // Установка элементов в ListView
        listView.setItems(items);

        listView.setCellFactory(new Callback<ListView<Shape>, ListCell<Shape>>() {
            @Override
            public ListCell<Shape> call(ListView<Shape> list) {
                return new ShapeCell();
            }
        });
    }

    public void drawShape(MouseEvent mouseEvent) {
        int index = listView.getSelectionModel().getSelectedIndex(); //получение индекса выбора из списка
        Shape shape = (Shape) items.get(index).clone();// создание копии фигуры
        shape.setColor(color.getValue());// установка цвета заполнения фигуры по значению элемента управления colorPicker
        shape.draw(gc, mouseEvent.getX(), mouseEvent.getY()); // рисование копии фигуры в точке, полученной из события MouseEvent x
    }

    public void onMouseClick(MouseEvent mouseEvent) {

        //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawShape(mouseEvent);

//        double x = mouseEvent.getX();
//        double y = mouseEvent.getY();
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        shape = shapeFactory.createShape(Integer.parseInt(textF.getText()), x, y);
//        shape.setColor(color.getValue());
//        dragOffsetX = mouseEvent.getX() - shape.getX();
//        dragOffsetY = mouseEvent.getY() - shape.getY();
//        shape.draw(gc, mouseEvent.getX(), mouseEvent.getY());
    }
    public void MouseDragged(MouseEvent mouseEvent) {
        if (shape != null) {
            isDragging = true;
            double newX = mouseEvent.getX() - dragOffsetX;
            double newY = mouseEvent.getY() - dragOffsetY;
            shape.setPosition(newX, newY);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            shape.draw(gc, newX, newY);
        }
    }
    public void MouseReleased(MouseEvent mouseEvent) {
        if (isDragging) {
            isDragging = false;
            double newX = mouseEvent.getX() - dragOffsetX;
            double newY = mouseEvent.getY() - dragOffsetY;
            shape.setPosition(newX, newY);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            shape.draw(gc, newX, newY);
        }
    }

}