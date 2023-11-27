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
        StringBuilder mapAsString = new StringBuilder();
        for (String key : statisticsOutputData.getStats().keySet()) {
            mapAsString.append(key).append(":").append(" ").append(statisticsOutputData.getStats().get(key)).append("\n");}
        String stringToShow = String.format("Here are your statistics, Player! \n"  + mapAsString);
        statisticsState.setStats(stringToShow);
        statisticsViewModel.firePropertyChanged();
    }
}
