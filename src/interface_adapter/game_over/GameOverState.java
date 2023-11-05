package interface_adapter.game_over;

public class GameOverState {
    private int score = 0;
    public GameOverState(GameOverState copy){
        score = copy.score;
    }
    public GameOverState(){}

    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }
}
