package app;

import data_access.game_data.SQLiteDatabaseGameDataAccessObject;
import data_access.api.SongAPI;
import data_access.game_data.GameDataAccessInterface;
import data_access.api.SpotifyAPI;
import interface_adapter.ViewManagerModel;
import interface_adapter.exit_round.ExitRoundViewModel;
import interface_adapter.create_game.CreateGameViewModel;
import interface_adapter.finish_round.FinishRoundViewModel;
import interface_adapter.game_over.GameOverViewModel;
import entity.*;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.get_loadable_games.GetLoadableGamesViewModel;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.statistics.StatisticsViewModel;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
import io.github.cdimascio.dotenv.Dotenv;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final Dotenv dotenv = Dotenv.load();  // load environment variables

    public static void main(String[] args) {
        // Setup JFrame app
        JFrame application = new JFrame("Spotify Bandits");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        application.setMinimumSize(new Dimension(600, 300));
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
        ExitRoundViewModel exitRoundViewModel = new ExitRoundViewModel(MenuView.VIEW_NAME);
        FinishRoundViewModel finishRoundViewModel = new FinishRoundViewModel(RoundView.VIEW_NAME);
        CreateGameViewModel createGameViewModel = new CreateGameViewModel(RoundView.VIEW_NAME);
        LoadGameViewModel loadGameViewModel = new LoadGameViewModel(RoundView.VIEW_NAME);

        // Create factories and data access objects
        PlayableAudioFactory playableAudioFactory = new CommonPlayableAudioFactory();
        SongFactory songFactory = new CommonSongFactory();
        SongAPI songAPI = new SpotifyAPI(songFactory, playableAudioFactory, dotenv.get("SPOTIFY_CLIENT_ID"), dotenv.get("SPOTIFY_CLIENT_SECRET"));
        RoundFactory roundFactory = new CommonRoundFactory(songAPI);
        GameDataAccessInterface gameDataAccessObject = new SQLiteDatabaseGameDataAccessObject(dotenv.get("SQLITE_DB_PATH_PRODUCTION"), roundFactory, songFactory, playableAudioFactory);

        // Create views
        MenuView menuView = MenuViewFactory.create(viewManagerModel, gameSettingsViewModel, getLoadableGamesViewModel, statisticsViewModel, gameDataAccessObject);
        GameSettingsView gameSettingsView = GameSettingsViewFactory.create(viewManagerModel, roundViewModel, createGameViewModel, gameSettingsViewModel, gameDataAccessObject, roundFactory);
        GameOverView gameOverView = new GameOverView(gameOverViewModel, viewManagerModel);
        RoundView roundView = RoundViewFactory.create(viewManagerModel, roundViewModel, submitAnswerViewModel, finishRoundViewModel, createGameViewModel, loadGameViewModel, toggleAudioViewModel, gameOverViewModel, exitRoundViewModel, gameDataAccessObject, roundFactory);
        LoadableGamesView loadableGamesView = LoadableGamesViewFactory.create(viewManagerModel, getLoadableGamesViewModel, roundViewModel, loadGameViewModel, gameDataAccessObject);

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
