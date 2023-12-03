package use_case.statistics;

/**
 * Input boundary interface for Statistics use case
 */
public interface StatisticsInputBoundary {
    /**
     * Get data from the game data access object.
     * If there is no data, there is no output data
     * If there is data, set all the parameters with the corresponding statistics
     * and prepare view in the presenter based on the output dat.
     */
    void execute();
}
