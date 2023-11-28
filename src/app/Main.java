package app;

import data_access.InMemoryGameDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_game.CreateGameController;
import interface_adapter.create_game.CreateGamePresenter;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.get_loadable_games.GetLoadableGamesViewModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.statistics.StatisticsViewModel;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
import use_case.create_game.CreateGameInteractor;
import use_case.create_game.*;
import view.*;
import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // Setup JFrame app
        JFrame application = new JFrame("Spotify Bandits");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        application.setMinimumSize(new Dimension(500, 200));
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Setup view manager
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Create view models
        GameSettingsViewModel gameSettingsViewModel = new GameSettingsViewModel(GameSettingsView.VIEW_NAME);
        GameOverViewModel gameOverViewModel = new GameOverViewModel(GameOverView.VIEW_NAME);
        RoundViewModel roundViewModel = new RoundViewModel(RoundView.VIEW_NAME);
        SubmitAnswerViewModel submitAnswerViewModel = new SubmitAnswerViewModel(RoundView.VIEW_NAME);
        GetLoadableGamesViewModel getLoadableGamesViewModel = new GetLoadableGamesViewModel(LoadableGamesView.VIEW_NAME);
        StatisticsViewModel statisticsViewModel = new StatisticsViewModel(MenuView.VIEW_NAME);
        ToggleAudioViewModel toggleAudioViewModel = new ToggleAudioViewModel(RoundView.VIEW_NAME);

        // Create data access objects
        InMemoryGameDataAccessObject gameDataAccessObject = new InMemoryGameDataAccessObject();

        // Create objects for GameSettings View (TEMPORARY UNTIL FACTORY IS CREATED)
        CreateGameOutputBoundary createGamePresenter = new CreateGamePresenter(viewManagerModel, roundViewModel);
        CreateGameInputBoundary createGameInteractor = new CreateGameInteractor(gameDataAccessObject, createGamePresenter);
        CreateGameController createGameController = new CreateGameController(createGameInteractor);

        // Create views
        MenuView menuView = MenuViewFactory.create(viewManagerModel, gameSettingsViewModel, getLoadableGamesViewModel, statisticsViewModel, gameDataAccessObject, gameDataAccessObject);
        GameSettingsView gameSettingsView = new GameSettingsView(gameSettingsViewModel, viewManagerModel, createGameController);
        GameOverView gameOverView = new GameOverView(gameOverViewModel, viewManagerModel);
        RoundView roundView = RoundViewFactory.create(viewManagerModel, roundViewModel, submitAnswerViewModel, toggleAudioViewModel, gameOverViewModel, gameDataAccessObject);
        LoadableGamesView loadableGamesView = LoadableGamesViewFactory.create(viewManagerModel, getLoadableGamesViewModel, roundViewModel, gameDataAccessObject);

        // Add views to app
        views.add(menuView, MenuView.VIEW_NAME);
        views.add(gameSettingsView, GameSettingsView.VIEW_NAME);
        views.add(gameOverView, GameOverView.VIEW_NAME);
        views.add(roundView, RoundView.VIEW_NAME);
        views.add(loadableGamesView, LoadableGamesView.VIEW_NAME);

        // Set starting view
        viewManagerModel.setActiveView(MenuView.VIEW_NAME);
        viewManagerModel.firePropertyChanged();

        // Display app
        application.setLocationRelativeTo(null); // app opens on center of screen
        application.pack();
        application.setVisible(true);
    }
}