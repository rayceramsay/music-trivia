package use_case.load_game;

import java.util.ArrayList;

public class LoadGameOutputData {

    private final String gameId;
    private final String question;
    private final String genre;
    private final String difficulty;
    private final int initialLives;
    private final int currentLives;
    private final int maxRounds;
    private final int currentRoundNumber;
    private final ArrayList<String> multipleChoiceAnswers;
    private final int score;

    public LoadGameOutputData(String gameId, String question, String genre, String difficulty, int initialLives, int currentLives,
                              int maxRounds, int currentRoundNumber, ArrayList<String> multipleChoiceAnswers, int score) {
        this.gameId = gameId;
        this.question = question;
        this.genre = genre;
        this.difficulty = difficulty;
        this.initialLives = initialLives;
        this.currentLives = currentLives;
        this.maxRounds = maxRounds;
        this.currentRoundNumber = currentRoundNumber;
        this.multipleChoiceAnswers = multipleChoiceAnswers;
        this.score = score;
    }

    public String getGameId() {
        return gameId;
    }

    public String getQuestion() {
        return question;
    }

    public String getGenre() {
        return genre;
    }

    public int getInitialLives() {
        return initialLives;
    }

    public int getCurrentLives() {
        return currentLives;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public int getScore() {return score;}

    public String getDifficulty() {
        return difficulty;
    }

    public ArrayList<String> getMultipleChoiceAnswers() {
        return multipleChoiceAnswers;
    }
}
