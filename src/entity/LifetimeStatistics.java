package entity;

/**
 * Stores statistics aggregated from every game the user has ever played
 */
public interface LifetimeStatistics {
    /**
     * @return Average Score.
     */
    int getAverageScore();

    /**
     * @return Average amount of initial lives.
     */
    int getAverageInitialLives();

    /**
     * @return Average amount of lives played.
     */
    int getAverageRoundsPlayed();

    /**
     * @return Top difficulty played.
     */
    String getTopDifficulty();

    /**
     * @return Top genre of songs played.
     */
    String getTopGenre();
}
