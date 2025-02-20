package com.example.task5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Director;
import model.IBuilder;
import model.SimpleBuilder;

public class HelloController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField textField;

    @FXML
    protected void buttonPaintAction(ActionEvent event)
    {
        IBuilder builder = new SimpleBuilder();
        Director director = new Director();
        int deviation = Integer.parseInt(textField.getText());//получение значения отклонения макушки
        if(deviation>=0 && deviation<=5){
            anchorPane.getChildren().clear();//очищение поля вывода
            //Первый параметр передает интерфейс типа IBuilder,
            // второй параметр это панель куда будет выводится рисунок,
            // третий параметр это отклонение елки
            director.build(builder, anchorPane,deviation);
            anchorPane.getChildren().add(builder.getTree());
        }
        else
        {
            anchorPane.getChildren().clear();//очищение поля вывода
            Alert alert = new Alert(Alert.AlertType.INFORMATION); // Тип сообщения: информация
            alert.setTitle("Информация");                   // Заголовок окна
            alert.setHeaderText(null);                       // Подзаголовок (можно оставить null)
            alert.setContentText("Неправильно введено отклонение"); // Содержимое сообщения
            alert.showAndWait();                             // Показать окно и ждать закрытия
            textField.setText("");
        }
    }
}