package use_case.load_game;

public class LoadGameInputData {

    private final String gameID;

    /**
     * Constructor to initialize objects of LoadGameInputData
     *
     * @param gameID GameID
     */
    public LoadGameInputData(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
