package use_case.statistics;
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
      HashMap<String, Object> data = gameDataAccessObject.avgStats();
        StatisticsOutputData statisticsOutputData = new StatisticsOutputData();
        if(data.isEmpty()){
            statisticsOutputData.setHasStats(false);
        }else{
            statisticsOutputData.setHasStats(true);
            statisticsOutputData.setAverageLives((Integer) data.get("Average Initial Lives"));
            statisticsOutputData.setAverageScore((Integer) data.get("Average Score"));
            statisticsOutputData.setCommonGameDifficulty((String) data.get("Most Common Game Difficulty"));
            statisticsOutputData.setCommonGameGenre((String) data.get("Most Common Genre"));
            statisticsOutputData.setAverageRoundsPlayed((Integer) data.get("Average Number of Rounds Played"));
        }
        statisticsPresenter.prepareView(statisticsOutputData);

    }
}
