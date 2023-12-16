package interface_adapter.statistics;

import use_case.statistics.StatisticsInputBoundary;

public class StatisticsController {

    private final StatisticsInputBoundary statisticsInteractor;

    public StatisticsController(StatisticsInputBoundary statisticsInteractor) {
        this.statisticsInteractor = statisticsInteractor;
    }

    public void execute() {
        statisticsInteractor.execute();
    }
}
