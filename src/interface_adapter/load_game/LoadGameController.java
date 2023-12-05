package interface_adapter.load_game;

import use_case.load_game.LoadGameInputBoundary;
import use_case.load_game.LoadGameInputData;

/**
 * Controller for LoadGame interface adapter
 */
public class LoadGameController {
    private final LoadGameInputBoundary loadGameInteractor;

    /**
     * Constructor to initialize objects of LoadGameController
     *
     * @param loadGameInteractor LoadGame input boundary
     */
    public LoadGameController(LoadGameInputBoundary loadGameInteractor) {
        this.loadGameInteractor = loadGameInteractor;
    }

    /**
     * Method to execute the interactor using the given input data
     *
     * @param gameID ID of current game
     */
    public void execute(String gameID) {
        LoadGameInputData inputData = new LoadGameInputData(gameID);
        loadGameInteractor.execute(inputData);
    }
}
