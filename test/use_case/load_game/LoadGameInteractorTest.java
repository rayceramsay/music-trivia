package use_case.load_game;

import data_access.api.MockAPI;
import data_access.game_data.GameDataAccessInterface;
import data_access.game_data.InMemoryGameDataAccessObject;
import data_access.api.SongAPI;
import entity.*;
import data_access.api.MockAPI;
import data_access.api.SongAPI;
import org.junit.Before;
import org.junit.Test;
import use_case.load_game.*;

import static org.junit.Assert.*;

public class LoadGameInteractorTest {

    private GameDataAccessInterface gameRepository;
    private Game expectedGame;
    private boolean isPresenterCalled = false;

    /**
     * Initializes gameRepository with loadable games.
     */
    @Before
    public void setupGameRepository() {
        gameRepository = new InMemoryGameDataAccessObject();
        SongAPI songAPI = new MockAPI(new CommonSongFactory());
        RoundFactory roundFactory = new CommonRoundFactory(songAPI);

        for (int i = 0; i < 3; i++) {
            Game game = new CommonGame("hip hop " + i, "hard", 1, 3);
            Round round = roundFactory.createHardRound("hip hop " + i);
            game.setCurrentRound(round);
            gameRepository.save(game);

            if (i == 1) {
                expectedGame = game;
            }
        }
    }

    /**
     * Test that the interactor outputs the correct game data when given a loadable game ID.
     */
    @Test
    public void correctGameDataTest() {
        LoadGameInputData inputData = new LoadGameInputData(expectedGame.getID());

        LoadGameOutputBoundary presenter = new LoadGameOutputBoundary() {
            @Override
            public void prepareView(LoadGameOutputData outputData) {
                isPresenterCalled = true;

                assertEquals(expectedGame.getID(), outputData.getGameId());
                assertEquals(expectedGame.getCurrentRound().getQuestion(), outputData.getQuestion());
                assertEquals(expectedGame.getRoundsPlayed(), outputData.getCurrentRoundNumber());
                assertEquals(expectedGame.getMaxRounds(), outputData.getMaxRounds());
                assertEquals(expectedGame.getCurrentLives(), outputData.getCurrentLives());
                assertEquals(expectedGame.getInitialLives(), outputData.getInitialLives());
                assertEquals(expectedGame.getGenre(), outputData.getGenre());
            }
        };

        LoadGameInputBoundary interactor = new LoadGameInteractor(presenter, gameRepository);
        interactor.execute(inputData);

        assertTrue("Presenter did not get called", isPresenterCalled);
    }
}
