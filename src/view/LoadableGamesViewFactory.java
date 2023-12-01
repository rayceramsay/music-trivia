package view;

import data_access.game_data.GameDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.get_loadable_games.GetLoadableGamesViewModel;
import interface_adapter.load_game.LoadGameController;
import interface_adapter.load_game.LoadGamePresenter;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.round.RoundViewModel;
import use_case.load_game.LoadGameInputBoundary;
import use_case.load_game.LoadGameInteractor;
import use_case.load_game.LoadGameOutputBoundary;

public class LoadableGamesViewFactory {

    private LoadableGamesViewFactory() {}

    public static LoadableGamesView create(ViewManagerModel viewManagerModel,
                                           GetLoadableGamesViewModel getLoadableGamesViewModel,
                                           RoundViewModel roundViewModel,
                                           LoadGameViewModel loadGameViewModel,
                                           GameDataAccessInterface gameDataAccessInterface) {
        LoadGameController loadGameController = createLoadGameUseCase(viewManagerModel, roundViewModel, loadGameViewModel, gameDataAccessInterface);

        return new LoadableGamesView(viewManagerModel, getLoadableGamesViewModel, loadGameController);
    }

    private static LoadGameController createLoadGameUseCase(ViewManagerModel viewManagerModel,
                                                            RoundViewModel roundViewModel,
                                                            LoadGameViewModel loadGameViewModel,
                                                            GameDataAccessInterface gameDataAccessObject) {
        LoadGameOutputBoundary loadGamePresenter = new LoadGamePresenter(viewManagerModel, roundViewModel, loadGameViewModel);
        LoadGameInputBoundary loadGameInteractor = new LoadGameInteractor(loadGamePresenter, gameDataAccessObject);

        return new LoadGameController(loadGameInteractor);
    }
}
