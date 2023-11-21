package use_case.load_game;

public class LoadGameOutputData {

    private final String gameId;
    private final String promptText;
    private final String genre;
    private final int initialLives;
    private final int currentLives;
    private final int maxRounds;
    private final int currentRoundNumber;

    public LoadGameOutputData(String gameId, String promptText, String genre, int initialLives, int currentLives,
                              int maxRounds, int currentRoundNumber) {
        this.gameId = gameId;
        this.promptText = promptText;
        this.genre = genre;
        this.initialLives = initialLives;
        this.currentLives = currentLives;
        this.maxRounds = maxRounds;
        this.currentRoundNumber = currentRoundNumber;
    }

    public String getGameId() {
        return gameId;
    }

    public String getPromptText() {
        return promptText;
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
}
