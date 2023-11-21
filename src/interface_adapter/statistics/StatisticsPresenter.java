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
        statisticsState.setStats(statisticsOutputData.getStats());
        statisticsViewModel.firePropertyChanged();
    }
}
