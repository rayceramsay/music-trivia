package use_case.statistics;

public interface StatisticsOutputBoundary {
    /**
     * If game stats exist in output data, and shows lifetime statistics in String format.
     * If game stats do not exist in output data, show message that no stats have been recorded yet.
     *
     * @param statisticsOutputData Game difficulty, genre of music in game, average lives, average score, if stats exist.
     */
    void prepareView(StatisticsOutputData statisticsOutputData);
}

