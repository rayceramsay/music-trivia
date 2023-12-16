package use_case.finish_round;

public class FinishRoundInputData {

    private final String gameId;

    public FinishRoundInputData(String gameId) {
        this.gameId = gameId;
    }

    String getGameId() {
        return gameId;
    }
}
