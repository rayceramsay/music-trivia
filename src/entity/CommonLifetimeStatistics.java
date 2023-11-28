package entity;

public class CommonLifetimeStatistics implements LifetimeStatistics {
    int getAverageScore;
    int getAverageInitialLives;
    int averageRoundsPlayed;
    String getTopDifficulty;
    String getTopGenre;

    public CommonLifetimeStatistics(int getAverageScore, int getAverageInitialLives,
                                    int averageRoundsPlayed, String getTopDifficulty, String getTopGenre) {
        this.getAverageScore = getAverageScore;
        this.getAverageInitialLives = getAverageInitialLives;
        this.averageRoundsPlayed = averageRoundsPlayed;
        this.getTopDifficulty = getTopDifficulty;
        this.getTopGenre = getTopGenre;
    }

    public int getAverageScore() {
        return getAverageScore;
    }

    public int getAverageInitialLives() {
        return getAverageInitialLives;
    }

    public int getAverageRoundsPlayed() {
        return averageRoundsPlayed;
    }

    public String getTopDifficulty() {
        return getTopDifficulty;
    }

    public String getTopGenre() {
        return getTopGenre;
    }
}