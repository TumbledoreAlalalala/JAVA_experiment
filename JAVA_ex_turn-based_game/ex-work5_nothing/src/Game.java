import java.util.ArrayList;

public class Game {
    private boolean gameOver =false;

    public boolean getGameState_over(){return gameOver;}
    public void setGameState_over(boolean state){ gameOver =state;}
    public void Play(){System.out.println("基类Game's Play()被调用");}
    public void Result(){System.out.println("基类Game's Result()被调用");}
}
