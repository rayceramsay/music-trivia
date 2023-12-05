package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of Game
 */
public class CommonGame implements Game {

    private final String ID;
    private final String genre;
    private final String difficulty;
    private final int maxRounds;
    private final int initialLives;
    private int currentLives;
    private int score;
    private final LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private final List<Round> allRounds = new ArrayList<>();

    /**
     * Constructor to initialize objects of CommonGame
     *
     * @param ID           ID of game
     * @param genre        type of songs chosen by API
     * @param difficulty   level of gameplay
     * @param maxRounds    total rounds until game over
     * @param initialLives preset amount of lives before start of game
     * @param currentLives current amount of lives left
     * @param score        score of the game
     * @param createdAt    time of game creation
     * @param finishedAt   time of game completion
     */
    public CommonGame(String ID, String genre, String difficulty, int maxRounds, int initialLives, int currentLives,
                      int score, LocalDateTime createdAt, LocalDateTime finishedAt) {
        this.ID = ID;
        this.genre = genre;
        this.difficulty = difficulty;
        this.maxRounds = maxRounds;
        this.initialLives = initialLives;
        this.currentLives = currentLives;
        this.score = score;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
    }

    public CommonGame(String genre, String difficulty, int maxRounds, int initialLives) {
        this(UUID.randomUUID().toString(), genre, difficulty, maxRounds, initialLives, initialLives,
                0, LocalDateTime.now(), null);
    }

    /**
     * @return Game ID
     */
    @Override
    public String getID() {
        return ID;
    }

    /**
     * @return Difficulty of game
     */
    @Override
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * @return Genre of songs played in this game
     */
    @Override
    public String getGenre() {
        return genre;
    }

    /**
     * @return Amount of initial lives
     */
    @Override
    public int getInitialLives() {
        return initialLives;
    }

    /**
     * @return Total amount of rounds that can be played
     */
    @Override
    public int getMaxRounds() {
        return maxRounds;
    }

    /**
     * @return current amount of lives
     */
    @Override
    public int getCurrentLives() {
        return currentLives;
    }

    /**
     * Gets the total amount rounds played so far.
     *
     * @return int
     */
    @Override
    public int getRoundsPlayed() {
        return allRounds.size();
    }

    /**
     * @return score of game
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * @return current round
     */
    @Override
    public Round getCurrentRound() {
        int roundsSize = allRounds.size();
        if (roundsSize == 0) {
            return null;
        }

        return allRounds.get(roundsSize - 1);
    }

    /**
     * @return List of all rounds played in the game
     */
    @Override
    public List<Round> getRounds() {
        return allRounds;
    }

    /**
     * @return Time of game creation
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * @return Time that game ended
     */
    @Override
    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    /**
     * @param lives current lives
     */
    @Override
    public void setCurrentLives(int lives) {
        currentLives = lives;
    }

    /**
     * @param score current score
     */
    @Override
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @param round current round
     */
    @Override
    public void setCurrentRound(Round round) {
        allRounds.add(round);
    }

    /**
     * @param finishedAt game end time
     */
    @Override
    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    /**
     * Checks whether the game is over or not.
     *
     * @return boolean
     */
    @Override
    public boolean isGameOver() {
        boolean currentRoundIsFinished = getCurrentRound().isFinished();

        return (currentLives <= 0)
                || (allRounds.size() == maxRounds && currentRoundIsFinished)
                || (allRounds.size() > maxRounds)
                || (finishedAt != null);
    }

    /**
     * Adds one to current score.
     */
    @Override
    public void incrementScore() {
        this.score += 1;
    }

    /**
     * Subtracts one from current lives.
     */
    @Override
    public void decrementLives() {
        this.currentLives -= 1;
    }
}
