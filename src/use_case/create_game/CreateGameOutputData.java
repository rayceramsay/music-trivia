package use_case.create_game;

import java.util.ArrayList;
import java.util.List;

public class CreateGameOutputData {

    private String gameId;
    private String difficulty;
    private String genre;
    private int rounds;
    private int lives;
    private List<String> multipleChoiceAnswers;
    public CreateGameOutputData() {}

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public String getGameId() {
        return gameId;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getGenre() {
        return genre;
    }

    public int getRounds() {
        return rounds;
    }

    public int getLives() {
        return lives;
    }

    public List<String> getMultipleChoiceAnswers() {
        return multipleChoiceAnswers;
    }

    public void setMultipleChoiceAnswers(List<String> multipleChoiceAnswers) {
        this.multipleChoiceAnswers = multipleChoiceAnswers;
    }
}
