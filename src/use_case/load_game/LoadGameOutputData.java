package use_case.load_game;

public class LoadGameOutputData {

    private final String gameId;
    private final String question;
    private final String genre;
    private final int initialLives;
    private final int currentLives;
    private final int maxRounds;
    private final int currentRoundNumber;
    private final int score;

    /**
     * Constructor to initialize objects of LoadGameOutputData
     *
     * @param gameId             ID of game
     * @param question           Prompt used throughout the game
     * @param genre              genre of songs used in game
     * @param initialLives       initial amount of lives in game
     * @param currentLives       current amount of lives left
     * @param maxRounds          maximum amount of rounds left
     * @param currentRoundNumber current round
     */
    public LoadGameOutputData(String gameId, String question, String genre, int initialLives, int currentLives,
                              int maxRounds, int currentRoundNumber, int score) {
        this.gameId = gameId;
        this.question = question;
        this.genre = genre;
        this.initialLives = initialLives;
        this.currentLives = currentLives;
        this.maxRounds = maxRounds;
        this.currentRoundNumber = currentRoundNumber;
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
}
