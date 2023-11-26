package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommonGame implements Game {
    private final String ID;
    private int score;
    private final String difficulty;
    private final String genre;
    private final int initialLives;
    private final int maxRounds;
    private int currentLives;
    private ArrayList<Round> allRounds;
    private final LocalDateTime createdAt;
    private LocalDateTime finishedAt;

   public CommonGame(String genre, String difficulty, int maxRounds,
               int initialLives) {
        this.ID = UUID.randomUUID().toString();
        this.genre = genre;
        this.difficulty = difficulty;
        this.maxRounds = maxRounds;
        this.initialLives = initialLives;
        this.currentLives = initialLives;
        this.createdAt = LocalDateTime.now();
        this.allRounds = new ArrayList<>();
    }

    @Override
    public String getID() {
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
        return initialLives;
    }

    @Override
    public int getMaxRounds() {return maxRounds;}

    @Override
    public int getCurrentLives() {
        return currentLives;
    }

    @Override
    public int getRoundsPlayed() {
        return allRounds.size();
    }

    @Override
    public int getScore() {return score;}
    @Override
    public Round getCurrentRound() {return allRounds.get(allRounds.size() - 1);}
    @Override
    public List<Round> getRounds() {
        return allRounds;
    }

    @Override
    public LocalDateTime getCreatedAt() {return createdAt;}

    @Override
    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    @Override
    public void setCurrentLives(int lives) {
        currentLives = lives;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }
    @Override
    public void setCurrentRound(Round round) {allRounds.add(round);}
    @Override
    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    // TODO, implement
    @Override
    public boolean isGameOver() {
        return false;
    }
}
