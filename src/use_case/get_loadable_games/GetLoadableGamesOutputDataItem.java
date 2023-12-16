package use_case.get_loadable_games;

import java.time.LocalDateTime;

public class GetLoadableGamesOutputDataItem {

    private final String gameID;
    private final String genre;
    private final String difficulty;
    private final int initialLives;
    private final int currentLives;
    private final int maxRounds;
    private final int currentRoundNumber;
    private final LocalDateTime createdAt;
    private final int score;

    public GetLoadableGamesOutputDataItem(String gameID, String genre, String difficulty, int initialLives,
                                          int currentLives, int maxRounds, int currentRoundNumber, LocalDateTime createdAt, int score) {
        this.gameID = gameID;
        this.genre = genre;
        this.difficulty = difficulty;
        this.initialLives = initialLives;
        this.currentLives = currentLives;
        this.maxRounds = maxRounds;
        this.currentRoundNumber = currentRoundNumber;
        this.createdAt = createdAt;
        this.score = score;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getScore() {
        return score;
    }
}
