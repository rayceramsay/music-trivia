package app;

import data_access.InMemoryGameDataAccessObject;

import data_access.api.SpotifyAPI;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_game.CreateGameController;
import interface_adapter.create_game.CreateGamePresenter;
import interface_adapter.game_over.GameOverViewModel;
import entity.*;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import use_case.create_game.CreateGameInteractor;
import use_case.create_game.*;
import view.*;
import javax.swing.*;
import java.awt.*;

public class Main{
    public static void main(String[] args){
        // View
        JFrame application = new JFrame("Spotify Bandits");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        application.setMinimumSize(new Dimension(300, 200));

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        InMemoryGameDataAccessObject gameDataAccessObject = new InMemoryGameDataAccessObject();

        RoundFactory roundFactory = new CommonRoundFactory(new SpotifyAPI(new CommonSongFactory()));

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // View Models
        MenuViewModel menuViewModel = new MenuViewModel();
        GameSettingsViewModel gameSettingsViewModel = new GameSettingsViewModel();
        RoundViewModel roundViewModel = new RoundViewModel();
        SubmitAnswerViewModel submitAnswerViewModel = new SubmitAnswerViewModel();
        GameOverViewModel gameOverViewModel = new GameOverViewModel();

        // Views
        MenuView menuView = new MenuView(menuViewModel, viewManagerModel, gameSettingsViewModel);
        GameOverView gameOverView = new GameOverView(gameOverViewModel, viewManagerModel);
        RoundView roundView = RoundViewFactory.create(viewManagerModel,
                roundViewModel,
                submitAnswerViewModel,
                gameOverViewModel,
                gameDataAccessObject,
                roundFactory);
        CreateGameOutputBoundary createGamePresenter = new CreateGamePresenter(viewManagerModel, roundViewModel);
        CreateGameInputBoundary createGameInteractor = new CreateGameInteractor(gameDataAccessObject, createGamePresenter, roundViewModel, roundFactory);
        CreateGameController createGameController = new CreateGameController(createGameInteractor);
        GameSettingsView gameSettingsView = new GameSettingsView(gameSettingsViewModel, viewManagerModel, createGameController);

        // Add views
        views.add(menuView, menuView.viewName);
        views.add(roundView, roundView.viewName);
        views.add(gameOverView, gameOverView.viewName);
        views.add(gameSettingsView, gameSettingsView.viewName);

        viewManagerModel.setActiveView(menuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setLocationRelativeTo(null); // app opens on center of screen

        application.pack();
        application.setVisible(true);
    }
}
