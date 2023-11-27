package interface_adapter.statistics;

public class StatisticsState {
    public String commonGameDifficulty;
    public String commonGameGenre;
    public int averageLives;
    public int averageScore;
    public int averageRoundsPlayed;

    public int getAverageRoundsPlayed() {
        return averageRoundsPlayed;
    }

    public void setAverageRoundsPlayed(int averageRoundsPlayed) {
        this.averageRoundsPlayed = averageRoundsPlayed;
    }

    public StatisticsState(StatisticsState copy) {
        this.commonGameDifficulty = copy.commonGameDifficulty;
        this.commonGameGenre = copy.commonGameGenre;
        this.averageLives = copy.averageLives;
        this.averageScore = copy.averageScore;
        this.averageRoundsPlayed = copy.averageRoundsPlayed;
    }
    public StatisticsState(){}

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
}
