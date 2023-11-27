package interface_adapter.statistics;

import use_case.statistics.StatisticsOutputBoundary;
import use_case.statistics.StatisticsOutputData;

public class StatisticsPresenter implements StatisticsOutputBoundary {
    private final StatisticsViewModel statisticsViewModel;
    public StatisticsPresenter(StatisticsViewModel statisticsViewModel) {
        this.statisticsViewModel = statisticsViewModel;
    }
    @Override
    public void prepareView(StatisticsOutputData statisticsOutputData) {
        StatisticsState statisticsState = statisticsViewModel.getState();
        statisticsState.setAverageLives((Integer) statisticsOutputData.getStats().get("Average Initial Lives"));
        statisticsState.setAverageScore((Integer) statisticsOutputData.getStats().get("Average Score"));
        statisticsState.setCommonGameDifficulty((String) statisticsOutputData.getStats().get("Most Common Game Difficulty"));
        statisticsState.setCommonGameGenre((String) statisticsOutputData.getStats().get("Most Common Genre"));
        statisticsState.setAverageRoundsPlayed((Integer) statisticsOutputData.getStats().get("Average Number of Rounds Played"));
        statisticsViewModel.firePropertyChanged();
    }
}
