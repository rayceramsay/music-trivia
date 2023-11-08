package app;

import data_access.InMemoryGameDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerController;
import interface_adapter.submit_answer.SubmitAnswerPresenter;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;
import use_case.submit_answer.SubmitAnswerInputBoundary;
import use_case.submit_answer.SubmitAnswerInteractor;
import use_case.submit_answer.SubmitAnswerOutputBoundary;
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
        MenuView menuView = new MenuView(menuViewModel, viewManagerModel);
        views.add(menuView, menuView.viewName);

        GameSettingsViewModel gameSettingsViewModel = new GameSettingsViewModel();
        GameSettingsView gameSettingsView = new GameSettingsView(gameSettingsViewModel, viewManagerModel);
        views.add(gameSettingsView, gameSettingsView.viewName);

        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        GameOverView gameOverView = new GameOverView(gameOverViewModel, viewManagerModel);
        views.add(gameOverView, gameOverView.viewName);

        RoundViewModel roundViewModel = new RoundViewModel();
        SubmitAnswerViewModel submitAnswerViewModel = new SubmitAnswerViewModel();
        SubmitAnswerOutputBoundary submitAnswerPresenter = new SubmitAnswerPresenter(submitAnswerViewModel);
        SubmitAnswerGameDataAccessInterface gameDao = new InMemoryGameDataAccessObject();
        SubmitAnswerInputBoundary submitAnswerInteractor = new SubmitAnswerInteractor(gameDao, submitAnswerPresenter);
        SubmitAnswerController submitAnswerController = new SubmitAnswerController(submitAnswerInteractor);
        RoundView roundView = new RoundView(roundViewModel, submitAnswerViewModel, submitAnswerController);
        views.add(roundView, roundView.viewName);

        viewManagerModel.setActiveView(roundView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setLocationRelativeTo(null); // app opens on center of screen

        application.pack();
        application.setVisible(true);
    }
}