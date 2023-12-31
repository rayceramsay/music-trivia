package view;

import data_access.game_data.GameDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.get_loadable_games.GetLoadableGamesController;
import interface_adapter.get_loadable_games.GetLoadableGamesPresenter;
import interface_adapter.get_loadable_games.GetLoadableGamesViewModel;
import interface_adapter.statistics.StatisticsController;
import interface_adapter.statistics.StatisticsPresenter;
import interface_adapter.statistics.StatisticsViewModel;
import use_case.get_loadable_games.GetLoadableGamesInputBoundary;
import use_case.get_loadable_games.GetLoadableGamesInteractor;
import use_case.get_loadable_games.GetLoadableGamesOutputBoundary;
import use_case.statistics.StatisticsInputBoundary;
import use_case.statistics.StatisticsInteractor;
import use_case.statistics.StatisticsOutputBoundary;

public class MenuViewFactory {

    private MenuViewFactory() {
    }

    /**
     * Creates an instance of MenuView
     *
     * @param viewManagerModel          View manager model
     * @param gameSettingsViewModel     View model for GameSettings
     * @param getLoadableGamesViewModel View model for GetLoadableGames
     * @param statisticsViewModel       View model for statistics
     * @param gameDataAccessInterface   Data access interface
     * @return MenuView
     */
    public static MenuView create(ViewManagerModel viewManagerModel,
                                  GameSettingsViewModel gameSettingsViewModel,
                                  GetLoadableGamesViewModel getLoadableGamesViewModel,
                                  StatisticsViewModel statisticsViewModel,
                                  GameDataAccessInterface gameDataAccessInterface) {
        GetLoadableGamesController getLoadableGamesController = createGetLoadableGamesUseCase(viewManagerModel, getLoadableGamesViewModel, gameDataAccessInterface);
        StatisticsController statisticsController = createGetStatisticsUseCase(statisticsViewModel, gameDataAccessInterface);

        return new MenuView(viewManagerModel, gameSettingsViewModel, statisticsViewModel, getLoadableGamesController, statisticsController);
    }

    /**
     * Creates an instance of GetLoadableGamesController
     *
     * @param viewManagerModel          View manager model
     * @param getLoadableGamesViewModel View model for GetLoadableGames
     * @param gameDataAccessObject      Data access object
     * @return GetLoadableGamesController
     */
    private static GetLoadableGamesController createGetLoadableGamesUseCase(ViewManagerModel viewManagerModel,
                                                                            GetLoadableGamesViewModel getLoadableGamesViewModel,
                                                                            GameDataAccessInterface gameDataAccessObject) {
        GetLoadableGamesOutputBoundary presenter = new GetLoadableGamesPresenter(getLoadableGamesViewModel, viewManagerModel);
        GetLoadableGamesInputBoundary interactor = new GetLoadableGamesInteractor(presenter, gameDataAccessObject);

        return new GetLoadableGamesController(interactor);
    }

    /**
     * Creates instance of StatisticsController
     *
     * @param statisticsViewModel  View model for statistics
     * @param gameDataAccessObject Data access interface
     * @return StatisticsController
     */
    private static StatisticsController createGetStatisticsUseCase(StatisticsViewModel statisticsViewModel,
                                                                   GameDataAccessInterface gameDataAccessObject) {
        StatisticsOutputBoundary statisticsOutputBoundary = new StatisticsPresenter(statisticsViewModel);
        StatisticsInputBoundary statisticsInteractor = new StatisticsInteractor(gameDataAccessObject, statisticsOutputBoundary);

        return new StatisticsController(statisticsInteractor);
    }
}
