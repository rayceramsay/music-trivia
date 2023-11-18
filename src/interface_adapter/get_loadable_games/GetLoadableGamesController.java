package interface_adapter.get_loadable_games;

import use_case.get_loadable_games.GetLoadableGamesInputBoundary;

public class GetLoadableGamesController {
    private final GetLoadableGamesInputBoundary getLoadableGamesInteractor;

    public GetLoadableGamesController(GetLoadableGamesInputBoundary getLoadableGamesInteractor) {
        this.getLoadableGamesInteractor = getLoadableGamesInteractor;
    }

    public void execute() {
        getLoadableGamesInteractor.execute();
    }
}
