package interface_adapter.get_loadable_games;

public class GetLoadableGamesStateItem {

    private final String gameID;
    private final String genre;
    private final String difficulty;
    private final String initialLives;
    private final String currentLives;
    private final String maxRounds;
    private final String currentRoundNumber;
    private final String createdAt;

    public GetLoadableGamesStateItem(String gameID, String genre, String difficulty, String initialLives,
                                     String currentLives, String maxRounds, String currentRoundNumber, String createdAt) {
        this.gameID = gameID;
        this.genre = genre;
        this.difficulty = difficulty;
        this.initialLives = initialLives;
        this.currentLives = currentLives;
        this.maxRounds = maxRounds;
        this.currentRoundNumber = currentRoundNumber;
        this.createdAt = createdAt;
    }

    public String getGameID() {
        return gameID;
    }

    public String getGenre() {
        return genre;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getInitialLives() {
        return initialLives;
    }

    public String getCurrentLives() {
        return currentLives;
    }

    public String getMaxRounds() {
        return maxRounds;
    }

    public String getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
