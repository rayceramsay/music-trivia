package use_case.exit_round;

public class ExitRoundInputData {

    private final String gameID;

    public ExitRoundInputData(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
