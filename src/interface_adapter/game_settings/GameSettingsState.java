package interface_adapter.game_settings;

/**
 * Implementation of GameOverState
 */
public class GameSettingsState {

    private String genre = "";
    private String difficulty = "";
    private int lives;
    private int rounds;

    /**
     * Constructor to initialize objects of GameSettingsState
     *
     * @param copy copy of GameSettingsState
     */
    public GameSettingsState(GameSettingsState copy) {
        this.genre = copy.genre;
        this.difficulty = copy.difficulty;
        this.lives = copy.lives;
        this.rounds = copy.rounds;
    }

    public GameSettingsState() {
    }

    /**
     * @return genre of songs in the game
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @return difficulty level of game
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * @return lives left in game
     */
    public int getLives() {
        return lives;
    }

    /**
     * @return amount of rounds in game
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * @param genre genre of songs played
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @param difficulty difficulty level of game
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @param lives amount of lives left
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * @param rounds amount of rounds in game
     */
    public void setRounds(int rounds) {
        this.rounds = rounds;
    }
}
