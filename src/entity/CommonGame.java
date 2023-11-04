package entity;

import java.time.LocalDateTime;
import java.util.List;

public class CommonGame implements Game {
    private int ID;
    private int score;
    public String difficulty;
    public String genre;
    public int InitialLives;
    public int max_rounds;
    public int current_lives;
    public int rounds_played = 0;
    private final LocalDateTime createdAt;
    private LocalDateTime FinishedAt;

    // TODO add arguments to constructor?
    CommonGame(String genre, String difficulty, int max_rounds,
               int initialLives,  LocalDateTime creationTime) {
        this.genre = genre;
        this.difficulty = difficulty;
        this.max_rounds = max_rounds;
        this.InitialLives = initialLives;
        this.createdAt = creationTime;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public int getInitialLives() {
        return InitialLives;
    }

    @Override
    public int getMaxRounds() {return max_rounds;}

    @Override
    public int getCurrentLives() {
        return current_lives;
    }

    @Override
    public int getRoundsPlayed() {
        return rounds_played;
    }

    @Override
    public int getScore() {return score;}

    // TODO
    @Override
    public Round getRound() {
        return null;
    }
    // TODO
    @Override
    public List<Round> getRounds() {
        return null;
    }

    @Override
    public LocalDateTime getCreatedAt() {return createdAt;}

    @Override
    public LocalDateTime getFinishedAt() {
        return FinishedAt;
    }

    @Override
    public void setCurrentLives(int lives) {
        current_lives = lives;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void setCurrentRound(Round round) {}
    // TODO
    @Override
    public void setFinishedAt(LocalDateTime finishedAt) {
        FinishedAt = finishedAt;
    }

    // TODO, implement
    @Override
    public boolean isGameOver() {
        return false;
    }
}
