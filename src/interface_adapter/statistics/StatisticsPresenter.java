package interface_adapter.statistics;

import use_case.statistics.StatisticsOutputBoundary;
import use_case.statistics.StatisticsOutputData;

/**
 * Implementation of StatisticsOutputBoundary
 */
public class StatisticsPresenter implements StatisticsOutputBoundary {
    private final StatisticsViewModel statisticsViewModel;

    /**
     * Constructor to initialize objects of StatisticsPresenter
     *
     * @param statisticsViewModel View model for statistics interface adapter
     */
    public StatisticsPresenter(StatisticsViewModel statisticsViewModel) {
        this.statisticsViewModel = statisticsViewModel;
    }

    @Override
    public void prepareView(StatisticsOutputData statisticsOutputData) {
        StatisticsState statisticsState = statisticsViewModel.getState();
        if (statisticsOutputData.hasStats()) {
            statisticsState.setStatsMessage(String.format("Here are your lifetime statistics...\n" +
                    "Average Score: " + statisticsOutputData.getAverageScore() + "\n" +
                    "Average Initial Lives: " + statisticsOutputData.getAverageLives() + "\n" +
                    "Average # of Rounds Played: " + statisticsOutputData.getAverageRoundsPlayed() + "\n" +
                    "Most Common Game Difficulty: " + statisticsOutputData.getCommonGameDifficulty() + "\n" +
                    "Most Common Genre Played: " + statisticsOutputData.getCommonGameGenre()));
        } else {
            statisticsState.setStatsMessage("No stats have been recorded ... play games to record stats");
        }
        statisticsViewModel.firePropertyChanged();
    }
}
