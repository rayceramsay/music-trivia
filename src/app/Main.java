package app;

import data_access.InMemoryGameDataAccessObject;
import entity.CommonGame;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
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
        ToggleAudioViewModel toggleAudioViewModel = new ToggleAudioViewModel();

        // Create data access objects
        InMemoryGameDataAccessObject gameDataAccessObject = new InMemoryGameDataAccessObject();

        // Create views
        MenuView menuView = new MenuView(menuViewModel, viewManagerModel);
        GameSettingsView gameSettingsView = new GameSettingsView(gameSettingsViewModel, viewManagerModel);
        GameOverView gameOverView = new GameOverView(gameOverViewModel, viewManagerModel);
        RoundView roundView = RoundViewFactory.create(roundViewModel, submitAnswerViewModel, toggleAudioViewModel, gameDataAccessObject);

        // Add views to app
        views.add(menuView, menuView.viewName);
        views.add(gameSettingsView, gameSettingsView.viewName);
        views.add(gameOverView, gameOverView.viewName);
        views.add(roundView, roundView.viewName);

        // Set starting view
        viewManagerModel.setActiveView(roundView.viewName);
        viewManagerModel.firePropertyChanged();

        // Start app
        application.setLocationRelativeTo(null); // app opens on center of screen
        application.pack();
        application.setVisible(true);

        Game game = new CommonGame("pop", "hard", 10, 3);
        Song song = new CommonSong("Closer", "The Chainsmokers", new OnlineMP3PlayableAudio("path/song.mp3"));
        Round round = new TextInputRound(song, "What song is this?", "Closer");
        game.setCurrentRound(round);
        gameDataAccessObject.save(game);

        RoundState roundState = roundViewModel.getState();
        roundState.setGenre("pop");
        roundState.setMaxRounds(10);
        roundState.setInitialLives(3);
        roundState.setGameId(game.getID());
    }
}