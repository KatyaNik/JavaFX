package model;

import com.example.game_of_bean_bags.HelloController;

import java.util.Random;

public class ActionChain {
    private Handler chain;
    private Random random = new Random();

    public ActionChain() {
        buildChain();
    }

    private void buildChain() {
        chain = new WinHandler(
                new LossHandler(
                        new ChanceHandler(null)), 300); // Пример выигрыша в 300
    }

    public boolean process(HelloController controller) {
        int request = random.nextInt(3) + 1; // Генерация случайного события
        return chain.process(request, controller);
    }
}
