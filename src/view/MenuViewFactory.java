package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.get_loadable_games.GetLoadableGamesController;
import interface_adapter.get_loadable_games.GetLoadableGamesPresenter;
import interface_adapter.get_loadable_games.GetLoadableGamesViewModel;
import interface_adapter.statistics.StatisticsController;
import interface_adapter.statistics.StatisticsPresenter;
import interface_adapter.statistics.StatisticsViewModel;
import use_case.get_loadable_games.GetLoadableGamesGameDataAccessInterface;
import use_case.get_loadable_games.GetLoadableGamesInputBoundary;
import use_case.get_loadable_games.GetLoadableGamesInteractor;
import use_case.get_loadable_games.GetLoadableGamesOutputBoundary;
import use_case.statistics.StatisticsDataAccessInterface;
import use_case.statistics.StatisticsInputBoundary;
import use_case.statistics.StatisticsInteractor;
import use_case.statistics.StatisticsOutputBoundary;

public class MenuViewFactory {

    private MenuViewFactory() {}

    public static MenuView create(ViewManagerModel viewManagerModel,
                                   GameSettingsViewModel gameSettingsViewModel,
                                   GetLoadableGamesViewModel getLoadableGamesViewModel,
                                   StatisticsViewModel statisticsViewModel,
                                   GetLoadableGamesGameDataAccessInterface getLoadableGamesGameDataAccessObject,
                                   StatisticsDataAccessInterface statisticsDataAccessObject) {
        GetLoadableGamesController getLoadableGamesController = createGetLoadableGamesUseCase(viewManagerModel, getLoadableGamesViewModel, getLoadableGamesGameDataAccessObject);
        StatisticsController statisticsController = createGetStatisticsUseCase(statisticsViewModel, statisticsDataAccessObject);

        return new MenuView(viewManagerModel, gameSettingsViewModel, statisticsViewModel, getLoadableGamesController, statisticsController);
    }

    private static GetLoadableGamesController createGetLoadableGamesUseCase(ViewManagerModel viewManagerModel,
                                                                            GetLoadableGamesViewModel getLoadableGamesViewModel,
                                                                            GetLoadableGamesGameDataAccessInterface gameDataAccessObject) {
        GetLoadableGamesOutputBoundary presenter = new GetLoadableGamesPresenter(getLoadableGamesViewModel, viewManagerModel);
        GetLoadableGamesInputBoundary interactor = new GetLoadableGamesInteractor(presenter, gameDataAccessObject);

        return new GetLoadableGamesController(interactor);
    }

    private static StatisticsController createGetStatisticsUseCase(StatisticsViewModel statisticsViewModel,
                                                                   StatisticsDataAccessInterface statisticsDataAccessInterface) {
        StatisticsOutputBoundary statisticsOutputBoundary = new StatisticsPresenter(statisticsViewModel);
        StatisticsInputBoundary statisticsInteractor = new StatisticsInteractor(statisticsDataAccessInterface, statisticsOutputBoundary);

        return new StatisticsController(statisticsInteractor);
    }
}
