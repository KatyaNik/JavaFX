package com.example.game_of_bean_bags;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController implements Initializable {
    public Button PlayButton;
    public Button Bag1Button;
    public Button  Bag2Button;
    public Button  Bag3Button;
    public Button informationButton;
    public game_politics gaming = new game_politics();
    public wallet game_wallet = new wallet();
    public TextField capitalTextField;


    private ActionChain actionChain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        actionChain = new ActionChain();
        capitalTextField.setText(Integer.toString(game_wallet.getCapital()));
        picturesOnButton();
        // Добавляем Tooltips для всех элементов
        setupTooltips();
        // Проверка системы Tooltip
        Platform.runLater(() -> {
            Button testButton = new Button("Test Tooltip");
            Tooltip testTooltip = new Tooltip("Test Tooltip Works!");
            Tooltip.install(testButton, testTooltip);

            // Попробуйте показать программно
            Point2D p = testButton.localToScreen(0, 0);
            testTooltip.show(testButton, p.getX(), p.getY() + 30);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> testTooltip.hide());
                }
            }, 2000);
        });
    }
    private void setupTooltips() {
        // Убедимся, что Tooltip.install() используется явно
        installTooltip(capitalTextField, "Текущий баланс игрока");
        installTooltip(PlayButton, "Начать игру (стоимость 100 единиц)");
        installTooltip(Bag1Button, "Мешочек 1 - попробуйте удачу!");
        installTooltip(Bag2Button, "Мешочек 2 - может принести богатство!");
        installTooltip(Bag3Button, "Мешочек 3 - самый загадочный выбор!");
        installTooltip(informationButton, "Правила игры");
    }

    private void installTooltip(Control control, String text) {
        Tooltip tooltip = new Tooltip(text);

        // Сбросим все возможные стили, которые могут мешать
        tooltip.setStyle("");

        // Базовые настройки
        tooltip.setOpacity(1.0);
        tooltip.setAutoHide(true);

        // Альтернативный способ установки стиля (более надежный)
        tooltip.getStyleClass().add("custom-tooltip");

        // Настройки времени появления (уменьшим для теста)
        tooltip.setShowDelay(Duration.millis(100));
        tooltip.setHideDelay(Duration.millis(100));

        // Явная установка Tooltip
        Tooltip.uninstall(control, control.getTooltip()); // Сначала удаляем старый
        Tooltip.install(control, tooltip);

        // Для отладки
        control.setOnMouseEntered(e -> {
            System.out.println("Mouse entered: " + control.getId());
            System.out.println("Tooltip installed: " + (control.getTooltip() != null));
        });
    }
    @FXML
    public void Bag1ButtonOnAction() {
        if (gaming.playGame(this)) {
            disableTrueButton();
        }
        capitalTextField.setText(Integer.toString(game_wallet.getCapital()));
    }

    @FXML
    public void Bag2ButtonOnAction() {
        if (gaming.playGame(this)) {
            disableTrueButton();
        }
        capitalTextField.setText(Integer.toString(game_wallet.getCapital()));
    }

    @FXML
    public void Bag3ButtonOnAction() {
        if (gaming.playGame(this)) {
            disableTrueButton();
        }
        capitalTextField.setText(Integer.toString(game_wallet.getCapital()));
    }

    public wallet getGameWallet() {
        return game_wallet;
    }

    public void updateCapital() {
        capitalTextField.setText(Integer.toString(game_wallet.getCapital()));
    }

    public void showWinAlert(int amount) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Победа!");
        alert.setHeaderText("Вы выиграли " + amount + " единиц!");
        alert.showAndWait();
    }


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

    public void OnActioninformationButton(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация"); // Заголовок окна
        alert.setHeaderText("ВАЖНО!"); // Заголовок внутри окна
        alert.setContentText("Для того чтобы сыграть необходимо иметь на балансе не менее 100 единиц игровых денег!"); // Основной текст

        // Показать диалоговое окно
        alert.showAndWait();
    }

    private Label createLabel(String text, double fontSize) {
        Label label = new Label(text);
        label.setFont(new Font(fontSize));
        return label;
    }
}