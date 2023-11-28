package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.statistics.StatisticsController;
import interface_adapter.statistics.StatisticsPresenter;
import interface_adapter.statistics.StatisticsViewModel;
import use_case.statistics.StatisticsDataAccessInterface;
import use_case.statistics.StatisticsInputBoundary;
import use_case.statistics.StatisticsInteractor;
import use_case.statistics.StatisticsOutputBoundary;
import view.MenuView;

public class MenuViewUseCaseFactory {
    public static MenuView create(MenuViewModel menuViewModel, ViewManagerModel viewManagerModel, StatisticsViewModel
            statisticsViewModel, StatisticsDataAccessInterface statisticsDataAccessInterface) {
        StatisticsController statisticsController = statisticsUseCase(viewManagerModel, statisticsViewModel, statisticsDataAccessInterface);
        return new MenuView(menuViewModel, viewManagerModel, statisticsViewModel, statisticsController);
    }
    private static StatisticsController statisticsUseCase(ViewManagerModel viewManagerModel, StatisticsViewModel statisticsViewModel, StatisticsDataAccessInterface statisticsDataAccessInterface) {
        StatisticsOutputBoundary statisticsOutputBoundary = new StatisticsPresenter(statisticsViewModel);
        StatisticsInputBoundary statisticsInteractor = new StatisticsInteractor(statisticsDataAccessInterface, statisticsOutputBoundary);
        return new StatisticsController(statisticsInteractor);
    }
}
