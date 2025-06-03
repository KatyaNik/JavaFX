package model;

import com.example.game_of_bean_bags.HelloController;
import javafx.scene.control.Alert;

// Обработчик шанса
public class ChanceHandler extends Handler {
    public static final int CHANCE = 3;

    public ChanceHandler(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected boolean canHandle(int request) {
        return request == CHANCE;
    }

    @Override
    protected boolean handle(HelloController controller) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Шанс");
        alert.setHeaderText("Вы получаете бесплатную попытку!");
        alert.showAndWait();
        return true;
    }
}