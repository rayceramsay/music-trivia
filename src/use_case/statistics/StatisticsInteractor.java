package use_case.statistics;

import data_access.game_data.GameDataAccessInterface;
import entity.LifetimeStatistics;

/**
 * Interactor which implements the Input Boundary for the Statistics use case
 */
public class StatisticsInteractor implements StatisticsInputBoundary {

    private final GameDataAccessInterface gameDataAccessObject;
    private final StatisticsOutputBoundary statisticsPresenter;

    /**
     * Constructor to initialize objects of StatisticsInteractor
     *
     * @param statisticsDataAccessInterface Data access interface for statistics use case
     * @param statisticsOutputBoundary      Output boundary for statistics use case
     */

    public StatisticsInteractor(GameDataAccessInterface statisticsDataAccessInterface,
                                StatisticsOutputBoundary statisticsOutputBoundary) {
        gameDataAccessObject = statisticsDataAccessInterface;
        statisticsPresenter = statisticsOutputBoundary;
    }

    @Override
    public void execute() {
        LifetimeStatistics data = gameDataAccessObject.avgStats();
        StatisticsOutputData statisticsOutputData = new StatisticsOutputData();
        if (data == null) {
            statisticsOutputData.setHasStats(false);
        } else {
            statisticsOutputData.setHasStats(true);
            statisticsOutputData.setAverageLives(data.getAverageInitialLives());
            statisticsOutputData.setAverageScore(data.getAverageScore());
            statisticsOutputData.setCommonGameDifficulty(data.getTopDifficulty());
            statisticsOutputData.setCommonGameGenre(data.getTopGenre());
            statisticsOutputData.setAverageRoundsPlayed(data.getAverageRoundsPlayed());
        }
        statisticsPresenter.prepareView(statisticsOutputData);

    }
}
