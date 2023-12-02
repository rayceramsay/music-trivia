package use_case.exit_round;

/**
 * A DTO holding the ID of the game whose round to exit.
 */
public class ExitRoundInputData {

    private final String gameID;

    /**
     * Constructor to initialize objects of ExitRoundInputData
     *
     * @param gameID ID of current game
     */
    public ExitRoundInputData(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
