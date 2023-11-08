package entity;

import java.time.LocalDateTime;
import java.util.List;

public interface Game {

    // GETTERS
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

    // SETTERS
    void setCurrentLives(int lives);
    void setScore(int score);
    void setCurrentRound(Round round);
    void setFinishedAt(LocalDateTime finishedAt);

    //OTHER
    boolean isGameOver();
    void incrementScore();
    void decrementLives();

}
