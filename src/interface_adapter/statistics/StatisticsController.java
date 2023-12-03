package interface_adapter.statistics;

import use_case.statistics.StatisticsInputBoundary;

/**
 * Controller for Statistics interface adapter
 */
public class StatisticsController {
    private final StatisticsInputBoundary statisticsInteractor;

    /**
     * Constructor to initialize objects of StatisticsController
     *
     * @param statisticsInteractor statistics input boundary
     */
    public StatisticsController(StatisticsInputBoundary statisticsInteractor) {
        this.statisticsInteractor = statisticsInteractor;
    }

    public void execute() {
        statisticsInteractor.execute();
    }
}
