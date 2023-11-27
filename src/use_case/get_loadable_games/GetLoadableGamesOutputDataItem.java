package use_case.get_loadable_games;

public class GetLoadableGamesOutputDataItem {

    private final String gameID;
    private final String genre;
    private final String difficulty;
    private final int initialLives;
    private final int currentLives;
    private final int maxRounds;
    private final int currentRoundNumber;
    private final String createdAt;

    public GetLoadableGamesOutputDataItem(String gameID, String genre, String difficulty, int initialLives,
                                          int currentLives, int maxRounds, int currentRoundNumber, String createdAt) {
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

    public String getCreatedAt() {
        return createdAt;
    }
}
