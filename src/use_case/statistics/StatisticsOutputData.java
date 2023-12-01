package use_case.statistics;

public class StatisticsOutputData {

    private String commonGameDifficulty;
    private String commonGameGenre;
    private int averageLives;
    private int averageScore;
    private int averageRoundsPlayed;
    private boolean hasStats;

    public StatisticsOutputData() {
    }

    public String getCommonGameDifficulty() {
        return commonGameDifficulty;
    }

    public void setCommonGameDifficulty(String commonGameDifficulty) {
        this.commonGameDifficulty = commonGameDifficulty;
    }

    public String getCommonGameGenre() {
        return commonGameGenre;
    }

    public void setCommonGameGenre(String commonGameGenre) {
        this.commonGameGenre = commonGameGenre;
    }

    public int getAverageLives() {
        return averageLives;
    }

    public void setAverageLives(int averageLives) {
        this.averageLives = averageLives;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public int getAverageRoundsPlayed() {
        return averageRoundsPlayed;
    }

    public void setAverageRoundsPlayed(int averageRoundsPlayed) {
        this.averageRoundsPlayed = averageRoundsPlayed;
    }

    public boolean hasStats() {
        return hasStats;
    }

    public void setHasStats(boolean hasStats) {
        this.hasStats = hasStats;
    }
}
