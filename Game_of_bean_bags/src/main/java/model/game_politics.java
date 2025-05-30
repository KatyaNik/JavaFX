package model;
import java.util.Random;
public class game_politics {


    private int generationmoney(){
        Random random = new Random();
        int[] meanings={100,-200,300,400,500,1000};
        // Генерация случайного индекса в диапазоне [0, meanings.length - 1]
        int randomIndex = random.nextInt(meanings.length);
        return meanings[randomIndex];
    }
    public int[] playgame(){
        int[] win = new int[3];
        for(int i=0;i<3;i++){
            win[i]=generationmoney();
        }
        return win;
    }
    public boolean balance_check(int money){
        if(money<100)
            return false;
        else
            return true;
    }
}
