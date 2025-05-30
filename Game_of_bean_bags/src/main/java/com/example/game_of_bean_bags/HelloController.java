package com.example.game_of_bean_bags;

import javafx.scene.control.*;
import javafx.scene.text.Font;
import model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Button PlayButton;
    public Button Bag1Button;
    public Button  Bag2Button;
    public Button  Bag3Button;
    public Button informationButton;
    public game_politics gaming = new game_politics();
    public wallet game_wallet = new wallet();
    public TextField capitalTextField;
    @FXML
    public void OnActionPlayButton(){
        if(gaming.balance_check(game_wallet.getCapital())){

            disableFalseButton();
            picturesOnButton();
        }
        else{
            PlayButton.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ВНИМАНИЕ"); // Заголовок окна
            alert.setHeaderText("На вашем счете недостаточно средств! " );

            alert.setContentText("Вы проиграли!!!" ); // Основной текст
            // Показать диалоговое окно
            alert.showAndWait();
            disableTrueButton();

        }
    }
    public void picturesOnButton(){
        try {
            // Загрузка изображения из ресурсов
            InputStream inputStream1 = getClass().getResourceAsStream("/images/1.png");
            InputStream inputStream2 = getClass().getResourceAsStream("/images/2.png");
            InputStream inputStream3 = getClass().getResourceAsStream("/images/3.png");
            InputStream inputStream4 = getClass().getResourceAsStream("/images/4.png");
            if (inputStream1 == null) {
                System.err.println("Ошибка: Изображение не найдено!");
                return;
            }

            Image image = new Image(inputStream1);
            ImageView imageView = new ImageView(image);
            // Настройка размера изображения
            imageView.setFitWidth(60);
            imageView.setFitHeight(60);
            // Пример использования ImageView
            Bag1Button.setGraphic(imageView);

            Image image2 = new Image(inputStream2);
            ImageView imageView2 = new ImageView(image2);
            // Настройка размера изображения
            imageView2.setFitWidth(60);
            imageView2.setFitHeight(60);
            Bag2Button.setGraphic(imageView2);

            Image image3 = new Image(inputStream3);
            ImageView imageView3 = new ImageView(image3);
            // Настройка размера изображения
            imageView3.setFitWidth(60);
            imageView3.setFitHeight(60);
            Bag3Button.setGraphic(imageView3);

            Image image4 = new Image(inputStream4);
            ImageView imageView4 = new ImageView(image4);
            // Настройка размера изображения
            imageView4.setFitWidth(20);
            imageView4.setFitHeight(20);
            informationButton.setGraphic(imageView4);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        capitalTextField.setText(Integer.toString(game_wallet.getCapital()));
        picturesOnButton();
    }
    public void setMoneyOnButton(int[] money){
        // Создание меток
        Bag1Button.setGraphic(createLabel(String.valueOf(money[0]), 48));
        Bag2Button.setGraphic(createLabel(String.valueOf(money[1]), 48));
        Bag3Button.setGraphic(createLabel(String.valueOf(money[2]), 48));

    }
    public void messageBox(int[] money, int win, int i){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ПОБЕДА"); // Заголовок окна
        alert.setHeaderText("Вы выиграли " + money[i]); // Заголовок внутри окна
        alert.setContentText("Ваш итоговы баланс: " + win); // Основной текст

        // Показать диалоговое окно
        alert.showAndWait();
    }
    @FXML
    public void Bag1ButtonOnAction() {
        int[] money = gaming.playgame(); // Получаем массив чисел

        // Проверка массива
        if (money == null || money.length < 3) {
            throw new IllegalArgumentException("Массив должен содержать хотя бы 3 элемента.");
        }
        setMoneyOnButton(money);
        int win = game_wallet.getCapital()-100+money[0];
        game_wallet.setCapital(win);
        messageBox(money, win,0);
        disableTrueButton();
        capitalTextField.setText(Integer.toString(game_wallet.getCapital()));

    }
    public void disableFalseButton(){
        Bag1Button.setDisable(false);
        Bag2Button.setDisable(false);
        Bag3Button.setDisable(false);
    }
    public void disableTrueButton(){
        Bag1Button.setDisable(true);
        Bag2Button.setDisable(true);
        Bag3Button.setDisable(true);
    }
    public void Bag2ButtonOnAction() {
        int[] money = gaming.playgame(); // Получаем массив чисел

        // Проверка массива
        if (money == null || money.length < 3) {
            throw new IllegalArgumentException("Массив должен содержать хотя бы 3 элемента.");
        }
        setMoneyOnButton(money);
        int win = game_wallet.getCapital()-100+money[1];
        game_wallet.setCapital(win);
        messageBox(money, win,1);
        disableTrueButton();

        capitalTextField.setText(Integer.toString(game_wallet.getCapital()));

    }
    public void OnActioninformationButton(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация"); // Заголовок окна
        alert.setHeaderText("ВАЖНО!"); // Заголовок внутри окна
        alert.setContentText("Для того чтобы сыграть необходимо иметь на балансе не менее 100 единиц игровых денег!"); // Основной текст

        // Показать диалоговое окно
        alert.showAndWait();
    }
    public void Bag3ButtonOnAction() {
        int[] money = gaming.playgame(); // Получаем массив чисел

        // Проверка массива
        if (money == null || money.length < 3) {
            throw new IllegalArgumentException("Массив должен содержать хотя бы 3 элемента.");
        }
        setMoneyOnButton(money);
        int win = game_wallet.getCapital()-100+money[2];
        game_wallet.setCapital(win);
        messageBox(money, win,2);
        disableTrueButton();

        capitalTextField.setText(Integer.toString(game_wallet.getCapital()));

    }
    private Label createLabel(String text, double fontSize) {
        Label label = new Label(text);
        label.setFont(new Font(fontSize));
        return label;
    }
}