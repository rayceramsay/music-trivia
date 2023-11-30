package interface_adapter.round;

import java.util.ArrayList;
import java.util.Collections;

public class RoundState {

    private String gameId = "";
    private String promptText = "";
    private String userAnswer = "";
    private String genre = "";
    private int initialLives;
    private int currentLives;
    private int maxRounds;
    private int currentRoundNumber;
    private List<String> multipleChoiceOptions;
    private boolean isEasyRound = false;
    private boolean isMediumRound = false;
    private boolean isHardRound = false;

    public RoundState(RoundState copy) {
        gameId = copy.gameId;
        promptText = copy.promptText;
        userAnswer = copy.userAnswer;
        genre = copy.genre;
        initialLives = copy.initialLives;
        currentLives = copy.currentLives;
        maxRounds = copy.maxRounds;
        currentRoundNumber = copy.currentRoundNumber;
        multipleChoice1 = copy.multipleChoice1;
        multipleChoice2 = copy.multipleChoice2;
        multipleChoice3 = copy.multipleChoice3;
        multipleChoice4 = copy.multipleChoice4;
        isEasyRound = copy.isEasyRound;
        isMediumRound = copy.isMediumRound;
        isHardRound = copy.isHardRound;
    }

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

    public String getMultipleChoice1() {
        return multipleChoice1;
    }

    public String getMultipleChoice2() {
        return multipleChoice2;
    }

    public String getMultipleChoice3() {
        return multipleChoice3;
    }

    public String getMultipleChoice4() {
        return multipleChoice4;
    }

    public ArrayList<String> getEasyChoices () {
        ArrayList<String> ret = new ArrayList<>();
        ret.add(multipleChoice1);
        ret.add(multipleChoice2);
        Collections.shuffle(ret);
        return ret;
    }

    public ArrayList<String> getMediumChoices () {
        ArrayList<String> ret = new ArrayList<>();
        ret.add(multipleChoice1);
        ret.add(multipleChoice2);
        ret.add(multipleChoice3);
        ret.add(multipleChoice4);
        Collections.shuffle(ret);
        return ret;
    }

    public void setMultipleChoice1(String multipleChoice1) {
        this.multipleChoice1 = multipleChoice1;
    }

    public void setMultipleChoice2(String multipleChoice2) {
        this.multipleChoice2 = multipleChoice2;
    }

    public void setMultipleChoice3(String multipleChoice3) {
        this.multipleChoice3 = multipleChoice3;
    }

    public void setMultipleChoice4(String multipleChoice4) {
        this.multipleChoice4 = multipleChoice4;
    }

    public boolean isEasyRound() {
        return isEasyRound;
    }

    public boolean isMediumRound() {
        return isMediumRound;
    }

    public boolean isHardRound() {
        return isHardRound;
    }

    public void setEasyRound(boolean easyRound) {
        isEasyRound = easyRound;
    }

    public void setMediumRound(boolean mediumRound) {
        isMediumRound = mediumRound;
    }

    public void setHardRound(boolean hardRound) {
        isHardRound = hardRound;
    }
}