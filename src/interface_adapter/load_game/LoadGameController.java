package interface_adapter.load_game;

import use_case.load_game.LoadGameInputBoundary;
import use_case.load_game.LoadGameInputData;

public class LoadGameController {
    private final LoadGameInputBoundary loadGameInteractor;

    public LoadGameController(LoadGameInputBoundary loadGameInteractor) {
        this.loadGameInteractor = loadGameInteractor;
    }

    public void execute(String gameID) {
        LoadGameInputData inputData = new LoadGameInputData(gameID);
        loadGameInteractor.execute(inputData);
    }
}
