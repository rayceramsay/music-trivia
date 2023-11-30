package use_case.exit_round;

/**
 * A DTO holding the ID of the game whose round to exit.
 */
public class ExitRoundInputData {

    private final String gameID;

    public ExitRoundInputData(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
