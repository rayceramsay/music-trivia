package use_case.statistics;
import entity.LifetimeStatistics;

import java.util.HashMap;
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
      LifetimeStatistics data = gameDataAccessObject.avgStats();
        StatisticsOutputData statisticsOutputData = new StatisticsOutputData();
        if(data == null){
            statisticsOutputData.setHasStats(false);
        }else{
            statisticsOutputData.setHasStats(true);
            statisticsOutputData.setAverageLives( data.getAverageInitialLives());
            statisticsOutputData.setAverageScore( data.getAverageScore());
            statisticsOutputData.setCommonGameDifficulty( data.getTopDifficulty());
            statisticsOutputData.setCommonGameGenre( data.getTopGenre());
            statisticsOutputData.setAverageRoundsPlayed( data.getAverageRoundsPlayed());
        }
        statisticsPresenter.prepareView(statisticsOutputData);

    }
}
