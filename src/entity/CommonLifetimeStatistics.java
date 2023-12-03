package entity;

/**
 * Implementation of interface LifeTimeStatistics
 */
public class CommonLifetimeStatistics implements LifetimeStatistics {

    int getAverageScore;
    int getAverageInitialLives;
    int averageRoundsPlayed;
    String getTopDifficulty;
    String getTopGenre;

    /**
     * Constructor to initialize objects of CommonLifetimeStatistics
     *
     * @param getAverageScore        Average score statistic
     * @param getAverageInitialLives Average initial lives statistic
     * @param averageRoundsPlayed    Average rounds played statistic
     * @param getTopDifficulty       Highest difficulty played
     * @param getTopGenre            Most popular genre of songs
     */
    public CommonLifetimeStatistics(int getAverageScore, int getAverageInitialLives,
                                    int averageRoundsPlayed, String getTopDifficulty, String getTopGenre) {
        this.getAverageScore = getAverageScore;
        this.getAverageInitialLives = getAverageInitialLives;
        this.averageRoundsPlayed = averageRoundsPlayed;
        this.getTopDifficulty = getTopDifficulty;
        this.getTopGenre = getTopGenre;
    }

    /**
     * @return Average score
     */
    public int getAverageScore() {
        return getAverageScore;
    }

    /**
     * @return Average initial lives
     */
    public int getAverageInitialLives() {
        return getAverageInitialLives;
    }

    /**
     * @return Average amount of rounds played
     */
    public int getAverageRoundsPlayed() {
        return averageRoundsPlayed;
    }

    /**
     * @return Top difficulty level played
     */
    public String getTopDifficulty() {
        return getTopDifficulty;
    }

    /**
     * @return Top genre played
     */
    public String getTopGenre() {
        return getTopGenre;
    }
}