package use_case.statistics;

public class StatisticsInteractor implements StatisticsInputBoundary {

    private final StatisticsDataAccessInterface gameDataAccessObject;
    private final StatisticsOutputBoundary statisticsPresenter;
    public StatisticsInteractor(StatisticsDataAccessInterface statisticsDataAccessInterface,
                                StatisticsOutputBoundary statisticsOutputBoundary) {
        gameDataAccessObject = statisticsDataAccessInterface;
        statisticsPresenter = statisticsOutputBoundary;
    }
    @Override
    public void execute() {
        StatisticsOutputData statisticsOutputData =
                new StatisticsOutputData(gameDataAccessObject.avgStats());
        statisticsPresenter.prepareView(statisticsOutputData);

    }
}
