package interface_adapter.get_loadable_games;

import use_case.get_loadable_games.GetLoadableGamesInputBoundary;

/**
 * Controller for GetLoadableGames interface adapter
 */
public class GetLoadableGamesController {

    private final GetLoadableGamesInputBoundary getLoadableGamesInteractor;

    /**
     * Constructor to initialize objects of GetLoadableGamesController
     *
     * @param getLoadableGamesInteractor GetLoadableGames Input Boundary
     */
    public GetLoadableGamesController(GetLoadableGamesInputBoundary getLoadableGamesInteractor) {
        this.getLoadableGamesInteractor = getLoadableGamesInteractor;
    }

    /**
     * Method to execute the interactor
     */
    public void execute() {
        getLoadableGamesInteractor.execute();
    }
}
