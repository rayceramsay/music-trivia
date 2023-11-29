package entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Game interface
 */
public interface Game {
    /**
     * @return Game ID.
     */
    String getID();

    /**
     * @return Difficulty of game.
     */
    String getDifficulty();

    /**
     * @return Genre of music in game.
     */
    String getGenre();

    /**
     * @return Initial amount of lives set at the start of the game.
     */
    int getInitialLives();

    /**
     * @return Max amount of rounds that can be played.
     */
    int getMaxRounds();

    /**
     * @return Current amount of lives.
     */
    int getCurrentLives();

    /**
     * @return Total amount of rounds played.
     */
    int getRoundsPlayed();

    /**
     * @return Current score.
     */
    int getScore();

    /**
     * @return Current Round.
     */
    Round getCurrentRound();

    /**
     * @return List of all rounds.
     */
    List<Round> getRounds();

    /**
     * @return Time of game creation.
     */
    LocalDateTime getCreatedAt();

    /**
     * @return Time that game ended.
     */
    LocalDateTime getFinishedAt();

    /**
     * @param lives amount of lives left
     */
    void setCurrentLives(int lives);

    /**
     * @param score current score
     */
    void setScore(int score);

    /**
     * @param round current round
     */
    void setCurrentRound(Round round);

    /**
     * @param finishedAt time that game ended
     */
    void setFinishedAt(LocalDateTime finishedAt);

    /**
     * @return If game is over.
     */
    boolean isGameOver();

    /**
     * Adds 1 to score.
     */
    void incrementScore();

    /**
     * Subtracts 1 from score.
     */
    void decrementLives();
}
