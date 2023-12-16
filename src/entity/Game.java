package entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a single game that the user has created
 */
public interface Game {
    String getID();
    String getDifficulty();
    String getGenre();
    int getInitialLives();
    int getMaxRounds();
    int getCurrentLives();
    int getRoundsPlayed();
    int getScore();
    Round getCurrentRound();
    List<Round> getRounds();
    LocalDateTime getCreatedAt();
    LocalDateTime getFinishedAt();
    void setScore(int score);
    void setCurrentRound(Round round);
    void setFinishedAt(LocalDateTime finishedAt);
    boolean isGameOver();
    void incrementScore();
    void decrementLives();
}
