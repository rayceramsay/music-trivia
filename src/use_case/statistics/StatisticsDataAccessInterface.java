package use_case.statistics;

import entity.Game;
import entity.LifetimeStatistics;

/**
 * Data access interface for Statistics use case
 */
public interface StatisticsDataAccessInterface {
    /**
     * Outputs statistics:
     * Average score,
     * A average initial lives,
     * Average rounds played,
     * Most common difficulty level,
     * Most common genre.
     *
     * @return LifetimeStatistics
     */
    LifetimeStatistics avgStats();

    void save(Game game);
}
