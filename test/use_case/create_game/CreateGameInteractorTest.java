package use_case.create_game;

import data_access.InMemoryGameDataAccessObject;
import data_access.api.SongAPI;
import data_access.api.SpotifyAPI;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_game.CreateGamePresenter;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.create_game.*;
import view.RoundView;

import static org.junit.Assert.*;

public class CreateGameInteractorTest {

    private InMemoryGameDataAccessObject gameDataAccessObject;
    private RoundFactory roundFactory;

    @Before
    public void init() {
        SongFactory songFactory = new CommonSongFactory();
        SongAPI songAPI = new SpotifyAPI(songFactory);
        roundFactory = new CommonRoundFactory(songAPI);
        gameDataAccessObject = new InMemoryGameDataAccessObject();
    }

    @Test
    public void createGameOutputDataTest() {

        String genre = "Hip-Hop";
        String difficulty = "Hard";
        int lives = 3;
        int rounds = 10;

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreateGameInputData createGameInputData = new CreateGameInputData(genre, difficulty, lives, rounds);
        RoundViewModel roundViewModel = new RoundViewModel();
        CreateGamePresenter createGamePresenter = new CreateGamePresenter(viewManagerModel, roundViewModel) {
            @Override
            public void prepareFirstRoundView (CreateGameOutputData outputData) {
                assert outputData.getGenre().equals(genre);
                assert outputData.getDifficulty().equals(difficulty);
                assert outputData.getLives() == lives;
                assert outputData.getRounds() == rounds;
            }
        };

        CreateGameInputBoundary interactor = new CreateGameInteractor(gameDataAccessObject, createGamePresenter, roundViewModel, roundFactory);

        interactor.execute(createGameInputData);


    }

    @Test
    public void gameCreatedTest () {

        String genre = "Hip-Hop";
        String difficulty = "Hard";
        int lives = 3;
        int rounds = 10;

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreateGameInputData createGameInputData = new CreateGameInputData(genre, difficulty, lives, rounds);
        RoundViewModel roundViewModel = new RoundViewModel();
        CreateGamePresenter createGamePresenter = new CreateGamePresenter(viewManagerModel, roundViewModel);

        CreateGameInputBoundary interactor = new CreateGameInteractor(gameDataAccessObject, createGamePresenter, roundViewModel, roundFactory);

        interactor.execute(createGameInputData);

        String ID = roundViewModel.getState().getGameId();

        assertEquals(gameDataAccessObject.getGameByID(ID).getDifficulty(), difficulty);
        assertEquals(gameDataAccessObject.getGameByID(ID).getGenre(), genre);
        assert gameDataAccessObject.getGameByID(ID).getInitialLives() == lives;
        assert gameDataAccessObject.getGameByID(ID).getCurrentLives() == lives;
        assert gameDataAccessObject.getGameByID(ID).getMaxRounds() == rounds;
        assert gameDataAccessObject.getGameByID(ID).getRounds().size() == 1;
    }

    @Test
    public void roundStateTest () {
        String genre = "Hip-Hop";
        String difficulty = "Hard";
        int lives = 3;
        int rounds = 10;

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreateGameInputData createGameInputData = new CreateGameInputData(genre, difficulty, lives, rounds);
        RoundViewModel roundViewModel = new RoundViewModel();
        CreateGamePresenter createGamePresenter = new CreateGamePresenter(viewManagerModel, roundViewModel);

        CreateGameInputBoundary interactor = new CreateGameInteractor(gameDataAccessObject, createGamePresenter, roundViewModel, roundFactory);

        interactor.execute(createGameInputData);

        RoundState state = roundViewModel.getState();

        assert state.getInitialLives() == lives;
        assert state.getCurrentLives() == lives;
        assert state.getMaxRounds() == rounds;
        assertEquals(state.getGenre(), genre);
        assert state.getCurrentRoundNumber() == 1;
    }



}
