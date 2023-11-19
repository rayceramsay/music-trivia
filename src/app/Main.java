package app;

import data_access.InMemoryGameDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.get_loadable_games.GetLoadableGamesViewModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // Setup JFrame app
        JFrame application = new JFrame("Spotify Bandits");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        application.setMinimumSize(new Dimension(500, 300));
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Setup view manager
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Create view models
        GameSettingsViewModel gameSettingsViewModel = new GameSettingsViewModel();
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        RoundViewModel roundViewModel = new RoundViewModel();
        SubmitAnswerViewModel submitAnswerViewModel = new SubmitAnswerViewModel();
        GetLoadableGamesViewModel getLoadableGamesViewModel = new GetLoadableGamesViewModel();

        // Create data access objects
        InMemoryGameDataAccessObject gameDataAccessObject = new InMemoryGameDataAccessObject();

        // Create views
        MenuView menuView = MenuViewFactory.create(viewManagerModel, getLoadableGamesViewModel, gameDataAccessObject);
        GameSettingsView gameSettingsView = new GameSettingsView(gameSettingsViewModel, viewManagerModel);
        GameOverView gameOverView = new GameOverView(gameOverViewModel, viewManagerModel);
        RoundView roundView = RoundViewFactory.create(viewManagerModel, roundViewModel, submitAnswerViewModel, gameOverViewModel, gameDataAccessObject);
        LoadableGamesView loadableGamesView = LoadableGamesViewFactory.create(viewManagerModel, getLoadableGamesViewModel);

        // Add views to app
        views.add(menuView, menuView.viewName);
        views.add(gameSettingsView, gameSettingsView.viewName);
        views.add(gameOverView, gameOverView.viewName);
        views.add(roundView, roundView.viewName);
        views.add(loadableGamesView, loadableGamesView.viewName);

        // Set starting view
        viewManagerModel.setActiveView(menuView.viewName);
        viewManagerModel.firePropertyChanged();

        // Start app
        application.setLocationRelativeTo(null); // app opens on center of screen
        application.pack();
        application.setVisible(true);
    }
}
