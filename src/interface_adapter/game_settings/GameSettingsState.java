package interface_adapter.game_settings;

import view.GameSettingsView;

public class GameSettingsState {

    private String genre = "";
    private String difficulty = "";
    private int lives;
    private int rounds;

    public GameSettingsState(GameSettingsState copy) {
        this.genre = copy.genre;
        this.difficulty = copy.difficulty;
        this.lives = copy.lives;
        this.rounds = copy.rounds;
    }

    public GameSettingsState() { }

    public String getGenre() {
        return genre;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getLives() {
        return lives;
    }

    public int getRounds() {
        return rounds;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }
}
