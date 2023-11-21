package interface_adapter.statistics;

import use_case.statistics.StatisticsInputBoundary;
import use_case.statistics.StatisticsInteractor;

public class StatisticsController {
    private final StatisticsInputBoundary statisticsInteractor;
    public StatisticsController(StatisticsInputBoundary statisticsInteractor) {
        this.statisticsInteractor = statisticsInteractor;
    }
    public void execute() {statisticsInteractor.execute();}
}
