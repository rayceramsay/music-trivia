package app;

import data_access.InMemoryGameDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_game.CreateGameController;
import interface_adapter.create_game.CreateGamePresenter;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.statistics.StatisticsViewModel;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import use_case.create_game.CreateGameInputBoundary;
import use_case.create_game.CreateGameInteractor;
import use_case.create_game.CreateGameOutputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main{
    public static void main(String[] args){
        // Setup JFrame app
        JFrame application = new JFrame("Spotify Bandits");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        application.setMinimumSize(new Dimension(300, 200));
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Setup view manager
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Create view models
        MenuViewModel menuViewModel = new MenuViewModel();
        GameSettingsViewModel gameSettingsViewModel = new GameSettingsViewModel();
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        RoundViewModel roundViewModel = new RoundViewModel();
        SubmitAnswerViewModel submitAnswerViewModel = new SubmitAnswerViewModel();
        StatisticsViewModel statisticsViewModel = new StatisticsViewModel();

        // Create data access objects
        InMemoryGameDataAccessObject gameDataAccessObject = new InMemoryGameDataAccessObject();
        CreateGameOutputBoundary createGamePresenter = new CreateGamePresenter(viewManagerModel, roundViewModel);
        CreateGameInputBoundary createGameInteractor = new CreateGameInteractor(gameDataAccessObject, createGamePresenter, roundViewModel);
        CreateGameController createGameController = new CreateGameController(createGameInteractor);
        GameSettingsView gameSettingsView = new GameSettingsView(gameSettingsViewModel, viewManagerModel, createGameController);

        // Create views
        MenuView menuView = MenuViewUseCaseFactory.create(menuViewModel, viewManagerModel, statisticsViewModel, gameDataAccessObject);
        GameOverView gameOverView = new GameOverView(gameOverViewModel, viewManagerModel);
        RoundView roundView = RoundViewFactory.create(viewManagerModel, roundViewModel, submitAnswerViewModel, gameOverViewModel, gameDataAccessObject);

        // Add views to app
        views.add(menuView, menuView.viewName);
        views.add(gameSettingsView, gameSettingsView.viewName);
        views.add(gameOverView, gameOverView.viewName);
        views.add(roundView, roundView.viewName);


        // Set starting view
        viewManagerModel.setActiveView(menuView.viewName);
        viewManagerModel.firePropertyChanged();

        // Start app
        application.setLocationRelativeTo(null); // app opens on center of screen
        application.pack();
        application.setVisible(true);
    }
}
