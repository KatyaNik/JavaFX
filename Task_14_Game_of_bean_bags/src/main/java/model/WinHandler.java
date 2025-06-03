package model;

import com.example.game_of_bean_bags.HelloController;

// Обработчик выигрыша
public class WinHandler extends Handler {
    public static final int WIN = 1;
    private int amount;

    public WinHandler(Handler nextHandler, int amount) {
        super(nextHandler);
        this.amount = amount;
    }

    @Override
    protected boolean canHandle(int request) {
        return request == WIN;
    }

    @Override
    protected boolean handle(HelloController controller) {
        controller.showWinAlert(amount);
        controller.getGameWallet().setCapital(controller.getGameWallet().getCapital() + amount);
        controller.updateCapital();
        return true;
    }
}