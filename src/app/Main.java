package app;

import data_access.InMemoryGameDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_game.CreateGameController;
import interface_adapter.create_game.CreateGamePresenter;
import interface_adapter.finish_round.FinishRoundController;
import interface_adapter.finish_round.FinishRoundPresenter;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.game_settings.GameSettingsState;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.statistics.StatisticsViewModel;
import interface_adapter.submit_answer.SubmitAnswerController;
import interface_adapter.submit_answer.SubmitAnswerPresenter;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
import use_case.create_game.CreateGameDataAccessInterface;
import use_case.create_game.CreateGameInteractor;
import use_case.finish_round.FinishRoundInputBoundary;
import use_case.finish_round.FinishRoundInteractor;
import use_case.submit_answer.SubmitAnswerInputBoundary;
import use_case.submit_answer.SubmitAnswerInteractor;
import use_case.create_game.*;
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

        InMemoryGameDataAccessObject gameDataAccessObject = new InMemoryGameDataAccessObject();
        
        // Create View Models
        MenuViewModel menuViewModel = new MenuViewModel();
        GameSettingsViewModel gameSettingsViewModel = new GameSettingsViewModel();
        RoundViewModel roundViewModel = new RoundViewModel();
        SubmitAnswerViewModel submitAnswerViewModel = new SubmitAnswerViewModel();
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        StatisticsViewModel statisticsViewModel = new StatisticsViewModel();
        ToggleAudioViewModel toggleAudioViewModel = new ToggleAudioViewModel();

        
        // Create views 
        MenuView menuView = MenuViewUseCaseFactory.create(menuViewModel, viewManagerModel, statisticsViewModel, gameDataAccessObject, gameSettingsViewModel);
        GameOverView gameOverView = new GameOverView(gameOverViewModel, viewManagerModel);
        RoundView roundView = RoundViewFactory.create(viewManagerModel, roundViewModel, submitAnswerViewModel, toggleAudioViewModel, gameOverViewModel, gameDataAccessObject);
        CreateGameOutputBoundary createGamePresenter = new CreateGamePresenter(viewManagerModel, roundViewModel);
        CreateGameInputBoundary createGameInteractor = new CreateGameInteractor(gameDataAccessObject, createGamePresenter, roundViewModel);
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