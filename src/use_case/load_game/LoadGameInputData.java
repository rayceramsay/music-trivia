package use_case.load_game;

public class LoadGameInputData {

    private final String gameID;

    public LoadGameInputData(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
