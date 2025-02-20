package com.example.task_10_adapter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController {
    public ListView<String> demoList;
    public Button newButton;
    public Button deleteButton;
    public Button findButton;
    public TextArea textArea;
    public TextArea textArea1;
    public TextField textField;
    @FXML
    public Adapter adapter = new Adapter(10);
    public void OnNewButtonClicked(){
        textArea1.setText("");
        adapter.newStackElement(Long.parseLong(textArea.getText()));


        display();
        textArea.setText("");
    }
    public void display(){
        demoList.getItems().clear();
        String input = adapter.displayElements();
        String delimiter = " ";

        String[] result = splitString(input, delimiter);

        for (String word : result) {
            demoList.getItems().add(word);
        }
    }
    public void OnfindButtonClicked(){

        //textArea1.setText(Long.toString(adapter.displayTopElement()));
        textArea1.setText(adapter.findElement(textField.getText()));
    }
    public void OnCheckButtonClicked(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        String stat = adapter.StackCheck();
        alert.setHeaderText("Сообщение о состоянии списка");
        if(stat=="Список пуст."){
            alert.setContentText("Список пуст.");
        }
        else{

            alert.setContentText("Список имеет записи.");
        }


        alert.showAndWait();
    }
    public void OndeleteButtonClicked(){
        adapter.deleteStackElement();
        display();
    }
    public static String[] splitString(String inputString, String delimiter) {
        return inputString.split(delimiter);
    }

}