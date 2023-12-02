package interface_adapter.round;

import java.util.List;

public class RoundState {

    private String gameId = "";
    private String promptText = "";
    private String userAnswer = "";
    private String genre = "";
    private int initialLives;
    private int currentLives;
    private int maxRounds;
    private int currentRoundNumber;
    private int score;
    private List<String> multipleChoiceOptions;

    public RoundState() {}

    public String getGameId() {
        return gameId;
    }

    public String getPromptText() {
        return promptText;
    }

    public String getUserAnswer() {
        return userAnswer;
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

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setInitialLives(int initialLives) {
        this.initialLives = initialLives;
    }

    public void setCurrentLives(int currentLives) {
        this.currentLives = currentLives;
    }

    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    public void setCurrentRoundNumber(int currentRoundNumber) {
        this.currentRoundNumber = currentRoundNumber;
    }
    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}

    public void setMultipleChoiceOptions(List<String> options){
        this.multipleChoiceOptions = options;
    }
    public List<String> getMultipleChoiceOptions(){
        return this.multipleChoiceOptions;
    }

    public boolean isMultipleChoiceRound() {
        return multipleChoiceOptions != null && !multipleChoiceOptions.isEmpty();
    }
}