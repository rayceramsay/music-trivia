package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.get_loadable_games.GetLoadableGamesController;
import interface_adapter.get_loadable_games.GetLoadableGamesPresenter;
import interface_adapter.get_loadable_games.GetLoadableGamesViewModel;
import use_case.get_loadable_games.GetLoadableGamesGameDataAccessInterface;
import use_case.get_loadable_games.GetLoadableGamesInputBoundary;
import use_case.get_loadable_games.GetLoadableGamesInteractor;
import use_case.get_loadable_games.GetLoadableGamesOutputBoundary;

public class MenuViewFactory {

    private MenuViewFactory() {}

    public static MenuView create(ViewManagerModel viewManagerModel,
                                   GameSettingsViewModel gameSettingsViewModel,
                                   GetLoadableGamesViewModel getLoadableGamesViewModel,
                                   GetLoadableGamesGameDataAccessInterface gameDataAccessObject) {
        GetLoadableGamesController getLoadableGamesController = createGetLoadableGamesUseCase(viewManagerModel, getLoadableGamesViewModel, gameDataAccessObject);

        return new MenuView(viewManagerModel, gameSettingsViewModel, getLoadableGamesController);
    }

    private static GetLoadableGamesController createGetLoadableGamesUseCase(ViewManagerModel viewManagerModel,
                                                                            GetLoadableGamesViewModel getLoadableGamesViewModel,
                                                                            GetLoadableGamesGameDataAccessInterface gameDataAccessObject) {
        GetLoadableGamesOutputBoundary presenter = new GetLoadableGamesPresenter(getLoadableGamesViewModel, viewManagerModel);
        GetLoadableGamesInputBoundary interactor = new GetLoadableGamesInteractor(presenter, gameDataAccessObject);

        return new GetLoadableGamesController(interactor);
    }
}
