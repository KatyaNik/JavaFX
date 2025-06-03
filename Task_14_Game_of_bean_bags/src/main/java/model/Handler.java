package model;

import com.example.game_of_bean_bags.HelloController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public abstract class Handler {
    private Handler nextHandler;

    public Handler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public boolean process(int request, HelloController controller) {
        if (canHandle(request)) {
            return handle(controller);
        } else if (nextHandler != null) {
            return nextHandler.process(request, controller);
        }
        return true;
    }

    protected abstract boolean canHandle(int request);
    protected abstract boolean handle(HelloController controller);
}







