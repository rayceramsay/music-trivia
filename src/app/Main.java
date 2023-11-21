package app;

import data_access.InMemoryGameDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_game.CreateGameController;
import interface_adapter.create_game.CreateGamePresenter;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.game_settings.GameSettingsState;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.round.RoundViewModel;
import use_case.create_game.CreateGameDataAccessInterface;
import use_case.create_game.CreateGameInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main{
    public static void main(String[] args){

        JFrame application = new JFrame("Spotify Bandits");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        application.setMinimumSize(new Dimension(300, 200));

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Create Views
        MenuViewModel menuViewModel = new MenuViewModel();
        GameSettingsViewModel gameSettingsViewModel = new GameSettingsViewModel();
        MenuView menuView = new MenuView(menuViewModel, viewManagerModel, gameSettingsViewModel);
        views.add(menuView, menuView.viewName);

        InMemoryGameDataAccessObject gameDataAccessObject = new InMemoryGameDataAccessObject();
        RoundViewModel roundViewModel = new RoundViewModel();
        CreateGamePresenter createGamePresenter = new CreateGamePresenter(viewManagerModel, roundViewModel);
        CreateGameInteractor createGameInteractor = new CreateGameInteractor(gameDataAccessObject, createGamePresenter, roundViewModel);
        CreateGameController createGameController = new CreateGameController(createGameInteractor);
        GameSettingsView gameSettingsView = new GameSettingsView(gameSettingsViewModel, viewManagerModel, createGameController);
        views.add(gameSettingsView, gameSettingsView.viewName);

        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        GameOverView gameOverView = new GameOverView(gameOverViewModel, viewManagerModel);
        views.add(gameOverView, gameOverView.viewName);

        RoundView roundView = new RoundView(roundViewModel, viewManagerModel);
        views.add(roundView, roundView.viewName);

        viewManagerModel.setActiveView(menuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setLocationRelativeTo(null); // app opens on center of screen

        application.pack();
        application.setVisible(true);

    }
}