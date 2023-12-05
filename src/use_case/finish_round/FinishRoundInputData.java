package use_case.finish_round;

public class FinishRoundInputData {
    private final String gameId;

    /**
     * Constructor to initialize objects of FinishRoundInputData
     *
     * @param gameId GameId
     */
    public FinishRoundInputData(String gameId) {
        this.gameId = gameId;
    }

    String getGameId() {
        return gameId;
    }
}
