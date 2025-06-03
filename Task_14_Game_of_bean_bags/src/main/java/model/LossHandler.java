package model;

import com.example.game_of_bean_bags.HelloController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

// Обработчик проигрыша
public class LossHandler extends Handler {
    public static final int LOSS = 2;

    public LossHandler(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected boolean canHandle(int request) {
        return request == LOSS;
    }

    @Override
    protected boolean handle(HelloController controller) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Проигрыш");
        alert.setHeaderText("Вы потеряли 100 единиц!");

        ButtonType replay = new ButtonType("Продолжить", ButtonBar.ButtonData.YES);
        ButtonType quit = new ButtonType("Выйти", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(replay, quit);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == replay;
    }
}