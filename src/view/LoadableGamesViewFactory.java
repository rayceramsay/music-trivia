package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.get_loadable_games.GetLoadableGamesViewModel;
import interface_adapter.load_game.LoadGameController;
import interface_adapter.load_game.LoadGamePresenter;
import interface_adapter.round.RoundViewModel;
import use_case.load_game.LoadGameGameDataAccessInterface;
import use_case.load_game.LoadGameInputBoundary;
import use_case.load_game.LoadGameInteractor;
import use_case.load_game.LoadGameOutputBoundary;

public class LoadableGamesViewFactory {

    private LoadableGamesViewFactory() {}

    public static LoadableGamesView create(ViewManagerModel viewManagerModel,
                                           GetLoadableGamesViewModel getLoadableGamesViewModel,
                                           RoundViewModel roundViewModel,
                                           LoadGameGameDataAccessInterface loadGameGameDataAccessObject) {
        LoadGameController loadGameController = createLoadGameUseCase(viewManagerModel, roundViewModel, loadGameGameDataAccessObject);

        return new LoadableGamesView(viewManagerModel, getLoadableGamesViewModel, loadGameController);
    }

    private static LoadGameController createLoadGameUseCase(ViewManagerModel viewManagerModel,
                                                            RoundViewModel roundViewModel,
                                                            LoadGameGameDataAccessInterface gameDataAccessObject) {
        LoadGameOutputBoundary loadGamePresenter = new LoadGamePresenter(viewManagerModel, roundViewModel);
        LoadGameInputBoundary loadGameInteractor = new LoadGameInteractor(loadGamePresenter, gameDataAccessObject);

        return new LoadGameController(loadGameInteractor);
    }
}
