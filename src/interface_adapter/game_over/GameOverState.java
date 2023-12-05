package interface_adapter.game_over;

/**
 * Implementation for GameOverState
 */
public class GameOverState {
    private int score = 0;

    /**
     * Constructor to initialize objects of GameOverState
     *
     * @param copy copy of GameOverState
     */
    public GameOverState(GameOverState copy) {
        score = copy.score;
    }

    public GameOverState() {
    }

    /**
     * @return score at the end of the game
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score initial score
     */
    public void setScore(int score) {
        this.score = score;
    }
}
