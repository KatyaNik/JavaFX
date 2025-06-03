package model;

import com.example.game_of_bean_bags.HelloController;

import java.util.Random;

public class game_politics {
    private ActionChain actionChain;

    public game_politics() {
        this.actionChain = new ActionChain();
    }

    public boolean playGame(HelloController controller) {
        return actionChain.process(controller);
    }

    public boolean balance_check(int money) {
        return money >= 100;
    }
}