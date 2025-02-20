package com.example.iterator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;
import model.ConcreteAggregate;
import model.Iterator;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {
    public Pane pane;
    public Label label;
    private ConcreteAggregate slides;
    private List<Image> imageList;
    @FXML
    public ImageView imageView;
    @FXML
    public Button buttonPlay;
    public Button buttonBack;
    public Button buttonStop;
    public Button buttonForward;
    public Button buttonChooseFolder;
    public Button buttonBegin;
    public Button buttonEnd;


    private int currentIndex = 0; // Для отслеживания текущего изображения
    private Timeline slideshowTimeline;

    @FXML
    private void onActionButtonChooseFolder(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите Папку");

        // Показываем диалог выбора папки и получаем выбранный каталог
        File selectedDirectory = directoryChooser.showDialog(pane.getScene().getWindow());

        if (selectedDirectory != null && selectedDirectory.isDirectory()) {
            label.setText(selectedDirectory.getAbsolutePath());
            slides = new ConcreteAggregate(selectedDirectory.getAbsolutePath());
            imageList = slides.getAllImages(); // Получаем список изображений
            currentIndex = 0; // Сбросим индекс
            if (!imageList.isEmpty()) {
                showImage(currentIndex); // Показ первого изображения
            } else {
                // Если папка не содержит изображений
                label.setText("Папка не содержит изображений.");
            }
        }
    }
    private void showImage(int index) {
        if (index >= 0 && index < imageList.size()) {
            Image currentImage = imageList.get(index);
            imageView.setImage(currentImage);
        }
    }

    @FXML
    private void nextSlide() {
        if (currentIndex < imageList.size() - 1) {
            currentIndex++;
            showImage(currentIndex);
        }
    }
    @FXML
    public void buttonStopPut(){
        // Логика остановки слайд-шоу
        if (slideshowTimeline != null) {
            slideshowTimeline.stop(); // Остановить слайд-шоу
        }
    }
    @FXML
    public void buttonBeginClick(){
        currentIndex = 0;
        showImage(currentIndex);
    }
    @FXML
    public void buttonForwardClick(){
        currentIndex+=1;
        showImage(currentIndex);
    }
    @FXML
    public void buttonBackClick(){
        currentIndex-=1;
        showImage(currentIndex);
    }
    @FXML
    public void buttonEndClick(){
        currentIndex = imageList.size() - 1;
        showImage(currentIndex);
    }
    @FXML
    public void buttonPlayPut () {
        // Логика запуска слайд-шоу
        if (slideshowTimeline != null) {
            slideshowTimeline.stop(); // Остановить предыдущее слайд-шоу, если оно работает
        }

        slideshowTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            nextSlide(); // Перейти к следующему слайду
        }));
        slideshowTimeline.setCycleCount(Timeline.INDEFINITE);
        slideshowTimeline.play(); // Запустите слайд-шоу
    }
    @FXML
    public void buttonEnClick(){
        Locale currentLocale = new Locale("en");
        ResourceBundle messages = ResourceBundle.getBundle("messages", currentLocale);
        buttonPlay.setText(messages.getString("Play"));
        buttonBack.setText(messages.getString("Back"));
        buttonStop.setText(messages.getString("Stop"));
        buttonForward.setText(messages.getString("Forward"));
        buttonChooseFolder.setText(messages.getString("ChooseFolder"));
        buttonBegin.setText(messages.getString("ToTheBeginning"));
        buttonEnd.setText(messages.getString("ToTheEnd"));

    }
    @FXML
    public void buttonRuClick(){
        Locale.setDefault(new Locale("ru", "RU"));

        ResourceBundle messages = ResourceBundle.getBundle("messages", Locale.getDefault());
        buttonPlay.setText(messages.getString("Play"));
        buttonPlay.setText(messages.getString("Play"));
        buttonBack.setText(messages.getString("Back"));
        buttonStop.setText(messages.getString("Stop"));
        buttonForward.setText(messages.getString("Forward"));
        buttonChooseFolder.setText(messages.getString("ChooseFolder"));
        buttonBegin.setText(messages.getString("ToTheBeginning"));
        buttonEnd.setText(messages.getString("ToTheEnd"));
    }


}