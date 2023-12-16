package entity;

/**
 * Core implementation of lifetime statistics object
 */
public class CommonLifetimeStatistics implements LifetimeStatistics {

    private final int averageScore;
    private final int averageInitialLives;
    private final int averageRoundsPlayed;
    private final String topDifficulty;
    private final String topGenre;

    public CommonLifetimeStatistics(int averageScore, int averageInitialLives, int averageRoundsPlayed,
                                    String topDifficulty, String topGenre) {
        this.averageScore = averageScore;
        this.averageInitialLives = averageInitialLives;
        this.averageRoundsPlayed = averageRoundsPlayed;
        this.topDifficulty = topDifficulty;
        this.topGenre = topGenre;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public int getAverageInitialLives() {
        return averageInitialLives;
    }

    public int getAverageRoundsPlayed() {
        return averageRoundsPlayed;
    }

    public String getTopDifficulty() {
        return topDifficulty;
    }

    public String getTopGenre() {
        return topGenre;
    }
}