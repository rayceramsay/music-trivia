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

    /**
     * Constructor to initialize objects of GetLoadableGamesOutputDataItem
     *
     * @param gameID             GameID
     * @param genre              genre of music played in game
     * @param difficulty         difficulty level of game
     * @param initialLives       initial lives set for game
     * @param currentLives       current amount of lives
     * @param maxRounds          max amount of rounds that can be played
     * @param currentRoundNumber number of current round
     * @param createdAt          time of game creation
     */
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
