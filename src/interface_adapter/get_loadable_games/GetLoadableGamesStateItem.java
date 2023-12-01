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
    private final String score;

    /**
     * Constructor to initialize objects of GetLoadableGamesStateItem
     *
     * @param gameID             gameID of loadable game
     * @param genre              genre of loadable game
     * @param difficulty         difficulty level of loadable game
     * @param initialLives       initial lives of loadable game
     * @param currentLives       current lives of loadable game
     * @param maxRounds          amount of max rounds of loadable game
     * @param currentRoundNumber current round of loadable game (so user can start where left off)
     * @param createdAt          time when game was created
     */
    public GetLoadableGamesStateItem(String gameID, String genre, String difficulty, String initialLives,
                                     String currentLives, String maxRounds, String currentRoundNumber,
                                     String createdAt, String score) {
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

    public String getScore() {return score;}
}
